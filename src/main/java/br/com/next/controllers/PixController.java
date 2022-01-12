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

import br.com.next.bo.PixBo;
import br.com.next.models.entities.Cliente;
import br.com.next.models.entities.Pix;
import br.com.next.services.ClienteService;
import br.com.next.services.ContaService;
import br.com.next.services.PixService;

@RestController
@RequestMapping("/pix")
public class PixController {
	
	@Autowired
	private PixService ps;
	@Autowired
	private ClienteService cs;
	
	@Autowired
	private ContaService cons;
	
	private PixBo pb = new PixBo();
	
	@PostMapping(path = "/gerar")
	public ResponseEntity<?> gerar(@RequestParam int id,@RequestParam double valor){
		Pix p = new Pix();
		p.setIdRecebe(id);
		p.setValor(valor);
		p = ps.inserir(p);
		
		p.setCodigo(pb.gerarCodigo(p.getId()));
		
		ps.inserir(p);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(p.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping(path = "/pagar")
	public ResponseEntity<?> pagar(@RequestParam int idPix, @RequestParam int idPagante){
		Cliente cliP = new Cliente();
		Cliente cliR = new Cliente();
		Pix p = new Pix();
		
		p = ps.buscar(idPix);
		cliR = cs.buscar(p.getIdRecebe());
		cliP = cs.buscar(idPagante);
		
		cons.transferir(cliP.getConta().getNumeroConta(),cliP.getSenha(),cliR.getConta().getNumeroConta(),p.getValor());
		
		ps.deletar(p.getId());
		return ResponseEntity.noContent().build();
	}
	
}
