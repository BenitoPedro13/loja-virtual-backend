package com.dev.backend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.crypto.Data;
import java.util.Date;

@RestController
@RequestMapping("/api")
public class Hello {
    @GetMapping("/")
    public String hello() {
        return "Ola mundo Spring " + new Date();
    }
}
