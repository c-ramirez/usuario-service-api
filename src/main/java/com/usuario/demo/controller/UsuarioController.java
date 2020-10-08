package com.usuario.demo.controller;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.usuario.demo.repository.usuario.Usuario;
import com.usuario.demo.service.UsuarioService;
import com.usuario.demo.service.exception.BussinesException;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Api(tags = { "1.0" })
@Path("/v1/usuario")
public class UsuarioController {
	UsuarioService usuarioService;

	public UsuarioController() {
		usuarioService = new UsuarioService();
	}

	@POST
	@Path("/login")
	@ApiOperation(value = "Login de usuario", notes = "Campos obligatorios : usuario, clave", response = GenericResponse.class)
	public Response login(@Valid Usuario usuario) {
		GenericResponse response = new GenericResponse();
		try {
			response.setBody(usuarioService.login(usuario));
			response.setMessage("El usuario se logeo correctamente");
			return Response.ok(response).build();
		} catch (BussinesException e) {
			response.getError().add(e.getMessage());
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(response).build();
		}
	}

	@POST
	@Path("")
	@ApiOperation(value = "Creacion de usuario", response = GenericResponse.class)
	public Response creacionUsuario(@Valid Usuario usuario) {
		GenericResponse response = new GenericResponse();
		try {
			usuarioService.crearUsuario(usuario);
			response.setMessage("Se creó el usuario correctamente");
			return Response.ok(response).build();
		} catch (BussinesException e) {
			response.getError().add(e.getMessage());
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(response).build();
		}
	}

	@PUT
	@Path("")
	@ApiOperation(value = "Actualizacion de usuario", response = GenericResponse.class)
	public Response actualizacionUsuario(@Valid Usuario usuario) {
		GenericResponse response = new GenericResponse();
		try {
			usuarioService.actualizarUsuario(usuario);
			response.setMessage("Se actualizó correctamente el usuario");
			return Response.ok(response).build();
		} catch (BussinesException e) {
			response.getError().add(e.getMessage());
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(response).build();
		}

	}

	@GET
	@Path("/{id}")
	@ApiOperation(value = "Obtener usuario", response = GenericResponse.class)
	public Response obtenerUsuarioPorId(@PathParam("id") Integer id) {
		GenericResponse response = new GenericResponse();
		try {
			Usuario usuario = usuarioService.obtenerUsuario(id);
			response.setBody(usuario);
			response.setMessage("Se encontro al usuario con ID:" + id);
			return Response.ok(response).build();
		} catch (BussinesException e) {
			response.getError().add(e.getMessage());
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(response).build();
		}

	}

	@DELETE
	@Path("/{id}")
	@ApiOperation(value = "Eliminar usuario", response = GenericResponse.class)
	public Response eliminarUsuario(@PathParam("id") Integer id) {
		GenericResponse response = new GenericResponse();
		try {
			usuarioService.eliminarUsuario(id);
			response.setMessage("Se eliminó al usuario correctamente");
			return Response.ok(response).build();
		} catch (BussinesException e) {
			response.getError().add(e.getMessage());
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(response).build();
		}

	}
}
