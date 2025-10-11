package com.example.FrankySabado;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class FrankySabadoApplication {

	public static void main(String[] args) {
		SpringApplication.run(FrankySabadoApplication.class, args);
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**")                 // todas las rutas
						.allowedOriginPatterns("*")        // todos los orígenes (usar * en Boot 3)
						.allowedMethods("*")               // todos los verbos: GET, POST, PUT, DELETE, etc.
						.allowedHeaders("*")               // todos los headers
						.exposedHeaders("*")               // expón si necesitas leer headers en el front
						.allowCredentials(false)           // true requiere orígenes explícitos (no "*")
						.maxAge(3600);                     // cache del preflight en segundos
			}
		};
	}

}
