package com.ABC_company.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Health {
    @GetMapping("/ok")
    public String ok(){
        return "ok ok";
    }
}
