package io.github.Speciallist.vendasapi.model.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import io.github.Speciallist.vendasapi.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

	@Query("SELECT c FROM Cliente c WHERE UPPER(c.nome) LIKE UPPER(:nome) AND c.cpf LIKE UPPER(:cpf) ")
	Page<Cliente> buscarPorNomeCPF(@Param("nome") String nome, @Param("cpf") String cpf, Pageable pageable);

}
