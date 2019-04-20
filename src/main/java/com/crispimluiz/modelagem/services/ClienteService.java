package com.crispimluiz.modelagem.services;


import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.crispimluiz.modelagem.domain.Cidade;
import com.crispimluiz.modelagem.domain.Cliente;
import com.crispimluiz.modelagem.domain.Endereco;
import com.crispimluiz.modelagem.domain.enums.TipoCliente;
import com.crispimluiz.modelagem.dto.ClienteDTO;
import com.crispimluiz.modelagem.dto.ClienteNewDTO;
import com.crispimluiz.modelagem.repositories.ClienteRepository;
import com.crispimluiz.modelagem.repositories.EnderecoRepository;
import com.crispimluiz.modelagem.services.Exception.DataIntegrityException;
import com.crispimluiz.modelagem.services.Exception.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private BCryptPasswordEncoder pe;
	
	@Autowired
	private ClienteRepository repo;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	public Cliente find(Integer id) {
		Optional<Cliente> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
	}

	@Transactional//Com ele irá salvar as outras classes cmo endereço
	public Cliente insert(Cliente obj) {
		obj.setId(null);
		obj = repo.save(obj);
		enderecoRepository.saveAll(obj.getEnderecos());
		return obj;
	}
	
	public Cliente update(Cliente obj) {
		Cliente newObj = find(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}
	
	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		}
		catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir, tem pedidos relacionados");
		}
	}
	
	public List <Cliente> findAll(){
		return repo.findAll();
	}
	//Paginação para que volte uma quantidade certa do BD, senão voltaria todos os valores
	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	
	//Post
	public Cliente fromDTO(ClienteDTO objDto) {
		return new Cliente(objDto.getId(), objDto.getNome(), objDto.getEmail(), null, null, null);
	}
	
	//Post Cliente para salvar Endereço e telefone juntos
		public Cliente fromDTO(ClienteNewDTO objDto) {
			Cliente cli = new Cliente(null, objDto.getNome(), objDto.getCpfOuCnpj(), objDto.getEmail(), 
					TipoCliente.toEnum(objDto.getTipo()), pe.encode(objDto.getSenha()));
			Cidade cid = new Cidade(objDto.getCidadeId(), null, null);
			Endereco end = new Endereco(null, objDto.getLogradouro(), objDto.getNumero(), 
					objDto.getComplemento(), objDto.getBairro(), objDto.getCep(), cli, cid);
			
			//Cliente Conhece Endereço
			cli.getEnderecos().add(end);
			
			//Cliente Conhece Tel
			cli.getTelefones().add(objDto.getTelefone1());
			
			//Se não vier nulo é telefone 2 e add
			if (objDto.getTelefone2()!=null) {
				cli.getTelefones().add(objDto.getTelefone2());
			}
			
			if (objDto.getTelefone3()!=null) {
				cli.getTelefones().add(objDto.getTelefone3());
			}
			return cli;
		}
	
	private void updateData(Cliente newObj, Cliente obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
	}
}
