package com.bcopstein;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {"com.bcopstein"})
@EnableJpaRepositories(basePackages = {"com.bcopstein"})
@EntityScan(basePackages = {"com.bcopstein"}) 
public class VendasApplication {
  public static void main(String[] args) {
    SpringApplication.run(VendasApplication.class, args);
  }
}
