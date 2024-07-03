package br.com.lmf.ControleContatos.exceptions;

public class CepNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public CepNotFoundException() {
		
	}

	public CepNotFoundException(String mensagem) {
		super(mensagem = "Não foi encontrado endereço para o CEP informado");
	}
	
	


	

}
