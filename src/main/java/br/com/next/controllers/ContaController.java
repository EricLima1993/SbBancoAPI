package br.com.next.controllers;

import java.net.URI;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.next.models.entities.Cliente;
import br.com.next.models.entities.Conta;
import br.com.next.models.repositories.ContaRepository;

@RestController
@RequestMapping("/conta")
public class ContaController {

	@Autowired
	private ContaRepository cr;
	
	@PutMapping(path="/saque")
	public Conta sacar(@RequestParam Conta con, double saque) {
		con.setSaldo(con.getSaldo() - saque);
		cr.save(con);
		return con;
	}
	
	@PutMapping(path="/deposito")
	public Conta depositar(@RequestParam int num, double dep) {
		Conta con = new Conta();
		Optional<Conta> opCon = cr.findById(num);
		if(opCon.isPresent()) {
			con = opCon.get();
			con.setSaldo(con.getSaldo() + dep);
			cr.save(con);
		}
		return con;
	}
	
}
