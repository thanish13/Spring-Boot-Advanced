package org.t13.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class CacheStrategyApp {

    public static void main(String[] args) {
        SpringApplication.run(CacheStrategyApp.class, args);
    }
}
