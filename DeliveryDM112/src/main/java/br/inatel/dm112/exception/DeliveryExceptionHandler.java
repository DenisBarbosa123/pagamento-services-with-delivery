package br.inatel.dm112.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ControllerAdvice
public class DeliveryExceptionHandler {
	
	@ExceptionHandler(StatusNotFoundException.class)
	@ResponseBody
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public DeliveryStardardError handlerException(StatusNotFoundException ex) 
	{
		return new DeliveryStardardError(HttpStatus.NOT_FOUND.value(), ex.getMessage(), System.currentTimeMillis());
	}
	
	@ExceptionHandler(OrderNotFoundException.class)
	@ResponseBody
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public DeliveryStardardError handlerException(OrderNotFoundException ex) 
	{
		return new DeliveryStardardError(HttpStatus.NOT_FOUND.value(), ex.getMessage(), System.currentTimeMillis());
	}
}
