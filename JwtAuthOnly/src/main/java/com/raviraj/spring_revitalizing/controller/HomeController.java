package com.raviraj.spring_revitalizing.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/status", "/health"})
public class HomeController {

    @GetMapping
    public static ResponseEntity<String> getStatus(){
        return ResponseEntity.ok("Application is running");
    }
}
