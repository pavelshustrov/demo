package com.example.demo.configs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class RepositoryConfig {

    @Value("${sourceFile}")
    private String sourceFile;

    public String getSourceFile() {
        return sourceFile;
    }
}
