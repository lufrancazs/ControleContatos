package br.com.lmf.ControleContatos.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class AppException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public ProblemDetail toProblemDetail() {
		
		ProblemDetail pb = ProblemDetail.forStatus(HttpStatus.INTERNAL_SERVER_ERROR);
		
		pb.setTitle("App internal server error");
		
		return pb;
	}

}
