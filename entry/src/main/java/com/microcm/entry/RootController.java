package com.microcm.entry;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RootController {
    @GetMapping("/")
    public ServiceResponse index() {
        return new ServiceResponse("success", "Server is running", null);
    }
}
