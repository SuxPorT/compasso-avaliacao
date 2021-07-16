package br.com.alura.avaliacaosprint4.controller.form;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import br.com.alura.avaliacaosprint4.model.Endereco;
import br.com.alura.avaliacaosprint4.model.Pessoa;
import br.com.alura.avaliacaosprint4.repository.EnderecoRepository;
import br.com.alura.avaliacaosprint4.repository.PessoaRepository;

public class PessoaForm {

	@NotNull @NotEmpty @Length(min = 3, max = 50)
	private String nome;
	
	@NotNull @NotEmpty @Length(min = 11, max = 11)
	private String cpf;
	
	@NotNull @DecimalMin(value = "1100.00") @DecimalMax(value = "50000.00")
	private BigDecimal salario;
	
	@NotNull @NotEmpty @Length(min = 1, max = 1)
	private String sexo;
	
	@NotNull @NotEmpty
	private List<Endereco> endereco = new ArrayList<>();
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	public void setSalario(BigDecimal salario) {
		this.salario = salario;
	}
	
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	
	public void setEndereco(List<Endereco> endereco) {
		this.endereco = endereco;
	}
	
	public String getCpf() {
		return this.cpf;
	}

	public Pessoa save(PessoaRepository pessoaRepository, 
			EnderecoRepository enderecoRepository) {

		for (Endereco endereco : endereco) {
			enderecoRepository.save(endereco);
		}

		Pessoa pessoa = new Pessoa(this.nome, this.cpf, this.salario, this.sexo,
				this.endereco);
		
		pessoaRepository.save(pessoa);

		return pessoa;
	}
	
}
