package com.example.demo.controllers;

import com.example.demo.services.FileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Slf4j
@RestController
@RequestMapping(value = "/airport")
public class FileServiceController {

    @Autowired
    private FileService service;

    @GetMapping(value = "/{id}")
    public @ResponseBody
    ResponseEntity<?> getAirport(@PathVariable(value = "id") String id) {
        Optional<String> airport = service.get(id);
        if (!airport.isPresent())
            return ResponseEntity.badRequest().body("Airport not found");
        else
            return ResponseEntity.ok(airport.get());
    }
}
