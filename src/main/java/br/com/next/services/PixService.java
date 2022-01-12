package br.com.next.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.next.models.entities.Pix;
import br.com.next.models.repositories.PixRepository;
import br.com.next.services.exceptions.ObjectNotFoundException;

@Service
public class PixService {

	@Autowired
	private PixRepository pr;
	
	public Pix buscar(Integer id) { 
		Optional<Pix> obj = pr.findById(id); 
		return obj.orElseThrow(() -> new ObjectNotFoundException( 
				 "Objeto não encontrado! Id: " + id + ", Tipo: " + Pix.class.getName())); 
	}
	
	public Pix inserir(Pix obj) {
		return pr.save(obj);
	}
	
	public void deletar(Integer id) { 
		pr.deleteById(id);
	}
}
