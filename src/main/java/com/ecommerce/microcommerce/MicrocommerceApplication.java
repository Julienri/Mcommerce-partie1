package com.ecommerce.microcommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2 //"/v2/api-docs" pour le format JSON et "/swagger-ui.html" pour le format html
public class MicrocommerceApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicrocommerceApplication.class, args);
    }

}
