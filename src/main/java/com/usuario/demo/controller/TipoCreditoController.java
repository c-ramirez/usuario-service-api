package com.usuario.demo.controller;

import java.awt.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.usuario.demo.service.TipoCreditoService;
import com.usuario.demo.service.exception.BussinesException;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Api(tags = { "1.0" })
@Path("/v1/credito")
public class TipoCreditoController {
	TipoCreditoService tipoCreditoService;

	public TipoCreditoController() {
		tipoCreditoService = new TipoCreditoService();
	}
	
	@GET
	@Path("")
	@ApiOperation(value = "Lista de tipos de credito", response = List.class)
	public Response obtenerCreditos() {
		GenericResponse response = new GenericResponse();
		try {
			response.setBody(tipoCreditoService.obtenerTiposCredito());
			response.setMessage("Se consiguio la lista de tipos de credito");
			return Response.ok(response).build();
		}catch(BussinesException e) {
			response.getError().add(e.getMessage());
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(response).build();
		}
	}
	
}
