package com.naoido.echoev1.controller;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import com.naoido.echoev1.entity.Echo;
import com.naoido.echoev1.entity.FirebaseAuthenticationToken;
import com.naoido.echoev1.service.EchoesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class EchoController {
    private final EchoesService echoesService;

    @Autowired
    public EchoController(EchoesService echoesService) {
        this.echoesService = echoesService;
    }

    @GetMapping("/profile/")
    public Map<String, String> profile(FirebaseAuthenticationToken token) {
        return Map.of("user-id", token.getUserId(), "user-name", token.getName(), "image", token.getImageUrl());
    }

    @GetMapping("/profile/{userId}")
    public Map<String, String> otherProfile(@PathVariable String userId) throws FirebaseAuthException {
        UserRecord user = FirebaseAuth.getInstance().getUser(userId);
        return Map.of("user-id", user.getUid(), "user-name", user.getDisplayName(), "image", user.getPhotoUrl());
    }

    @PostMapping("/echo")
    public String echo(@RequestParam("echo") String echo, FirebaseAuthenticationToken token) {
        System.out.println("hi");
        this.echoesService.saveEcho(token.getUserId(), echo);
        return "success";
    }

    @GetMapping("/echo")
    public ResponseEntity<List<Echo>> getEcho(@RequestParam(value = "index", defaultValue = "0") int i) {
        return ResponseEntity.ok(this.echoesService.findEchoe(PageRequest.of(i, 10)).stream().toList());
    }
}
