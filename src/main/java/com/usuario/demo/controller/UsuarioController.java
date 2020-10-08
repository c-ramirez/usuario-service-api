package com.usuario.demo.controller;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
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

import com.usuario.demo.repository.usuario.Login;
import com.usuario.demo.repository.usuario.Register;
import com.usuario.demo.repository.usuario.Usuario;
import com.usuario.demo.service.Messages;
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
	ValidatorFactory factory = null;
	Validator validator = null;
	Messages messages;
	public UsuarioController() {
		usuarioService = new UsuarioService();
		factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
		messages = Messages.getInstance();
	}

	@POST
	@Path("/login")
	@ApiOperation(value = "Login de usuario", notes = "Campos obligatorios : usuario, clave", response = GenericResponse.class)
	public Response login(Usuario usuario) {

		Set<ConstraintViolation<Usuario>> violations = validator.validate(usuario, Login.class);
		if (!violations.isEmpty())
			throw new ConstraintViolationException(violations);

		GenericResponse response = new GenericResponse();
		try {
			response.setBody(usuarioService.login(usuario));
			response.setMessage(messages.getMessage("info.usuario.login.ok"));
			return Response.ok(response).build();
		} catch (BussinesException e) {
			response.getError().add(e.getMessage());
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(response).build();
		}

	}

	@POST
	@Path("")
	@ApiOperation(value = "Creacion de usuario", response = GenericResponse.class)
	public Response creacionUsuario(Usuario usuario) {
		Set<ConstraintViolation<Usuario>> violations = validator.validate(usuario, Register.class);
		if (!violations.isEmpty())
			throw new ConstraintViolationException(violations);
		GenericResponse response = new GenericResponse();
		try {
			usuarioService.crearUsuario(usuario);
			response.setMessage(messages.getMessage("info.usuario.create.ok"));
			return Response.status(Status.CREATED).entity(response).build();
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
			response.setMessage(messages.getMessage("info.usuario.update.ok"));
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
			response.setMessage(messages.getMessage("info.usuario.find.ok",id));
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
			response.setMessage(messages.getMessage("info.usuario.delete.ok"));
			return Response.ok(response).build();
		} catch (BussinesException e) {
			response.getError().add(e.getMessage());
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(response).build();
		}

	}
}
