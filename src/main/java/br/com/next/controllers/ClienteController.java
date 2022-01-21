package br.com.next.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.next.bo.CartaoBo;
import br.com.next.bo.ClienteBo;
import br.com.next.models.entities.CartaoCredito;
import br.com.next.models.entities.CartaoDebito;
import br.com.next.models.entities.Cliente;
import br.com.next.models.entities.ContaCorrente;
import br.com.next.models.entities.Endereco;
import br.com.next.services.CartaoDebService;
import br.com.next.services.CartaoService;
import br.com.next.services.ClienteService;
import br.com.next.services.ContaPoupancaService;
import br.com.next.services.ContaService;
import br.com.next.services.EnderecoService;

@RestController
@RequestMapping("/principal")
public class ClienteController {

	@Autowired
	ClienteService cs;
	@Autowired
	ContaService cons;
	@Autowired
	EnderecoService es;
	@Autowired
	CartaoDebService cds;
	@Autowired
	ContaPoupancaService cps;
	@Autowired
	CartaoService ccs;
	
	CartaoBo cb = new CartaoBo();

	@GetMapping(path = "/buscar")
	public ResponseEntity<?> buscar(@RequestParam int id) {
		Cliente obj = cs.buscar(id);
		return ResponseEntity.ok().body(obj);
	}

	@PostMapping(path = "/cadastrar")
	public ResponseEntity<?> cadastro(@RequestBody Cliente obj) {	
		Endereco en = obj.getEndereco();
		CartaoDebito cd = new CartaoDebito();
		cd.setNomeCartao(obj.getNome());
		cd.setSenha(obj.getSenha());
		
		obj.setEndereco(null);
		obj = cs.inserir(obj);
		ContaCorrente con = new ContaCorrente(obj);
		en.setCliente(obj);
		
		cd.setCliente(obj);
		
		
		obj.setConta(con);
		con = cons.inserir(con);
		
		obj.setEndereco(en);	
		en = es.inserir(en);
		
		obj.setCardDeb(cd);
		cd = cds.inserir(cd);
		cd = cb.criacaoCartaoDebito(cd);
		cd = cds.inserir(cd);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping(path = "/alterar/{id}")
	public ResponseEntity<Void> update(@RequestBody Cliente obj, @PathVariable int id){
		obj.setId(id);
		cs.atualizar(obj);
		
		Endereco endereco = obj.getEndereco();
		
		es.atualizar(endereco);
		
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping(path = "/cancelar/{id}")
	public ResponseEntity<Void> deletar(@PathVariable int id) {
		Cliente cli = cs.buscar(id);
		cs.deletar(id);
		cons.deletar(cli.getConta().getNumeroConta());
		if(cli.getEndereco() != null) {
			es.deletar(cli.getEndereco().getId());
		}
		if(cli.getCardDeb() != null) {
			cds.deletar(cli.getCardDeb().getId());
		}
		if(cli.getContaP() != null) {
			cps.deletar(cli.getContaP().getNumeroConta());
		}
		
		List<CartaoCredito> cartoes = cli.getCartoes();
		
		for(CartaoCredito cartao: cartoes) {
			ccs.deletar(cartao.getId());
		}
		
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping(path = "/login")
	public ResponseEntity<?> login(@RequestParam String cpf, String senha) {
		Cliente obj = cs.logar(cpf, senha);
		atualizarTipo(obj.getId());
		return ResponseEntity.ok().body(obj);
	}
	
	public void atualizarTipo(int id) {
		Cliente cli = new Cliente();
		
		cli = cs.buscar(id);
		
		cli.setTipo(ClienteBo.verificarTipo(cli.getConta().getSaldo()).toString());
		
		cs.atualizar(cli);
	}
}
