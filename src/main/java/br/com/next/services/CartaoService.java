package br.com.next.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.next.bo.CartaoBo;
import br.com.next.enums.TipoCliente;
import br.com.next.models.entities.CartaoCredito;
import br.com.next.models.entities.Cliente;
import br.com.next.models.entities.ContaCorrente;
import br.com.next.models.repositories.CartaoRepository;
import br.com.next.services.exceptions.ObjectNotFoundException;

@Service
public class CartaoService {

	@Autowired
	CartaoRepository cr;
	
	CartaoBo cb = new CartaoBo();
	
	public CartaoCredito buscar(Integer id) { 
		Optional<CartaoCredito> obj = cr.findById(id); 
		return obj.orElseThrow(() -> new ObjectNotFoundException( 
				 "Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName())); 
	}
	
	public CartaoCredito inserir(CartaoCredito obj) {
		
		return cr.save(obj);	
	}
	
	public CartaoCredito atualizar(CartaoCredito obj) {
		
		return cr.save(obj);	
	}
	
	public CartaoCredito usar(CartaoCredito obj, @RequestParam double valor) {

		CartaoCredito temp = buscar(obj.getId());
		if (!obj.getSenha().equals(temp.getSenha())) {
			throw new ObjectNotFoundException("Senha invalida!");
		} else {
			obj = temp;
			obj = cb.debitarCredito(obj, valor);
			return cr.save(obj);
		}
	}

	public CartaoCredito pagar(CartaoCredito obj, double valor) {
		CartaoCredito temp = buscar(obj.getId());
		obj = temp;
		obj = cb.pagarCredito(obj, valor);

		return cr.save(obj);
	}
}
