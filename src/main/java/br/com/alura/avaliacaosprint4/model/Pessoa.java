package br.com.alura.avaliacaosprint4.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Pessoa {

	@Id
	private String cpf;
	
	private String nome, sexo;
	private BigDecimal salario;
	
	@OneToMany(cascade = CascadeType.REMOVE)
	private List<Endereco> endereco = new ArrayList<>();
	
	public Pessoa() {
	}
	
	public Pessoa(String nome, String cpf, BigDecimal salario, String sexo, 
			List<Endereco> endereco) {
		this.nome     = nome;
		this.cpf      = cpf;
		this.salario  = salario;
		this.sexo     = sexo;
		this.endereco = endereco;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return this.cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public BigDecimal getSalario() {
		return this.salario;
	}

	public void setSalario(BigDecimal salario) {
		this.salario = salario;
	}

	public String getSexo() {
		return this.sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public List<Endereco> getEndereco() {
		return this.endereco;
	}

	public void setEndereco(List<Endereco> endereco) {
		this.endereco = endereco;
	}
	
}
