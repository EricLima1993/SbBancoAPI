package br.com.next.controllers;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.next.enums.TipoCliente;
import br.com.next.models.entities.Cliente;
import br.com.next.models.entities.Conta;
import br.com.next.models.repositories.ClienteRepository;
import br.com.next.models.repositories.ContaRepository;

@RestController
@RequestMapping("/principal")
public class ClienteController {

	@Autowired
	private ClienteRepository cr;
	@Autowired
	private ContaRepository conR;

	@PostMapping(path = "/cadastro")
	public Cliente novoCliente(@Valid @RequestParam String nome, String cpf, String senha) {
		Cliente cli = new Cliente(nome,cpf, senha);
		Conta con;
		cli.setTipo(TipoCliente.COMUM.toString());
		cr.save(cli);
		con = new Conta(0,cli);
		conR.save(con);
		return cli;
	}
	
	/*@PostMapping(path = "/login")
	public Optional<Conta> entrar(@Valid @RequestParam String cpf, String senha) {
		Conta con = new Conta();
		Cliente cli = new Cliente(cpf, senha);
		return Optional<con>;
	}*/
}
