package br.com.next.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.next.models.entities.Cliente;
import br.com.next.models.repositories.ClienteRepository;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository cr;

	public Cliente buscar(Integer id) { 
		 Optional<Cliente> obj = cr.findById(id); 
		return obj.orElse(null); 
		} 

}
