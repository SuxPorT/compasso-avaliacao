package br.com.alura.avaliacaosprint4.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Pedido {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private BigDecimal total;
	
	private String dataCriacao = LocalDateTime.now().format(
			DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
	
	@ManyToMany(fetch = FetchType.EAGER)
	private List<Produto> produto = new ArrayList<>();
	
	public Long getId() {
		return this.id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public BigDecimal getTotal() {
		return this.total;
	}
	
	public void setTotal(BigDecimal total) {
		this.total = total;
	}
	
	public String getDataCriacao() {
		return this.dataCriacao;
	}
	
	public void setDataCriacao(String dataCriacao) {
		this.dataCriacao = dataCriacao;
	}
	
	public List<Produto> getProduto() {
		return this.produto;
	}
	
	public void setProdutoDto(List<Produto> produto) {
		this.produto = produto;
	}

}
