package com.Learning.valuesprocessor.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class HelloController {

    @GetMapping("/test")
    public String test(@RequestParam String name){
        return "Hello " + name;
    }

}