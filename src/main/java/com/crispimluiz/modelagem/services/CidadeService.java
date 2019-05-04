package com.crispimluiz.modelagem.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crispimluiz.modelagem.domain.Cidade;
import com.crispimluiz.modelagem.repositories.CidadeRepository;

@Service
public class CidadeService {
	
	@Autowired
	private CidadeRepository repo;
	
	public List<Cidade> findByEstado(Integer estadoid){
		return repo.findCidades(estadoid);
	}
}
