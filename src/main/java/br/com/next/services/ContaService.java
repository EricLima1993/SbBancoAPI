package br.com.next.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.next.models.entities.Conta;
import br.com.next.models.repositories.ContaRepository;
import br.com.next.services.exceptions.DataIntegrityException;
import br.com.next.services.exceptions.ObjectNotFoundException;

@Service
public class ContaService {

	@Autowired
	private ContaRepository cr;
	
	public Conta buscar(Integer id) { 
		Optional<Conta> obj = cr.findById(id); 
		return obj.orElseThrow(() -> new ObjectNotFoundException( 
				 "Objeto não encontrado! Id: " + id + ", Tipo: " + Conta.class.getName())); 
	}

	public Conta inserir(Conta obj) {
		return cr.save(obj);
	}
	
	public void deletar(Integer id) { 
		buscar(id);
		try {
			cr.deleteById(id);
		}catch(DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel excluir um conta que sem excluir o cadastro do cliente primeiro");
		}
	}
}
