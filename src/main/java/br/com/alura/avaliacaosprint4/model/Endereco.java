package br.com.alura.avaliacaosprint4.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Entity
public class Endereco {

	@NotBlank
	private String pais;
	
	@NotBlank
	private String estado;
	
	@NotBlank
	private String cidade;
	
	@Id @NotBlank
	private String cep;
	
	@NotBlank
	private String rua;
	
	public Endereco() {
	}
	
	public Endereco(String pais, String estado, String cidade, 
			String cep, String rua) {
		this.pais   = pais;
		this.estado = estado;
		this.cidade = cidade;
		this.cep    = cep;
		this.rua    = rua;
	}

	public String getPais() {
		return this.pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getEstado() {
		return this.estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getCidade() {
		return this.cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getRua() {
		return this.rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}
	
}
