package com.ReservaHoteles.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class webConfig implements WebMvcConfigurer{

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // permite todos los endpoints
                .allowedOrigins("http://localhost:4200") // origen del frontend Angular
                .allowedMethods("GET", "POST", "PUT", "DELETE") // métodos permitidos
                .allowedHeaders("*");
    }



        @Override
        public void addResourceHandlers(ResourceHandlerRegistry registry) {
            registry.addResourceHandler("/imagenes/**")
                    .addResourceLocations("file:imagenes/");
        }

}
