/**
 * 26 de mar. de 2021
 */
package br.edu.ifc.licitacao.configuracoes;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Classe de configurações.
 * 
 * @author Daryan Avi
 */
@Configuration
public class LicitacaoConfiguracoes {
	/**
	 * Configuração para que CORS aceite requisições vindas de qualquer endereço.
	 * Criada para facilitar testes na fase de desenvolvimento.
	 * 
	 * @return WebMvcConfigurer
	 */
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedMethods("HEAD", "GET", "PUT", "POST", "DELETE", "PATCH");
            }
        };
    }
}