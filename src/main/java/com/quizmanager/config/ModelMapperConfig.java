package com.quizmanager.config;

import org.hibernate.collection.spi.PersistentSet;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashSet;
import java.util.Set;

@Configuration
public class ModelMapperConfig {
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

//        // Custom converter for PersistentSet to HashSet
//        modelMapper.addConverter(context -> {
//            PersistentSet source = context.getSource();
//            return source == null ? null : new HashSet<>(source);
//        }, PersistentSet.class, Set.class);

        return modelMapper;
    }
}
