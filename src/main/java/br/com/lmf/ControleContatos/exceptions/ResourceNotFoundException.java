package br.com.lmf.ControleContatos.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class ResourceNotFoundException extends AppException{

	private static final long serialVersionUID = 1L;
	
	private Long resourceId;
	
	public ResourceNotFoundException(String message) {

	}

	public ResourceNotFoundException(Long resourceId) {
		this.resourceId = resourceId;
	}
	
	@Override
	public ProblemDetail toProblemDetail() {
		ProblemDetail pb = ProblemDetail.forStatus(HttpStatus.UNPROCESSABLE_ENTITY);
		
		pb.setTitle("Recurso Não Encontrado");
		pb.setDetail("Não existe o recurso com o id: " + resourceId + ".");
		
		return pb;
	}

}
