package com.crispimluiz.modelagem.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.crispimluiz.modelagem.domain.enums.Perfil;
import com.crispimluiz.modelagem.domain.enums.TipoCliente;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Cliente implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nome;
	private String cpfOuCnpj;
	
	//O banco de dados não deixa repetir os dados
	//Tem que mexer no cliente repository
	@Column(unique=true)
	private String email;
	private Integer tipo;
	
	@JsonIgnore
	private String senha;
/*
private TipoCliente(ENUM) tipo; Altera para Integer para gravar o numero no final
*/
	//Cascade e para se excluir cliente o endereço seja excuido
	@OneToMany(mappedBy = "cliente", cascade=CascadeType.ALL)//Varios endereços um cliente
	private List<Endereco> enderecos = new ArrayList<>();

	@ElementCollection//Cria uma tabela telefone
	@CollectionTable(name = "TELEFONE")
	private Set<String> telefones = new HashSet<>();
	
	
	@ElementCollection(fetch=FetchType.EAGER)
	@CollectionTable(name="PERFIS")
	private Set<Integer> perfis = new HashSet<>();
	
	@JsonIgnore//Pedidos do cliente ñ seram serializados
	@OneToMany(mappedBy="cliente")//Mapeada em cliente
	private List<Pedido> pedidos = new ArrayList<>();
	
	public Cliente() {
		addPerfil(Perfil.CLIENTE);
	}

	public Cliente(Integer id, String nome, String cpfOuCnpj, String email, TipoCliente tipo, String senha) {
		super();
		this.id = id;
		this.nome = nome;
		this.cpfOuCnpj = cpfOuCnpj;
		this.email = email;
		this.tipo = (tipo == null) ? null : tipo.getCod();// Add get para manter o tipo-tipo
		this.senha = senha;
		addPerfil(Perfil.CLIENTE);
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

	public String getCpfOuCnpj() {
		return cpfOuCnpj;
	}

	public void setCpfOuCnpj(String cpfOuCnpj) {
		this.cpfOuCnpj = cpfOuCnpj;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public TipoCliente getTipo() {
		return TipoCliente.toEnum(tipo);
		// esse to enum vem da classe enum tipo
	}

	public void setTipo(TipoCliente tipo) {
		this.tipo = tipo.getCod();// add .get também
	}
	
	public String getSenha() {
		return senha;
	}

	public Set<Perfil> getPerfis(){
		return perfis.stream().map(x -> Perfil.toEnum(x)).collect(Collectors.toSet());
	}
	
	public void addPerfil(Perfil perfil) {
		perfis.add(perfil.getCod());
	}
	
	public void setSenha(String senha) {
		this.senha = senha;
	}

	public List<Endereco> getEnderecos() {
		return enderecos;
	}

	public void setEnderecos(List<Endereco> enderecos) {
		this.enderecos = enderecos;
	}

	public Set<String> getTelefones() {
		return telefones;
	}

	public void setTelefones(Set<String> telefones) {
		this.telefones = telefones;
	}

	public List<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
