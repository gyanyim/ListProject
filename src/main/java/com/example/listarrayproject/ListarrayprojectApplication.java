package com.example.listarrayproject;

import com.example.listarrayproject.service.ValidatorClass;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.rest.core.event.ValidatingRepositoryEventListener;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;

@SpringBootApplication
public class ListarrayprojectApplication implements RepositoryRestConfigurer {

    public static void main(String[] args) {
        SpringApplication.run(ListarrayprojectApplication.class, args);
    }

    @Override
    public void configureValidatingRepositoryEventListener(
            ValidatingRepositoryEventListener v) {
        v.addValidator("beforeCreate", new ValidatorClass());
    }
}