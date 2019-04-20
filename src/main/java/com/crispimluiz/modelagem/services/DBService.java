package com.crispimluiz.modelagem.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.crispimluiz.modelagem.domain.Categoria;
import com.crispimluiz.modelagem.domain.Cidade;
import com.crispimluiz.modelagem.domain.Cliente;
import com.crispimluiz.modelagem.domain.Endereco;
import com.crispimluiz.modelagem.domain.Estado;
import com.crispimluiz.modelagem.domain.ItemPedido;
import com.crispimluiz.modelagem.domain.Pagamento;
import com.crispimluiz.modelagem.domain.PagamentoComBoleto;
import com.crispimluiz.modelagem.domain.PagamentoComCartao;
import com.crispimluiz.modelagem.domain.Pedido;
import com.crispimluiz.modelagem.domain.Produto;
import com.crispimluiz.modelagem.domain.enums.EstadoPagamento;
import com.crispimluiz.modelagem.domain.enums.Perfil;
import com.crispimluiz.modelagem.domain.enums.TipoCliente;
import com.crispimluiz.modelagem.repositories.CategoriaRepository;
import com.crispimluiz.modelagem.repositories.CidadeRepository;
import com.crispimluiz.modelagem.repositories.ClienteRepository;
import com.crispimluiz.modelagem.repositories.EnderecoRepository;
import com.crispimluiz.modelagem.repositories.EstadoRepository;
import com.crispimluiz.modelagem.repositories.ItemPedidoRepository;
import com.crispimluiz.modelagem.repositories.PagamentoRepository;
import com.crispimluiz.modelagem.repositories.PedidoRepository;
import com.crispimluiz.modelagem.repositories.ProdutoRepository;

@Service
public class DBService {
	/* Essa classe é para configurar o application.properties
	 *Nela eu Criei a classe DBService onde levei a teste banco
	 *que estava no ModelagemApplication para lá
	 *Altera o application.properties e cria o application-teste
	 */
	
	@Autowired
	private BCryptPasswordEncoder pe;
	
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
	@Autowired
	private PedidoRepository pedidoRepository;
	@Autowired
	private PagamentoRepository pagamentoRepository;
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	public void instantiateTesteDataBase() throws ParseException {
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		Categoria cat3 = new Categoria (null, "Cama Mesa e banho");
		Categoria cat4 = new Categoria (null, "Eletrônicos");
		Categoria cat5 = new Categoria (null, "Jardinagem");
		Categoria cat6 = new Categoria (null, "Decoração");
		Categoria cat7 = new Categoria (null, "Perfumaria");
		
		
		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);
		Produto p4 = new Produto(null, "Mesa de escritório", 300.00);
		Produto p5 = new Produto(null, "Toalha", 50.00);
		Produto p6 = new Produto(null, "Colcha", 200.00);
		Produto p7 = new Produto(null, "Tv True Collor", 1200.00);
		Produto p8 = new Produto(null, "Roçadeira", 800.00);
		Produto p9 = new Produto(null, "Abajour", 100.00);
		Produto p10 = new Produto(null, "Pendente", 180.00);
		Produto p11 = new Produto(null, "Shampoo", 90.00);
		
		cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
		cat2.getProdutos().addAll(Arrays.asList(p2, p4));
		cat3.getProdutos().addAll(Arrays.asList(p5, p6));
		cat4.getProdutos().addAll(Arrays.asList(p1, p2, p3, p7));
		cat5.getProdutos().addAll(Arrays.asList(p8));
		cat6.getProdutos().addAll(Arrays.asList(p9, p10));
		cat7.getProdutos().addAll(Arrays.asList(p11));
		
		p1.getCategorias().addAll(Arrays.asList(cat1, cat4));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2, cat4));
		p3.getCategorias().addAll(Arrays.asList(cat1,cat4));
		p4.getCategorias().addAll(Arrays.asList(cat2));
		p5.getCategorias().addAll(Arrays.asList(cat3));
		p6.getCategorias().addAll(Arrays.asList(cat3));
		p7.getCategorias().addAll(Arrays.asList(cat4));
		p8.getCategorias().addAll(Arrays.asList(cat5));
		p9.getCategorias().addAll(Arrays.asList(cat6));
		p10.getCategorias().addAll(Arrays.asList(cat6));
		p11.getCategorias().addAll(Arrays.asList(cat7));
		
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11));
		
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");
		
		Cidade c1 = new Cidade(null, "Uberlândia", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);
		
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2,c3));
		
		
		estadoRepository.saveAll(Arrays.asList(est1,est2));
		cidadeRepository.saveAll(Arrays.asList(c1,c2,c3));
		
		Cliente cli1 = new Cliente(null, "Maria Aparecida", "84311383484", "crispimluiz@live.com",TipoCliente.PESSOAFISICA, pe.encode("123"));
		
		Cliente cli2 = new Cliente(null, "Alice Pereia", "70332349020", "crluiz@gmail.com",TipoCliente.PESSOAFISICA, pe.encode("321"));
		cli2.addPerfil(Perfil.ADMIN);
		
		cli1.getTelefones().addAll(Arrays.asList("3265785496","52489634"));
		cli2.getTelefones().addAll(Arrays.asList("3265700000","52480000"));
		
		
		Endereco e1 = new Endereco(null, "Rua das Emboabas", "55b", "Ap 310", "Jardim", "38447587", cli1, c1);
		Endereco e2 = new Endereco(null, "Rua da Mata", "10A", "Ap 10", "Jardim das Acasias", "38447507", cli1, c2);
		Endereco e3 = new Endereco(null, "Rua Alameda Nasciute", "234", null, "Centro", "34555098", cli2, c3);
		
		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));
		cli2.getEnderecos().addAll(Arrays.asList(e1, e2));
		
		clienteRepository.saveAll(Arrays.asList(cli1, cli2));
		enderecoRepository.saveAll(Arrays.asList(e1, e2, e3));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyy hh:mm");
		
		Pedido ped1 = new Pedido(null, sdf.parse("30/12/2017 14:25"), cli1, e1 );
		Pedido ped2 = new Pedido(null, sdf.parse("08/01/2018 09:43"), cli1, e2 );
		
		Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pagto1);
		
		Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("08/02/2018 00:00"), null);
		ped2.setPagamento(pagto2);
		
		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));
		
		pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
		pagamentoRepository.saveAll(Arrays.asList(pagto1, pagto2));
		
		ItemPedido ip1= new ItemPedido(ped1, p1, 0.00, 1, 2000.00);
		ItemPedido ip2= new ItemPedido(ped1, p3, 0.00, 2, 80.00);
		ItemPedido ip3= new ItemPedido(ped2, p2, 100.00, 1, 800.00);
		
		ped1.getItens().addAll(Arrays.asList(ip1, ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));
		
		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));
		p3.getItens().addAll(Arrays.asList(ip2));
		
		itemPedidoRepository.saveAll(Arrays.asList(ip1, ip2,ip3));
	}
}
