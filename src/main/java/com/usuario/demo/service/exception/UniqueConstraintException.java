package com.usuario.demo.service.exception;

public class UniqueConstraintException extends Exception{

	private static final long serialVersionUID = 1L;

	public UniqueConstraintException() {
		super();
	}

	public UniqueConstraintException(String message) {
		super(message);
	}
	
	

}
