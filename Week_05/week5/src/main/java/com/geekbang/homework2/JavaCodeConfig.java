package com.geekbang.homework2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JavaCodeConfig {

    @Bean
    public JavaCodeDemo javaCodeDemo() {
        return new JavaCodeDemo();
    }
}
