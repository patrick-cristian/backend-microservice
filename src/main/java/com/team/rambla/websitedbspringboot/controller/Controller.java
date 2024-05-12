package com.team.rambla.websitedbspringboot.controller;

import com.team.rambla.websitedbspringboot.entity.EntityTest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class Controller {


    @GetMapping("/hello")
    public ResponseEntity<EntityTest> hello() {
        return ResponseEntity.ok(new EntityTest());
    }
}
