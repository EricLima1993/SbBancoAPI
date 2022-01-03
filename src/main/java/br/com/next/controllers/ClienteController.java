package br.com.next.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.next.models.entities.Cliente;
import br.com.next.services.ClienteService;

@RestController
@RequestMapping("/principal")
public class ClienteController {

	@Autowired
	ClienteService cs;

	@PostMapping(path = "/buscar")
	public ResponseEntity<?> buscar(@RequestParam int id) {
		Cliente obj = cs.buscar(id);
		return ResponseEntity.ok().body(obj);
	}

}
