package com.microcm.entry.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microcm.entry.models.ServiceResponse;

@RestController
public class RootController {
    @GetMapping("/")
    public ServiceResponse index() {
        return new ServiceResponse("success", "Server is running", null);
    }
}
