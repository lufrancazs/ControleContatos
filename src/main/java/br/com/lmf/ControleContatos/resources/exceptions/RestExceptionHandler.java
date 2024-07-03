package br.com.lmf.ControleContatos.resources.exceptions;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.lmf.ControleContatos.exceptions.AppException;
import br.com.lmf.ControleContatos.exceptions.CepNotFoundException;
import br.com.lmf.ControleContatos.exceptions.NomeAndCepDataAlreadyExistsException;
import br.com.lmf.ControleContatos.exceptions.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class RestExceptionHandler {
	
	@ExceptionHandler(AppException.class)
	public ProblemDetail handlerAppException(AppException e) {
		return e.toProblemDetail();
	}
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<StandardError> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request){
		
		String error = "Recurso Não Encontrado";
		HttpStatus status = HttpStatus.NOT_FOUND;
		StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}
	
	@ExceptionHandler(CepNotFoundException.class)
	public ResponseEntity<StandardError> cepNotFound(CepNotFoundException e, HttpServletRequest request){
		
		String error = "CEP Não Encontrado";
		HttpStatus status = HttpStatus.NOT_FOUND;
		StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}
	
	@ExceptionHandler(NomeAndCepDataAlreadyExistsException.class)
	public ResponseEntity<StandardError> nomeAndCepExists(NomeAndCepDataAlreadyExistsException e, HttpServletRequest request){
		
		String error = "Nome e CEP Já Existem";
		HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
		StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}

}
