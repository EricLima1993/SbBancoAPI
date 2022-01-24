package br.com.next.controllers;

import java.net.URI;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.next.bo.CartaoBo;
import br.com.next.models.entities.CartaoCredito;
import br.com.next.models.entities.Cliente;
import br.com.next.services.CartaoService;
import br.com.next.services.ClienteService;
import br.com.next.services.ContaService;

@RestController
@RequestMapping("/cartoes")
public class CartaoController {

	@Autowired
	ClienteService clis;
	@Autowired
	CartaoService cs;
	@Autowired
	ContaService conS;
	CartaoBo cb = new CartaoBo();

	
	@PostMapping(path = "/solicitacao")
	public ResponseEntity<?> criar(@RequestBody CartaoCredito obj,@RequestParam int id,@RequestParam String bandeira){
		double totalPagar = 0;
		if(obj.getAnosContratacaoApolice() > 0) {
			if(obj.isSeguroMorte() == true) {
				totalPagar += (36*obj.getAnosContratacaoApolice());
			}
			if(obj.isSeguroInvalidez() == true) {
				totalPagar += (26*obj.getAnosContratacaoApolice());
			}
			if(obj.isSeguroDesemprego() == true){
				totalPagar += (16*obj.getAnosContratacaoApolice());
			}
			Cliente cli = clis.buscar(id);
			conS.sacar(cli.getConta().getNumeroConta(), cli.getSenha(), totalPagar);
			LocalDate dtm = LocalDate.now();  
			obj.setDataApolice(Date.valueOf(dtm));
			cli.getCartoes().add(obj);
			obj.setCliente(cli);
			
			obj = cs.inserir(obj);

			cb.verificar(obj, bandeira);
			
			obj = cs.atualizar(obj);
			
		}else {
			Cliente cli = clis.buscar(id);
			cli.getCartoes().add(obj);
			obj.setCliente(cli);
			
			obj = cs.inserir(obj);

			cb.verificar(obj, bandeira);
			
			obj = cs.atualizar(obj);
		}
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping(path="/usar")
	public ResponseEntity<Void> sacar(@RequestBody CartaoCredito obj, @RequestParam double valor) {
		cs.usar(obj,valor);
		
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping(path="/pagar")
	public ResponseEntity<Void> pagar(@RequestBody CartaoCredito obj,@RequestParam int id, @RequestParam double valor) {
		Cliente c = clis.buscar(id);
		
		conS.sacar(c.getConta().getNumeroConta(), c.getSenha(), valor);
		
		cs.pagar(obj,valor);
		
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping(path = "/cancelar/{id}")
	public ResponseEntity<Void> deletar(@PathVariable int id,@RequestParam int idC) {
		Cliente cli = clis.buscar(idC);
		CartaoCredito cc = cs.buscar(id);
		
		
		cli.getCartoes().remove(cc);
		clis.atualizar(cli);
		
		cs.deletar(id);
		return ResponseEntity.noContent().build();
	}
}
