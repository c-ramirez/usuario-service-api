package com.usuario.demo.controller;

import java.util.ArrayList;
import java.util.List;


public class GenericResponse {
	
	List<String> error;
	String message;
	Object body;

	public GenericResponse() {
		error = new ArrayList<>();
	}

	public GenericResponse(Object body) {
		super();
		this.body = body;
	}

	public List<String> getError() {
		return error;
	}

	public void setError(List<String> error) {
		this.error = error;
	}

	public Object getBody() {
		return body;
	}

	public void setBody(Object body) {
		this.body = body;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
