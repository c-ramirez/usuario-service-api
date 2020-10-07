package com.usuario.demo.config;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import com.usuario.demo.controller.RequestException;
import com.usuario.demo.controller.TipoCreditoController;
import com.usuario.demo.controller.UsuarioController;

import io.swagger.jaxrs.config.BeanConfig;

@ApplicationPath("api")
public class ApplicationConfig extends Application {
	public ApplicationConfig() {
        BeanConfig beanConfig = new BeanConfig();
        beanConfig.setVersion("1.0.0");
        beanConfig.setSchemes(new String[]{"http"});
        beanConfig.setHost("localhost:8090");
        beanConfig.setBasePath("/api");
        beanConfig.setResourcePackage("com.usuario.demo");
        beanConfig.setScan(true);
        beanConfig.setPrettyPrint(true);
    }
	@Override
	public Set<Class<?>> getClasses() {
		Set<Class<?>> resources = new HashSet<Class<?>>();
		resources.add(UsuarioController.class);
		resources.add(RequestException.class);
		resources.add(TipoCreditoController.class);
		resources.add(io.swagger.jaxrs.listing.ApiListingResource.class);
		resources.add(io.swagger.jaxrs.listing.SwaggerSerializers.class);
		return resources;
	}

}
