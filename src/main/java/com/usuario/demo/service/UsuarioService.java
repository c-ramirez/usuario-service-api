package com.usuario.demo.service;

import java.util.List;

import com.usuario.demo.repository.tipocredito.TipoCredito;
import com.usuario.demo.repository.tipocredito.TipoCreditoRepository;
import com.usuario.demo.repository.tipocredito.TipoCreditoRepositoryImpl;
import com.usuario.demo.repository.usuario.Usuario;
import com.usuario.demo.repository.usuario.UsuarioRepository;
import com.usuario.demo.repository.usuario.UsuarioRepositoryImpl;

public class UsuarioService {
	UsuarioRepository usuarioRepository;
	TipoCreditoRepository tipoCreditoRepository;

	public UsuarioService() {
		usuarioRepository = new UsuarioRepositoryImpl();
		tipoCreditoRepository = new TipoCreditoRepositoryImpl();
	}

	public Usuario login(Usuario usuario) {
		return usuarioRepository.obtenerUsuario(usuario.getUsuario(), usuario.getClave());
	}

	public List<TipoCredito> obtenerTiposCredito() {
		return tipoCreditoRepository.obtenerTiposCredito();
	}

	public void crearUsuario(Usuario usuario) {
		usuarioRepository.crear(usuario);

	}

	public Usuario obtenerUsuario(Integer id) {
		return usuarioRepository.obtenerUsuarioPorId(id);
	}
	public void actualizarUsuario(Usuario usuario) {
		usuarioRepository.actualizar(usuario);
	}
	public void eliminarUsuario(Integer id) {
		usuarioRepository.eliminar(id);
	}
}
