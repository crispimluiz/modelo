package com.crispimluiz.modelagem.dto;

import java.io.Serializable;

import com.crispimluiz.modelagem.domain.Categoria;

public class CategoriaDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	/*
	 * DTO e para definir os dados que desejo trafegar a categoria quando
	 * transmitida vem os produtos no DTO é para quando quando chamar todas as
	 * categorias vir apenas elas sem os produtos.
	 */

	private Integer id;
	private String nome;

	public CategoriaDTO() {

	}
	
	//Recebe Categoria
	public CategoriaDTO(Categoria obj) {
		
		id = obj.getId();
		nome = obj.getNome();
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
