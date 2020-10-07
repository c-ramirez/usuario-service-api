package com.usuario.demo.controller;

import java.util.List;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
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
	@Path("/test/{nombre}")
	public Response test(@PathParam("nombre") String nombre) {
		return Response.ok("Test Variable ::" + nombre).build();
	}

	@POST
	@Path("/usuario/login")
	@ApiOperation(value = "Login de usuario", notes = "Campos obligatorios : usuario, clave", response = Usuario.class)
	public Response login(@Valid Usuario usuario) {
		return Response.ok(usuarioService.login(usuario)).build();
	}
	@POST
	@Path("/usuario")
	@ApiOperation(value = "Creacion de usuario", response = Usuario.class)
	public Response creacionUsuario(@Valid Usuario usuario) {
		usuarioService.crearUsuario(usuario);
		return Response.ok().build();
	}
	@PUT
	@Path("/usuario")
	@ApiOperation(value = "Creacion de usuario", response = Usuario.class)
	public Response actualizacionUsuario(@Valid Usuario usuario) {
		usuarioService.actualizarUsuario(usuario);
		return Response.ok().build();
	}

	@GET
	@Path("/credito")
	public Response tiposCredito() {
		GenericEntity<List<TipoCredito>> entity = new GenericEntity<List<TipoCredito>>(
				usuarioService.obtenerTiposCredito()) {
		};
		return Response.accepted(entity).build();
	}
	
	@GET
	@Path("/usuario/{id}")
	@ApiOperation(value = "Creacion de usuario", response = Usuario.class)
	public Response creacionUsuario(@PathParam("id")Integer id) {
		return Response.ok(usuarioService.obtenerUsuario(id)).build();
	}
	@DELETE
	@Path("/usuario/{id}")
	@ApiOperation(value = "Creacion de usuario", response = Usuario.class)
	public Response eliminarUsuario(@PathParam("id")Integer id) {
		usuarioService.eliminarUsuario(id);
		return Response.ok().build();
	}
}
