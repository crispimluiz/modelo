package com.crispimluiz.modelagem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.crispimluiz.modelagem.services.S3Service;

@SpringBootApplication
public class ModelagemApplication implements CommandLineRunner{
	/* Antes do properties-teste ficava o teste de BD aqui
	 * Essa classe é para configurar o application.properties
	 *Nela eu Criei a classe DBService onde levei a teste banco
	 *que estava no ModelagemApplication para lá
	 *Altera o application.properties e cria o application-teste
	 */
	
	@Autowired
	private S3Service s3Service;
	
	public static void main(String[] args) {
		SpringApplication.run(ModelagemApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		s3Service.uploadFile("D:\\teste.jpg");
	}
}
