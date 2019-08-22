package com.hrong.major.controller.common;

import com.hrong.major.model.vo.Result;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * @Author hrong
 **/
@ControllerAdvice
public class ValidErrorController extends ResponseEntityExceptionHandler {

	@Override
	protected ResponseEntity<Object> handleBindException(
			BindException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		Result result = new Result();
		for(ObjectError error:ex.getBindingResult().getFieldErrors()){
			result.setCode(400);
			result.setMessage(error.getDefaultMessage());
		}
		return new ResponseEntity<>(result, status);
	}

}
