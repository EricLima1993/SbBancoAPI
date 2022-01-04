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

import br.com.next.models.entities.Cliente;
import br.com.next.models.entities.Conta;
import br.com.next.services.ClienteService;
import br.com.next.services.ContaService;

@RestController
@RequestMapping("/principal")
public class ClienteController {

	@Autowired
	ClienteService cs;
	@Autowired
	ContaService cons;

	@PostMapping(path = "/buscar")
	public ResponseEntity<?> buscar(@RequestParam int id) {
		Cliente obj = cs.buscar(id);
		return ResponseEntity.ok().body(obj);
	}

	@PostMapping(path = "/cadastrar")
	public ResponseEntity<?> cadastro(@RequestBody Cliente obj) {
		obj = cs.insert(obj);
		Conta con = new Conta(obj);
		obj.setConta(con);
		con = cons.insert(con);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
}
