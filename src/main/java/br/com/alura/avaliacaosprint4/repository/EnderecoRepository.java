package br.com.alura.avaliacaosprint4.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.alura.avaliacaosprint4.model.Endereco;

public interface EnderecoRepository extends JpaRepository<Endereco, String> {

}
