package br.com.next.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.next.enums.TipoCliente;
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
	
	public Cliente inserir(Cliente obj) {
			obj.setTipo(TipoCliente.COMUM.toString());		
			return cr.save(obj);	
	}
	
	public Cliente atualizar(Cliente obj) {
		buscar(obj.getId());
		return cr.save(obj);
	}
	
	public void deletar(Integer id) { 
		cr.deleteById(id);
	}
	
	
	public Cliente logar(String cpf,String senha) {
		Optional<Cliente> obj = cr.search(cpf,senha); 
		return obj.orElseThrow(() -> new ObjectNotFoundException("Senha ou cpf invalido")); 
	}
	/*public Page<Cliente> buscarPagina(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pr = PageRequest.of(page,linesPerPage,Direction.valueOf(direction),orderBy);
		return cr.findAll(pr);
	}*/
}
