package br.com.alura.avaliacaosprint4.controller.form;

import java.math.BigDecimal;
import java.util.Optional;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.alura.avaliacaosprint4.model.Produto;
import br.com.alura.avaliacaosprint4.repository.ProdutoRepository;

public class ProdutoForm {

	@NotNull @NotEmpty
	private String descricao;
	
	private BigDecimal precoUnitario;
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public void setPrecoUnitario(BigDecimal precoUnitario) {
		this.precoUnitario = precoUnitario;
	}
	
	public Produto converter(ProdutoRepository repository) {
		return new Produto(this.descricao, this.precoUnitario);
	}
	
	public Produto atualizar(Long id, ProdutoRepository produtoRepository) {
		Optional<Produto> produto = produtoRepository.findById(id);
		
		if (produto.isPresent()) {
			Produto produtoEncontrado = produto.get();
			
			produtoEncontrado.setDescricao(this.descricao);
			produtoEncontrado.setPrecoUnitario(this.precoUnitario);
			
			return produtoEncontrado;
		}
		
				
		return null;
	}
	
}
