package com.crispimluiz.modelagem.Config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.crispimluiz.modelagem.services.DBService;

@Configuration
@Profile("teste")
public class TesteConfig {
	/* Essa classe é para configurar o application.properties
	 *Nela eu Criei a classe DBService onde levei a teste banco
	 *que estava no ModelagemApplication para lá
	 *Altera o application.properties e cria o application-teste
	 */
	
	@Autowired
	private DBService dbservice;
	
	@Bean
	public boolean instantiateDatabase() throws ParseException {
		dbservice.instantiateTesteDataBase();
		return true;
	}
}
