package com.lilium.springangular.controller;

import com.lilium.springangular.dto.HelloWorldDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api")
@RestController
public class HelloWorld {

    @GetMapping("/hello")
    public HelloWorldDTO getHelloWorldMessage() {
        HelloWorldDTO dto = new HelloWorldDTO();
        dto.setMessage("Hello Spring with Angular");
        return dto;
    }
}
