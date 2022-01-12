package br.com.next.models.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.next.models.entities.Pix;

@Repository
public interface PixRepository  extends JpaRepository<Pix, Integer> {

}
