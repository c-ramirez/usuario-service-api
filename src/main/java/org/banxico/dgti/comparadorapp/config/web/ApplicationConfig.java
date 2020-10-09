package org.banxico.dgti.comparadorapp.config.web;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;

import io.swagger.jaxrs.config.BeanConfig;

@ApplicationPath("api")
public class ApplicationConfig extends ResourceConfig {
	public ApplicationConfig() {
//		 Configuracion para el swagger 
        BeanConfig beanConfig = new BeanConfig();
        beanConfig.setVersion("1.0.0");
        beanConfig.setSchemes(new String[]{"http"});
        beanConfig.setHost("localhost:8080");
        beanConfig.setBasePath("/api");
        beanConfig.setResourcePackage("com.usuario.demo");
        beanConfig.setScan(true);
        beanConfig.setPrettyPrint(true);
        register(io.swagger.jaxrs.listing.ApiListingResource.class);
        register(io.swagger.jaxrs.listing.SwaggerSerializers.class);
//         Fin configuracion swagger 
        packages("org.banxico.dgti.comparadorapp.controller");
    }

}
