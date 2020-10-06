package com.usuario.demo.config;

import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;

import org.glassfish.jersey.servlet.ServletContainer;

//@WebServlet(
//		displayName = "usuario-service-api",
//		name = "usuario-rest",
//		initParams = {
//				@WebInitParam(name="jersey.config.server.provider.packages",value="com.usuario.demo")
//		},
//		loadOnStartup = 1,
//		urlPatterns = "/api/*"
//		)
public class ServletConfig extends ServletContainer{
	private static final long serialVersionUID = 1L;

}
