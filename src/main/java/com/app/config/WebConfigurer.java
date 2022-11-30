package com.app.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author tamnc
 *
 */
@Configuration
public class WebConfigurer implements WebMvcConfigurer{
	
	@Value(value = "alloweds.origins")
	private String allowedsOrigins;
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/upload/**").addResourceLocations("./uploads");
	}
	
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/api/**").allowedOrigins("http://127.0.0.1:5173/", "http://127.0.0.1:5173")
			.allowedMethods("GET", "PUT", "POST", "DELETE")
			.allowedHeaders("*")
			.allowCredentials(true).maxAge(3600);
	}

}
