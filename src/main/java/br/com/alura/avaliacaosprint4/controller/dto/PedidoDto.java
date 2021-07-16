package br.com.alura.avaliacaosprint4.controller.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import br.com.alura.avaliacaosprint4.model.Pedido;
import br.com.alura.avaliacaosprint4.model.Produto;

public class PedidoDto {

	private BigDecimal total = new BigDecimal("0.0");
	private String dataCriacao;
	private List<ProdutoDto> produtoDto = new ArrayList<>();
	
	public PedidoDto(Pedido pedido) {
		this.dataCriacao = pedido.getDataCriacao();
		this.setProdutoDto(pedido);
		this.total = pedido.getTotal();
	}
	
	private void setProdutoDto(Pedido pedido) {
		for (Produto produto : pedido.getProduto()) {
			this.produtoDto.add(new ProdutoDto(produto));
		}
	}

	public BigDecimal getTotal() {
		return this.total;
	}

	public String getDataCriacao() {
		return this.dataCriacao;
	}

	public List<ProdutoDto> getProdutoDto() {
		return this.produtoDto;
	}

	public static List<PedidoDto> converter(List<Pedido> pedidos) {
		return pedidos.stream().map(PedidoDto::new).collect(Collectors.toList());
	}
	
}
