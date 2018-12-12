package com.crispimluiz.modelagem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.crispimluiz.modelagem.domain.Pagamento;

@Repository
public interface PagamentoRepository extends JpaRepository<Pagamento, Integer>{
	
	//Basta fazer repository da superClasse ou da abstrata
	
}
