package br.com.alura.avaliacaosprint4.controller.form;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import br.com.alura.avaliacaosprint4.model.Endereco;
import br.com.alura.avaliacaosprint4.model.Pessoa;
import br.com.alura.avaliacaosprint4.repository.EnderecoRepository;
import br.com.alura.avaliacaosprint4.repository.PessoaRepository;

public class AtualizacaoPessoaForm {

	@NotNull @NotEmpty @Length(min = 3, max = 50)
	private String nome;
	
	@NotNull @DecimalMin(value = "1100.00") @DecimalMax(value = "50000.00")
	private BigDecimal salario;
	
	@NotNull @NotEmpty @Length(min = 1, max = 1)
	private String sexo;
	
	@NotNull @NotEmpty
	private List<Endereco> endereco = new ArrayList<>();

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
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

	public Pessoa atualizar(String cpf, PessoaRepository pessoaRepository,
			EnderecoRepository enderecoRepository) {
		Optional<Pessoa> pessoa = pessoaRepository.findById(cpf);
		
		if (pessoa.isPresent()) {
			Pessoa pessoaEncontrada = pessoa.get();
			
			pessoaEncontrada.setNome(this.nome);
			pessoaEncontrada.setSalario(this.salario);
			pessoaEncontrada.setSexo(this.sexo);
			
			// Ocorre Exception ao tentar mudar os dados com CPF diferente
			// Erro de chave primária/estrangeira
			pessoaEncontrada.setEndereco(this.endereco);
			
			return pessoaEncontrada;
		}
		
		return null;
	}
	
}
