package br.com.alura.avaliacaosprint4.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.alura.avaliacaosprint4.model.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, String> {

}
