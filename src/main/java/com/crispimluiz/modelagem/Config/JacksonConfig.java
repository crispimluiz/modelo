package com.crispimluiz.modelagem.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import com.crispimluiz.modelagem.domain.PagamentoComBoleto;
import com.crispimluiz.modelagem.domain.PagamentoComCartao;
import com.fasterxml.jackson.databind.ObjectMapper;


/*Classe configuração para que o Jackson type no 
 * pagamento e pagamentoBoleto e Cartão funcionem*/
@Configuration
public class JacksonConfig {
/* https://stackoverflow.com/questions/41452598/overcome-can-not-construct-instance-ofinterfaceclass-
without-hinting-the-pare*/
@Bean
	public Jackson2ObjectMapperBuilder objectMapperBuilder() {
		Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder() {
			public void configure(ObjectMapper objectMapper) {
				
				//tem que registrar as subClasses
			objectMapper.registerSubtypes(PagamentoComCartao.class);
			objectMapper.registerSubtypes(PagamentoComBoleto.class);
			super.configure(objectMapper);
			}
		};
		return builder;
	}
}