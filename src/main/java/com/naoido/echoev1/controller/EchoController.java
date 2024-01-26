package com.naoido.echoev1.controller;

import com.naoido.echoev1.entity.FirebaseAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class EchoController {
    @GetMapping("/echo/")
    public String get(FirebaseAuthenticationToken token) {
        System.out.println("hi");
        return "hi " + token.getUserId() + " " + token.getName();
    }
}
