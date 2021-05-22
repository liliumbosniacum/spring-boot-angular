package com.lilium.springangular.configuration;

import com.lilium.springangular.repository.DistributedRepositoryImpl;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableJpaRepositories(
        value = "com.lilium.springangular.repository",
        repositoryBaseClass = DistributedRepositoryImpl.class)
public class ApplicationConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(final CorsRegistry registry) {
        registry.addMapping("/**").allowedMethods("*").allowedOrigins("http://localhost:4200");
    }
}
