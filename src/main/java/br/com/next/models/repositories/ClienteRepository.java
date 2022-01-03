package br.com.next.models.repositories;

import org.springframework.data.repository.CrudRepository;

import br.com.next.models.entities.Cliente;

public interface ClienteRepository extends CrudRepository<Cliente, Integer> {

}
