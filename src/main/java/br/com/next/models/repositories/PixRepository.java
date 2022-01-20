package br.com.next.models.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.next.models.entities.Pix;

@Repository
public interface PixRepository  extends JpaRepository<Pix, Integer> {

	@Query("SELECT DISTINCT obj FROM Pix obj WHERE obj.codigo LIKE %:codigo%")
	Optional<Pix> searchForCode(@Param("codigo")String codigo);
}
