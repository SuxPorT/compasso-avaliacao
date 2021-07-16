package br.com.alura.avaliacaosprint4.controller.form;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.alura.avaliacaosprint4.model.Pedido;
import br.com.alura.avaliacaosprint4.model.Produto;
import br.com.alura.avaliacaosprint4.repository.PedidoRepository;
import br.com.alura.avaliacaosprint4.repository.ProdutoRepository;

public class PedidoForm {

	@NotNull @NotEmpty
	private List<Long> idProduto = new ArrayList<>();

	public void setIdProduto(List<Long> idProduto) {
		this.idProduto = idProduto;
	}
	
	public List<Long> getIdproduto() {
		return this.idProduto;
	}

	public Pedido converter(PedidoRepository pedidoRepository, 
			ProdutoRepository produtoRepository) {
		BigDecimal total = new BigDecimal("0.0");
		Pedido pedido = new Pedido();
		Optional<Produto> produto = null;
		
		for (Long id : idProduto) {
			produto = produtoRepository.findById(id);
			
			if (produto.isPresent()) {
				Produto produtoEncontrado = produto.get();
				total = total.add(produtoEncontrado.getPrecoUnitario());
				
				pedido.getProduto().add(produtoEncontrado);
			}
		}
		
		pedido.setTotal(total);
		
		return pedido;
	}

	public Pedido atualizar(Long id, PedidoRepository pedidoRepository) {
		Optional<Pedido> pedido = pedidoRepository.findById(id);
		
		if (pedido.isPresent()) {
			Pedido pedidoEncontrado = pedido.get();
			
			// Código
		
			return pedidoEncontrado;
		}
		
		return null;
	}
	
}
