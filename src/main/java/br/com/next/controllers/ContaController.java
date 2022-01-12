package br.com.next.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.next.bo.ClienteBo;
import br.com.next.models.entities.Cliente;
import br.com.next.services.ClienteService;
import br.com.next.services.ContaService;

@RestController
@RequestMapping("/conta")
public class ContaController {

	@Autowired
	private ContaService cs;
	@Autowired
	private ClienteService clis;
	
	@PutMapping(path="/saque")
	public ResponseEntity<Void> sacar(@RequestParam int id,@RequestParam int numCon,@RequestParam String senha, @RequestParam double saque) {
		cs.sacar(numCon, senha, saque);	
		
		atualizarTipo(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping(path="/deposito")
	public ResponseEntity<Void> depositar(@RequestParam int id, @RequestParam int numCon, @RequestParam double dep) {
		cs.depositar(numCon,dep);
		
		atualizarTipo(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping(path="/transferir")
	public ResponseEntity<Void> transferir(@RequestParam int idP,@RequestParam int id, @RequestParam int numCon,@RequestParam String senha, @RequestParam int numConT, @RequestParam double vTrans) {
		cs.transferir(numCon,senha,numConT,vTrans);
		
		atualizarTipo(idP);
		atualizarTipo(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping(path="/cartaodeb")
	public ResponseEntity<Void> usarCartaoDebito(@RequestParam int id,@RequestParam int numCon,@RequestParam String senha, @RequestParam double uso) {
		cs.sacar(numCon, senha, uso);	
		
		atualizarTipo(id);
		return ResponseEntity.noContent().build();
	}
	
	public void atualizarTipo(int id) {
		Cliente cli = new Cliente();
		
		cli = clis.buscar(id);
		
		cli.setTipo(ClienteBo.verificarTipo(cli.getConta().getSaldo()).toString());
		
		clis.atualizar(cli);
	}
	
}
