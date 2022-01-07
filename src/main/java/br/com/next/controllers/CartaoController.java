package br.com.next.controllers;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.next.bo.CartaoBo;
import br.com.next.models.entities.CartaoCredito;
import br.com.next.services.CartaoService;
import br.com.next.services.ClienteService;
import br.com.next.models.entities.Cliente;

@RestController
@RequestMapping("/cartoes")
public class CartaoController {

	@Autowired
	ClienteService clis;
	@Autowired
	CartaoService cs;
	CartaoBo cb = new CartaoBo();
	
	@PostMapping(path = "/solicitacao")
	public ResponseEntity<?> criar(@RequestBody CartaoCredito obj,@RequestParam int id,@RequestParam String bandeira){
		Cliente cli = clis.buscar(id);
		cli.getCartoes().add(obj);
		obj.setCliente(cli);
		
		obj = cs.inserir(obj);

		cb.verificar(obj, bandeira);
		
		obj = cs.atualizar(obj);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
}
