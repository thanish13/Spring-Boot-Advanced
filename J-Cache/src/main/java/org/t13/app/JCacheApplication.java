package org.t13.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication // Combines @Configuration, @EnableAutoConfiguration, and @ComponentScan
public class JCacheApplication {

    public static void main(String[] args) {
        SpringApplication.run(JCacheApplication.class, args);
    }
}
