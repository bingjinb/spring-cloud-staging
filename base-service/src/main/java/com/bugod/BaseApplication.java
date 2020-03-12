package com.bugod;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.bugod"})
public class BaseApplication {
    /**
     * swagger 入口：http://127.0.0.1:4001/swagger-ui.html
     */
    public static void main(String[] args) {
        SpringApplication.run(BaseApplication.class, args);
    }

}
