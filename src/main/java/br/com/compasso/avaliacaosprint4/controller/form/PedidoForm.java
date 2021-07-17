package br.com.compasso.avaliacaosprint4.controller.form;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.compasso.avaliacaosprint4.model.Pedido;
import br.com.compasso.avaliacaosprint4.model.Produto;
import br.com.compasso.avaliacaosprint4.repository.PedidoRepository;
import br.com.compasso.avaliacaosprint4.repository.ProdutoRepository;

public class PedidoForm {

	@NotNull @NotEmpty
	private List<Long> idProduto = new ArrayList<>();

	public void setIdProduto(List<Long> idProduto) {
		this.idProduto = idProduto;
	}
	
	public List<Long> getIdproduto() {
		return this.idProduto;
	}

	public Pedido converter(ProdutoRepository produtoRepository) {
		Pedido pedido = new Pedido();
		
		relacionarProdutos(pedido, produtoRepository);
		
		return pedido;
	}

	public Pedido atualizar(Long id, PedidoRepository pedidoRepository, 
			ProdutoRepository produtoRepository) {
		
		Optional<Pedido> pedido = pedidoRepository.findById(id);
		
		if (pedido.isPresent()) {
			Pedido pedidoEncontrado = pedido.get();
			pedidoEncontrado.getProduto().clear();
			
			relacionarProdutos(pedidoEncontrado, produtoRepository);
			
			pedidoEncontrado.setDataCriacao(LocalDateTime.now().format(
					DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
		
			return pedidoEncontrado;
		}
		
		return null;
	}
	
	private void relacionarProdutos(Pedido pedido, ProdutoRepository produtoRepository) {
		BigDecimal total = new BigDecimal("0.0");
		Optional<Produto> produto = null;
		
		for (Long id : this.idProduto) {
			produto = produtoRepository.findById(id);
			
			if (produto.isPresent()) {
				Produto produtoEncontrado = produto.get();
				total = total.add(produtoEncontrado.getPrecoUnitario());
				
				pedido.getProduto().add(produtoEncontrado);
			}
		}
		
		pedido.setTotal(total);
	}
	
}
