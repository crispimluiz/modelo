package com.crispimluiz.modelagem.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.crispimluiz.modelagem.domain.Cliente;
import com.crispimluiz.modelagem.domain.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Integer>{
	
	//Não necessita de ser envolvida no banco de dados
	//Deixa mais rápida e diminiu o lokin
	@Transactional(readOnly=true)
	Page<Pedido> findByCliente(Cliente cliente, PageRequest pageRequest);
	
}
