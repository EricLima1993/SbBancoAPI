package br.com.next.controllers;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.next.models.entities.Cliente;
import br.com.next.models.entities.ContaPoupanca;
import br.com.next.services.ClienteService;
import br.com.next.services.ContaPoupancaService;

@RestController
@RequestMapping("/contapoupanca")
public class ContaPoupancaController {

	@Autowired
	private ContaPoupancaService cps;
	@Autowired
	private ClienteService clis;
	
	@PostMapping(path = "/solicitar")
	public ResponseEntity<?> cadastro(@RequestParam int id) {	
		Cliente cli = clis.buscar(id);
		
		ContaPoupanca obj = new ContaPoupanca(cli);
		cli.setContaP(obj);
		cps.inserir(obj);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getNumeroConta()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping(path="/saque")
	public ResponseEntity<Void> sacar(@RequestParam int id,@RequestParam int numCon,@RequestParam String senha, @RequestParam double saque) {
		cps.sacar(numCon, senha, saque);	
		
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping(path="/deposito")
	public ResponseEntity<Void> depositar(@RequestParam int id, @RequestParam int numCon, @RequestParam double dep) {
		cps.depositar(numCon,dep);
		

		return ResponseEntity.noContent().build();
	}
	
	@PutMapping(path="/transferir")
	public ResponseEntity<Void> transferir(@RequestParam int idP,@RequestParam int id, @RequestParam int numCon,@RequestParam String senha, @RequestParam int numConT, @RequestParam double vTrans) {
		cps.transferir(numCon,senha,numConT,vTrans);
		
		return ResponseEntity.noContent().build();
	}
}
