package com.crispimluiz.modelagem.resouces;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.crispimluiz.modelagem.domain.Cidade;
import com.crispimluiz.modelagem.domain.Estado;
import com.crispimluiz.modelagem.dto.CidadeDTO;
import com.crispimluiz.modelagem.dto.EstadoDTO;
import com.crispimluiz.modelagem.services.CidadeService;
import com.crispimluiz.modelagem.services.EstadoService;

@RestController
@RequestMapping(value="/estados")
public class EstadoResorce {
	

	@Autowired
	private CidadeService cidadeService;
	
	@Autowired
	private EstadoService service;
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<EstadoDTO>> findAll(){
		List<Estado> list = service.findAll();
		List<EstadoDTO> listDto = list.stream().map(obj -> new EstadoDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}
	
	//Buscar Cidades
	@RequestMapping(value="/{estadoId}/cidades", method=RequestMethod.GET)
	public ResponseEntity<List<CidadeDTO>> findCidades(@PathVariable Integer estadoId){
		List<Cidade> list = cidadeService.findByEstado(estadoId);
		List<CidadeDTO> listDto = list.stream().map(obj -> new CidadeDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}
}
