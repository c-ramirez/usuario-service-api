package com.usuario.demo.controller;

import java.util.HashMap;
import java.util.Map;

import javax.validation.ConstraintViolationException;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class RequestException implements ExceptionMapper<ConstraintViolationException> {

	
	@Produces("application/json")
	public Response toResponse(ConstraintViolationException exception) {
		Map<String, String> errores = new HashMap<>();
		exception.getConstraintViolations().stream().forEach(error -> {
			String name = error.getPropertyPath().toString();
			errores.put(name.substring(name.lastIndexOf(".") + 1), error.getMessage());
		});
		return Response.status(Response.Status.BAD_REQUEST).entity(errores).build();
	}

}
