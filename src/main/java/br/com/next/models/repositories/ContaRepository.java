package br.com.next.models.repositories;

import org.springframework.data.repository.CrudRepository;

import br.com.next.models.entities.Conta;

public interface ContaRepository extends CrudRepository<Conta, Integer> {

}
