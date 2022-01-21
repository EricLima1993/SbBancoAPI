package br.com.next.models.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.next.models.entities.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
	
	@Query("SELECT DISTINCT obj FROM Cliente obj INNER JOIN obj.conta con WHERE obj.cpf LIKE %:cpf% AND obj.senha LIKE %:senha%")
	Optional<Cliente> search(@Param("cpf")String cpf, @Param("senha")String senha);
	
	@Query("SELECT DISTINCT obj FROM Cliente obj INNER JOIN obj.conta con WHERE obj.senha LIKE %:senha%")
	Optional<Cliente> searchForPass(@Param("senha")String senha);

	@Query("SELECT DISTINCT obj FROM Cliente obj WHERE obj.chave LIKE %:chave%")
	Optional<Cliente> searchForKey(@Param("chave")String chave);
}
