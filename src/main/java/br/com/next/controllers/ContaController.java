package br.com.next.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.next.models.entities.Conta;
import br.com.next.services.ContaService;

@RestController
@RequestMapping("/conta")
public class ContaController {

	@Autowired
	private ContaService cs;
	
	@PutMapping(path="/saque")
	public ResponseEntity<Void> sacar(@RequestParam int numCon,@RequestParam String senha, @RequestParam double saque) {
		cs.sacar(numCon, senha, saque);	
		
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping(path="/deposito")
	public ResponseEntity<Void> depositar(@RequestParam int numCon, @RequestParam double dep) {
		cs.depositar(numCon,dep);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping(path="/transferir")
	public ResponseEntity<Void> transferir(@RequestParam int numCon,@RequestParam String senha, @RequestParam int numConT, @RequestParam double vTrans) {
		cs.transferir(numCon,senha,numConT,vTrans);
		return ResponseEntity.noContent().build();
	}
	
}
