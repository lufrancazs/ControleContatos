package br.com.lmf.ControleContatos.exceptions;

public class NomeAndCepDataAlreadyExistsException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public NomeAndCepDataAlreadyExistsException() {
		
	}
	
	public NomeAndCepDataAlreadyExistsException(String mensagem) {
		super(mensagem = "Os Dados do Nome/CEP Já Existem, Informe Nome ou Cep Diferente");
	}

}
