package br.com.next.models.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.next.models.entities.ContaPoupanca;

@Repository
public interface ContaPoupancaRepository extends JpaRepository<ContaPoupanca, Integer>{

}
