package com.crispimluiz.modelagem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.crispimluiz.modelagem.domain.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer>{
	
	//Não necessita de ser envolvida no banco de dados
	//Deixa mais rápida e diminiu o lokin
	@Transactional(readOnly=true)
	Cliente findByEmail(String email);
	//findByEmail - Busca e-mail.
}
