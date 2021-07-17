package br.com.compasso.avaliacaosprint4.controller.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import br.com.compasso.avaliacaosprint4.model.Pessoa;

public class PessoaDto {

	private String nome, cpf;
	private List<EnderecoDto> enderecoDto = new ArrayList<>();
	
	public PessoaDto(Pessoa pessoa) {
		this.nome = pessoa.getNome();
		this.cpf  = pessoa.getCpf();
		this.setEnderecoDto(pessoa);
	}

	private void setEnderecoDto(Pessoa pessoa) {
		this.enderecoDto.addAll(
				pessoa.getEndereco()
					  .stream()
					  .map(EnderecoDto::new)
					  .collect(Collectors.toList())
		);
	}

	public String getNome() {
		return this.nome;
	}
	
	public String getCpf() {
		return this.cpf;
	}
	
	public List<EnderecoDto> getEnderecoDto() {
		return this.enderecoDto;
	}

	public static List<PessoaDto> converter(List<Pessoa> pessoas) {
		return pessoas.stream().map(PessoaDto::new).collect(Collectors.toList());
	}
	
}
