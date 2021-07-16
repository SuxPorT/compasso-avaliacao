package br.com.alura.avaliacaosprint4.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.alura.avaliacaosprint4.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

}
