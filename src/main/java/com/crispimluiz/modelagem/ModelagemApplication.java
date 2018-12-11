package com.crispimluiz.modelagem;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.crispimluiz.modelagem.domain.Categoria;
import com.crispimluiz.modelagem.domain.Cidade;
import com.crispimluiz.modelagem.domain.Cliente;
import com.crispimluiz.modelagem.domain.Endereco;
import com.crispimluiz.modelagem.domain.Estado;
import com.crispimluiz.modelagem.domain.Produto;
import com.crispimluiz.modelagem.domain.enums.TipoCliente;
import com.crispimluiz.modelagem.repositories.CategoriaRepository;
import com.crispimluiz.modelagem.repositories.CidadeRepository;
import com.crispimluiz.modelagem.repositories.ClienteRepository;
import com.crispimluiz.modelagem.repositories.EnderecoRepository;
import com.crispimluiz.modelagem.repositories.EstadoRepository;
import com.crispimluiz.modelagem.repositories.ProdutoRepository;

@SpringBootApplication
public class ModelagemApplication implements CommandLineRunner{
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private EstadoRepository estadoRepository;
	@Autowired
	private CidadeRepository cidadeRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	
	public static void main(String[] args) {
		SpringApplication.run(ModelagemApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		
		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);
		
		cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3));
		
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");
		
		Cidade c1 = new Cidade(null, "Uberlândia", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);
		
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2,c3));
		
		
		estadoRepository.saveAll(Arrays.asList(est1,est2));
		cidadeRepository.saveAll(Arrays.asList(c1,c2,c3));
		
		Cliente cli1 = new Cliente(null, "Maria Aparecida", "84311383484", "maria@gmail.com",TipoCliente.PESSOAFISICA);
		
		cli1.getTelefones().addAll(Arrays.asList("3265785496","52489634"));
		
		Endereco e1 = new Endereco(null, "Rua das Emboabas", "55b", "Ap 310", "Jardim", "38447587", cli1, c1);
		Endereco e2 = new Endereco(null, "Rua da Mata", "10A", "Ap 10", "Jardim das Acasias", "38447507", cli1, c2);
		
		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));
		
		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(e1,e2));
	}
}
