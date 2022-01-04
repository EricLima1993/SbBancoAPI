package br.com.next.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.next.models.entities.Cliente;
import br.com.next.models.repositories.ClienteRepository;
import br.com.next.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository cr;

	public Cliente buscar(Integer id) { 
		Optional<Cliente> obj = cr.findById(id); 
		return obj.orElseThrow(() -> new ObjectNotFoundException( 
				 "Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName())); 
	}
	
}
