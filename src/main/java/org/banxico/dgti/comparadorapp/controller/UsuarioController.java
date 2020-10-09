package org.banxico.dgti.comparadorapp.controller;

import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
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

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.banxico.dgti.comparadorapp.repository.entity.Usuario;
import org.banxico.dgti.comparadorapp.repository.validationgroup.Login;
import org.banxico.dgti.comparadorapp.repository.validationgroup.Register;
import org.banxico.dgti.comparadorapp.service.UsuarioService;
import org.banxico.dgti.comparadorapp.service.exception.BussinesException;
import org.banxico.dgti.comparadorapp.service.util.Messages;
import org.banxico.dgti.comparadorapp.service.util.ValidatorUtil;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Api(tags = { "1.0" })
@Path("/v1/usuario")
public class UsuarioController {
	final Logger log = LogManager.getLogger(UsuarioController.class);
	UsuarioService usuarioService;
	ObjectMapper objectMapper = null;
	Messages messages;

	public UsuarioController() {
		usuarioService = new UsuarioService();
		messages = Messages.getInstance();
		objectMapper = new ObjectMapper();
	}

	@POST
	@Path("/login")
	@ApiOperation(value = "Login de usuario", notes = "Campos obligatorios : usuario, clave", response = GenericResponse.class)
	public Response login(Usuario usuario) {		
		Set<ConstraintViolation<Usuario>> violations = ValidatorUtil.validation(usuario, Login.class);
		if (!violations.isEmpty())
			throw new ConstraintViolationException(violations);
		GenericResponse response = new GenericResponse();
		try {
			response.setBody(usuarioService.login(usuario));
			response.setMessage(messages.getMessage("info.usuario.login.ok"));
			return Response.ok(objectMapper.writeValueAsString(response)).build();
		} catch (BussinesException e) {
			response.getError().add(e.getMessage());
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(response).build();
		} catch (JsonProcessingException ex) {
			response.getError().add(ex.getMessage());
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(response).build();
		}

	}

	@POST
	@Path("")
	@ApiOperation(value = "Creacion de usuario", response = GenericResponse.class)
	public Response creacionUsuario(Usuario usuario) {
		Set<ConstraintViolation<Usuario>> violations = ValidatorUtil.validation(usuario, Register.class);
		if (!violations.isEmpty())
			throw new ConstraintViolationException(violations);
		GenericResponse response = new GenericResponse();
		try {
			usuarioService.save(usuario);
			response.setMessage(messages.getMessage("info.entity.create.ok"));
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
			usuarioService.update(usuario);
			response.setMessage(messages.getMessage("info.entity.update.ok"));
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
			Usuario usuario = usuarioService.findById(id);
			response.setBody(usuario);
			response.setMessage(messages.getMessage("info.entity.find.ok", id));
			return Response.ok(objectMapper.writeValueAsString(response)).build();
		} catch (BussinesException e) {
			response.getError().add(e.getMessage());
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(response).build();
		} catch (JsonProcessingException ex) {
			response.getError().add(ex.getMessage());
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(response).build();
		}

	}

	@DELETE
	@Path("/{id}")
	@ApiOperation(value = "Eliminar usuario", response = GenericResponse.class)
	public Response eliminarUsuario(@PathParam("id") Integer id) {
		GenericResponse response = new GenericResponse();
		try {
			usuarioService.delete(id);
			response.setMessage(messages.getMessage("info.entity.delete.ok"));
			return Response.ok(response).build();
		} catch (BussinesException e) {
			response.getError().add(e.getMessage());
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(response).build();
		}

	}
	@GET
	@Path("")
	@ApiOperation(value = "Obtener usuarios", response = GenericResponse.class)
	public Response obtenerUsuarios() {
		GenericResponse response = new GenericResponse();
		try {
			List<Usuario> usuarios = usuarioService.findAll();
			response.setBody(usuarios);
			response.setMessage(messages.getMessage("info.entity.findAll.ok"));
			return Response.ok(objectMapper.writeValueAsString(response)).build();
		} catch (BussinesException e) {
			response.getError().add(e.getMessage());
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(response).build();
		} catch (JsonProcessingException ex) {
			response.getError().add(ex.getMessage());
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(response).build();
		}

	}
}
