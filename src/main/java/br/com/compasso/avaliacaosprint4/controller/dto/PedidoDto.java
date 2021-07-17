package br.com.compasso.avaliacaosprint4.controller.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import br.com.compasso.avaliacaosprint4.model.Pedido;

public class PedidoDto {

	private BigDecimal total = new BigDecimal("0.0");
	private String dataCriacao;
	private List<ProdutoDto> produtoDto = new ArrayList<>();
	
	public PedidoDto(Pedido pedido) {
		this.total = pedido.getTotal();
		this.dataCriacao = pedido.getDataCriacao();
		this.setProdutoDto(pedido);
	}
	
	private void setProdutoDto(Pedido pedido) {
		this.produtoDto.addAll(
				pedido.getProduto()
					  .stream()
					  .map(ProdutoDto::new)
					  .collect(Collectors.toList())
		);
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
