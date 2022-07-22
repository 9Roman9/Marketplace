package com.marketplace;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class Config {
    @Bean
    public Menu menuBean(){
        return new Menu();
    }

    @Bean
    @Scope("prototype")
    public User userBean(){
        return new User();
    }

    @Bean
    @Scope("prototype")
    public Product productBean(){
        return  new Product();
    }
}
