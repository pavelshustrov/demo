package com.example.demo.services;

import com.example.demo.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FileService {

    @Autowired
    private FileRepository repository;

    public Optional<String> get(String id) {
        return Optional.ofNullable(repository.get(id));
    }
}
