package com.chatcake.component.Auth;

import com.chatcake.model.request.LoginRequest;
import com.chatcake.model.request.RegisterRequest;
import com.chatcake.model.response.LoginResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired private AuthService authService;

    @PostMapping("/register")
    public void register (@Valid @RequestBody RegisterRequest req) {
        authService.register(req);
    }

    @PostMapping("/login")
    public LoginResponse login (@Valid @RequestBody LoginRequest req) {
        return authService.login(req);
    }

    @DeleteMapping("/logout")
    public void logout (@RequestHeader("Authorization") String token) {
        authService.logout(token);
    }

}
