package br.com.compasso.avaliacaosprint4.controller.dto;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import br.com.compasso.avaliacaosprint4.model.Produto;

public class ProdutoDto {

	private Long id;
	private String descricao;
	private BigDecimal precoUnitario;
	
	public ProdutoDto(Produto produto) {
		this.id            = produto.getId();
		this.descricao     = produto.getDescricao();
		this.precoUnitario = produto.getPrecoUnitario();
	}
	
	public Long getId() {
		return this.id;
	}
	
	public String getDescricao() {
		return this.descricao;
	}
	
	public BigDecimal getPrecoUnitario() {
		return this.precoUnitario;
	}

	public static List<ProdutoDto> converter(List<Produto> produtos) {
		return produtos.stream().map(ProdutoDto::new).collect(Collectors.toList());
	}
	
}
