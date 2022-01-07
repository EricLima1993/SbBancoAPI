package br.com.next.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.next.enums.TipoCliente;
import br.com.next.models.entities.CartaoCredito;
import br.com.next.models.repositories.CartaoRepository;

@Service
public class CartaoService {

	@Autowired
	CartaoRepository cr;
	
	public CartaoCredito inserir(CartaoCredito obj) {
		
		return cr.save(obj);	
	}
	
	public CartaoCredito atualizar(CartaoCredito obj) {
		
		return cr.save(obj);	
	}
}
