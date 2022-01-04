package br.com.next.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.next.models.entities.Conta;
import br.com.next.models.repositories.ContaRepository;

@Service
public class ContaService {

	@Autowired
	private ContaRepository cr;
	
	public Conta insert(Conta obj) {
		return cr.save(obj);	
}
}
