package br.com.next.models.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.next.models.entities.CartaoCredito;

@Repository
public interface CartaoRepository extends JpaRepository<CartaoCredito, Integer>{

}
