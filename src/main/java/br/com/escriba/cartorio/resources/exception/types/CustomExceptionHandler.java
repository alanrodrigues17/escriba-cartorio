package br.com.escriba.cartorio.resources.exception.types;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import jakarta.validation.ConstraintViolationException;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {
	
	@org.springframework.web.bind.annotation.ExceptionHandler(Error.class)
	public ResponseEntity exceptions(Exception e) {
		
		ApiErrorMessage apiErrorMessage = new ApiErrorMessage(HttpStatus.BAD_REQUEST.value(), "Registro já cadastrado!");
		return new ResponseEntity(apiErrorMessage, HttpStatus.BAD_REQUEST);
	}
	
	@org.springframework.web.bind.annotation.ExceptionHandler(NullPointerException.class)
	public ResponseEntity handlerException(Exception e) {
		
		ApiErrorMessage apiErrorMessage = new ApiErrorMessage(HttpStatus.NOT_FOUND.value(), "Registro não encontrado na base de dados!");

		return new ResponseEntity(apiErrorMessage, HttpStatus.NOT_FOUND);
	}
	
	@org.springframework.web.bind.annotation.ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity handlerExceptions(Exception e) {
		
		ApiErrorMessage apiErrorMessage = new ApiErrorMessage(HttpStatus.UNAUTHORIZED.value(), "Registro utilizado em outro cadastro!");
		return new ResponseEntity(apiErrorMessage, HttpStatus.UNAUTHORIZED);
	}
	
	

}
