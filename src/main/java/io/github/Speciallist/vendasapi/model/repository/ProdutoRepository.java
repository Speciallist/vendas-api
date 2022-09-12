package io.github.Speciallist.vendasapi.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.Speciallist.vendasapi.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

}
