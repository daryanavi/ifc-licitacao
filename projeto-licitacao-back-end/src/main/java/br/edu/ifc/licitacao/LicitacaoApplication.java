package br.edu.ifc.licitacao;

import java.util.Collections;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Classe que executa aplicação.
 * 
 * @author Daryan Avi
 */
@SpringBootApplication
public class LicitacaoApplication {
	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(LicitacaoApplication.class);
		// Configura aplicação para porta 8080
        app.setDefaultProperties(Collections.singletonMap("server.port", "8080"));
		app.run(args);
	}
}