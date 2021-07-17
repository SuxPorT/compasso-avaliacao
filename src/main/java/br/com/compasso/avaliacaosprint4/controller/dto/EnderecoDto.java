package br.com.compasso.avaliacaosprint4.controller.dto;

import br.com.compasso.avaliacaosprint4.model.Endereco;

public class EnderecoDto {

	private String cidade, rua;
	
	public EnderecoDto(Endereco endereco) {
		this.cidade = endereco.getCidade();
		this.rua    = endereco.getRua();
	}
	
	public String getCidade() {
		return this.cidade;
	}
	
	public String getRua() {
		return this.rua;
	}
	
}
