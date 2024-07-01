package br.com.lmf.ControleContatos.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class OpenApiConfig {

	@Bean
	public OpenAPI customOpenApi() {
		return new OpenAPI().components(new Components().addSecuritySchemes("basicScheme",
				new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("basic"))).info(
						new Info().title("API Rest de Controle de Contatos")
								.description("Está aplicação realiza o cadastro e controle de pessoas e contatos")
								.contact(new Contact().name("Lucas Martins de França")
										.email("lucasfranca_zs@hotmail.com").url("http://localhost:8080"))
								.version("Versão 0.0.1 SNAPSHOT"));

	}
}
