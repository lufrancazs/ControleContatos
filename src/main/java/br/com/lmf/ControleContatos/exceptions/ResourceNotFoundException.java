package br.com.lmf.ControleContatos.exceptions;

public class ResourceNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public ResourceNotFoundException() {
		
	}
	
	public ResourceNotFoundException(Object id) {
		super("Recurso não encontrado: " + id);
	}	

}
