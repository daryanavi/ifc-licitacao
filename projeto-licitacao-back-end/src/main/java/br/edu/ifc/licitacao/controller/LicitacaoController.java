/**
 * 22 de mar. de 2021
 */
package br.edu.ifc.licitacao.controller;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifc.licitacao.services.LicitacaoRequisicao;

/**
 * Classe que manipula as requisições REST da API.
 * 
 * @author Daryan Avi
 */
@RestController
@RequestMapping("/api")
public class LicitacaoController {
	@Autowired
	private LicitacaoRequisicao requisicao;
	
	/**
	 * Retorna lista de licitações.
	 * 
	 * @return String
	 */
	@GetMapping("/licitacoes")
	public String licitacoes() {
		System.out.println();
		System.out.println("Mapeando /licitacoes");
		
		JSONObject jObj = new JSONObject();
		jObj.put("licitacoes", requisicao.getLicitacoes());
		
		System.out.println("JSON de resposta: " + jObj);
		
		return jObj.toString();
	}
	
	/**
	 * Marca uma licitação como lida. Retorna mensagem de acordo com resultado da operação.
	 * 
	 * @param id
	 * @return String
	 */
	@GetMapping("/marcacao-leitura")
	public String marcar(@RequestParam int id) {
		System.out.println();
		System.out.println("Mapeando /marcacao-leitura?id=" + id);
		
		String msg = requisicao.marcar(id) ? "Licitação marcada como lida!" : "Licitação não encontrada!";
		
		JSONObject jObj = new JSONObject();
		jObj.put("msg", msg);
		
		System.out.println("JSON de resposta: " + jObj);
		
		return jObj.toString();
	}
}