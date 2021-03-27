/**
 * 23 de mar. de 2021
 */
package br.edu.ifc.licitacao.services;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import br.edu.ifc.licitacao.dto.LicitacaoDTO;

/**
 * Classe com regras de negócio para realizar requisições de dados ao site do IFC.
 * 
 * @author Daryan Avi
 */
@Service
public class LicitacaoRequisicao {
	private final String URL = "http://www.ifc-riodosul.edu.br/site/dap/category/licitacoes/";
	
	private List<LicitacaoDTO> licitacoes;
	
	public LicitacaoRequisicao() throws IOException {
		doRequisicao();
	}
	
	/**
	 * Busca lista de licitações no site do IFC.
	 * @throws IOException 
	 */
	public void doRequisicao() throws IOException {
		URL url = new URL(URL);
		
		HttpURLConnection conn = (HttpURLConnection)url.openConnection();
		conn.setRequestMethod("GET");
		conn.setDoInput(true);
		
		DataInputStream data = new DataInputStream(conn.getInputStream());
		String s = new String(data.readAllBytes());
		data.close();
		
		// Utiliza biblioteca Jsoup para trabalhar com elementos do HTML
		Document doc = Jsoup.parse(s);
		Element link = doc.getElementsByClass("media-list").first();
		
		licitacoes = new ArrayList<>();
		int id = 0;
		
		for (Element e : link.getElementsByClass("media")) {
			Element mediaHeading = e.getElementsByClass("media-heading").first();
			
			Element a = mediaHeading.selectFirst("a");
			
			// Busca dados da licitação passando como parâmetro URL capturada no HTML
			LicitacaoDTO reqDados = doRequisicaoDados(a.attr("href"));
			
			// Adiciona item à lista de licitações apenas se ele conter o atributo Objeto
			if (reqDados.getObjeto() != null) {
				reqDados.setId(++ id);
			
				licitacoes.add(reqDados);
			}
		}
	}
	
	/**
	 * Busca uma licitação no site do IFC de acordo com a URL passada como parâmetro.
	 * 
	 * @param endereco
	 * @return LicitacaoDTO
	 * @throws IOException 
	 */
	private LicitacaoDTO doRequisicaoDados(String endereco) throws IOException {
		LicitacaoDTO licitacao = new LicitacaoDTO();
		
		URL url = new URL(endereco);
		
		HttpURLConnection conn = (HttpURLConnection)url.openConnection();
		conn.setRequestMethod("GET");
		conn.setDoInput(true);
		
		DataInputStream data = new DataInputStream(conn.getInputStream());
		String s = new String(data.readAllBytes());
		data.close();
		
		// Utiliza biblioteca Jsoup para trabalhar com elementos do HTML
		Document doc = Jsoup.parse(s);
		Element article = doc.selectFirst("article");
		
		// Adiciona Título
		licitacao.setTitulo(article.getElementsByClass("page-subheader").first().selectFirst("h2").text());
		// Adiciona Data de Publicação
		licitacao.setDataPublicacao(article.getElementsByClass("text-muted").first().text());
		
		Element entryContent = article.getElementsByClass("entry-content").first();
		
		Elements paragraphs = entryContent.select("p");
		
		if (paragraphs.size() > 2) {
			Element p = paragraphs.get(0);
			String[] partes = p.html().split("<br>");
			
			if (partes.length == 3) {
				Document d = Jsoup.parse(partes[0]);
				
				// Adiciona Endereço Eletrônico
				licitacao.setEnderecoEletronico(d.text().split(":")[1].trim());
				
				d = Jsoup.parse(partes[1]);
				
				// Adiciona Data de Abertura
				licitacao.setDataAbertura(d.text().split(":")[1].trim());
				
				d = Jsoup.parse(partes[2]);
				
				// Adiciona Horário de Abertura
				licitacao.setHorarioAbertura(d.text().split(":")[1].trim());
			}
			
			p = paragraphs.get(1);
			
			// Adiciona Código UASG
			licitacao.setCodigoUASG(p.text().split(":")[1].trim());
			
			p = paragraphs.get(2);
			
			// Adiciona Objeto
			licitacao.setObjeto(p.text().split(":")[1].trim());
		}
		
		// Adiciona Arquivos
		for (String c : entryContent.select("a").eachAttr("href")) {
			if (! c.endsWith("gov.br/")) {
				licitacao.addArquivo(c);
			}
		}
		
		return licitacao;
	}

	public List<LicitacaoDTO> getLicitacoes() {
		return licitacoes;
	}

	public void setLicitacoes(List<LicitacaoDTO> licitacoes) {
		this.licitacoes = licitacoes;
	}
	
	/**
	 * Marca uma licitação da lista como lida.
	 * 
	 * @param id
	 * @return boolean
	 */
	public boolean marcar(int id) {
		for (LicitacaoDTO l : licitacoes) {
			if (id == l.getId()) {
				l.setLido(true);
				
				return true;
			}
		}
		
		return false;
	}
}