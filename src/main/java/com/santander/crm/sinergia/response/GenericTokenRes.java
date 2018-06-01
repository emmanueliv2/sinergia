package com.santander.crm.sinergia.response;

import org.springframework.http.HttpStatus;
import com.santander.crm.sinergia.entity.Token;

public class GenericTokenRes {

	private Token token;

	private String message;

	private HttpStatus httpStatus;

	public Token getToken() {
		return token;
	}

	public void setToken(Token token) {
		this.token = token;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}

}
