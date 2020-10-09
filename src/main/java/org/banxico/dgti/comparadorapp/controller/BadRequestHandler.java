package org.banxico.dgti.comparadorapp.controller;

import javax.validation.ConstraintViolationException;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class BadRequestHandler implements ExceptionMapper<ConstraintViolationException> {

	@Produces("application/json")
	public Response toResponse(ConstraintViolationException exception) {
		GenericResponse response = new GenericResponse();
		exception.getConstraintViolations().stream().forEach(error -> response.getError().add(error.getMessage()));
		return Response.status(Response.Status.BAD_REQUEST).entity(response).build();
	}

}
