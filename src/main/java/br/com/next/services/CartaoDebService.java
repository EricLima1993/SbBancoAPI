package br.com.next.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.next.models.entities.CartaoDebito;
import br.com.next.models.repositories.CartaoDebRepository;
import br.com.next.services.exceptions.ObjectNotFoundException;

@Service
public class CartaoDebService {

	@Autowired
	public CartaoDebRepository cdr;
	
	public CartaoDebito buscar(Integer id) { 
		Optional<CartaoDebito> obj = cdr.findById(id); 
		return obj.orElseThrow(() -> new ObjectNotFoundException( 
				 "Objeto não encontrado! Id: " + id + ", Tipo: " + CartaoDebito.class.getName())); 
	}
	
	public CartaoDebito inserir(CartaoDebito obj) {
		return cdr.save(obj);	
	}
	
	public void deletar(Integer id) { 
		cdr.deleteById(id);
	}
}
