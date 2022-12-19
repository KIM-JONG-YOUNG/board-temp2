package edu.jong.board.client.handler;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import edu.jong.board.client.exception.DataAlreadyExistsException;
import edu.jong.board.client.exception.DataNotFoundException;
import edu.jong.board.client.exception.DataStateDeactiveException;
import edu.jong.board.client.response.ErrorMessage;
import edu.jong.board.client.response.ErrorMessage.ErrorMessageBuilder;

@RestControllerAdvice
public class ErrorMessageHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(Exception.class)
	ResponseEntity<ErrorMessage> handleCutomException(Exception e) {
		
		HttpStatus status = null;
		ErrorMessageBuilder builder = ErrorMessage.builder();
		
		builder.errorClass(e.getClass());
		builder.message(e.getMessage());
		
		if (e instanceof DataAlreadyExistsException) {
			status = HttpStatus.CONFLICT;
		} else if (e instanceof DataNotFoundException) {
			status = HttpStatus.NOT_FOUND;
		} else if (e instanceof DataStateDeactiveException) {
			status = HttpStatus.GONE;
		} else {
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			e.printStackTrace();
		}
		
		
		return ResponseEntity.status(status)
				.body(builder.build());
	}
	
	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		
		ErrorMessage errorMessage = ErrorMessage.builder()
				.errorClass(ex.getClass())
				.message(ex.getMessage())
				.build();
		
		return super.handleExceptionInternal(ex, errorMessage, headers, status, request);
	}
	
}
