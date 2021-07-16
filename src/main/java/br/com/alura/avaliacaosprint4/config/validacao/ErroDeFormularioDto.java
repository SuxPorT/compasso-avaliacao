package br.com.alura.avaliacaosprint4.config.validacao;

public class ErroDeFormularioDto {

	private String campo, erro;

	public ErroDeFormularioDto(String campo, String erro) {
		this.campo = campo;
		this.erro = erro;
	}

	public String getCampo() {
		return campo;
	}

	public String getErro() {
		return erro;
	}
	
}
