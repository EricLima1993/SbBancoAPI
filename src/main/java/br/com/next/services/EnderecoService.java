package br.com.next.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.next.models.entities.Endereco;
import br.com.next.models.repositories.EnderecoRepository;
import br.com.next.services.exceptions.ObjectNotFoundException;

@Service
public class EnderecoService {

	@Autowired
	private EnderecoRepository er;
	
	public Endereco buscar(Integer id) { 
		Optional<Endereco> obj = er.findById(id); 
		return obj.orElseThrow(() -> new ObjectNotFoundException( 
				 "Objeto não encontrado! Id: " + id + ", Tipo: " + Endereco.class.getName())); 
	}

	public Endereco inserir(Endereco obj) {
		return er.save(obj);
	}
	
	public Endereco atualizar(Endereco obj) {
		buscar(obj.getId());
		return er.save(obj);
	}
	
	public void deletar(Integer id) { 
		er.deleteById(id);
	}
}
