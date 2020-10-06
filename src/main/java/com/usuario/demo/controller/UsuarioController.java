package com.usuario.demo.controller;

import java.util.List;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.usuario.demo.repository.tipocredito.TipoCredito;
import com.usuario.demo.repository.usuario.Usuario;
import com.usuario.demo.service.UsuarioService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Api(tags = { "1.0" })
@Path("/v1")
public class UsuarioController {
	UsuarioService usuarioService;

	public UsuarioController() {
		usuarioService = new UsuarioService();
	}

	@GET
	@Path("/usuario/test/{nombre}")
	public Response test(@PathParam("nombre") String nombre) {
		return Response.ok("Test Variable ::" + nombre).build();
	}

	@POST
	@Path("/usuario/login")
	@ApiOperation(value = "Login de usuario", 
		notes = "Campos obligatorios : usuario, clave", 
		response = Usuario.class)
	public Response login(@Valid Usuario usuario) {
		return Response.ok(usuarioService.login(usuario)).build();
	}

	@GET
	@Path("/credito")
	public Response tiposCredito() {
		GenericEntity<List<TipoCredito>> entity = new GenericEntity<List<TipoCredito>>(
				usuarioService.obtenerTiposCredito()) {
		};
		return Response.accepted(entity).build();
	}
}
