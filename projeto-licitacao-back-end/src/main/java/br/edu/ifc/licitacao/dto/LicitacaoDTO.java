/**
 * 23 de mar. de 2021
 */
package br.edu.ifc.licitacao.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe DTO que representa uma licitação e seus atributos.
 * 
 * @author Daryan Avi
 */
public class LicitacaoDTO {
	private int id;
	private String titulo, enderecoEletronico, dataPublicacao, dataAbertura, horarioAbertura, codigoUASG, objeto;
	private List<String> arquivos;
	private boolean lido;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}
	
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	public String getEnderecoEletronico() {
		return enderecoEletronico;
	}
	
	public void setEnderecoEletronico(String enderecoEletronico) {
		this.enderecoEletronico = enderecoEletronico;
	}
	
	public String getDataPublicacao() {
		return dataPublicacao;
	}
	
	public void setDataPublicacao(String dataPublicacao) {
		this.dataPublicacao = dataPublicacao;
	}
	
	public String getDataAbertura() {
		return dataAbertura;
	}
	
	public void setDataAbertura(String dataAbertura) {
		this.dataAbertura = dataAbertura;
	}
	
	public String getHorarioAbertura() {
		return horarioAbertura;
	}
	
	public void setHorarioAbertura(String horarioAbertura) {
		this.horarioAbertura = horarioAbertura;
	}
	
	public String getCodigoUASG() {
		return codigoUASG;
	}
	
	public void setCodigoUASG(String codigoUASG) {
		this.codigoUASG = codigoUASG;
	}
	
	public String getObjeto() {
		return objeto;
	}
	
	public void setObjeto(String objeto) {
		this.objeto = objeto;
	}
	
	public List<String> getArquivos() {
		return arquivos;
	}
	
	public void setArquivos(List<String> arquivos) {
		this.arquivos = arquivos;
	}
	
	public void addArquivo(String arquivo) {
		if (arquivos == null) {
			arquivos = new ArrayList<>();
		}
		
		arquivos.add(arquivo);
	}
	
	public boolean isLido() {
		return lido;
	}

	public void setLido(boolean lido) {
		this.lido = lido;
	}

	@Override
	public String toString() {
		return "LicitacaoDTO [id=" + id + ", titulo=" + titulo + ", enderecoEletronico="
				+ enderecoEletronico + ", dataPublicacao=" + dataPublicacao
				+ ", dataAbertura=" + dataAbertura + ", horarioAbertura="
				+ horarioAbertura + ", codigoUASG=" + codigoUASG + ", objeto="
				+ objeto + ", arquivos=" + arquivos + ", lido=" + lido + "]";
	}
}