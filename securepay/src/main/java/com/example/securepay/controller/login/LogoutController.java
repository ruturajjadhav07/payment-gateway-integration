package com.example.securepay.controller.login;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LogoutController {

    @PostMapping("/logout")
    public ResponseEntity<?> logout() {

        ResponseCookie cookie = ResponseCookie.from("jwt", "")
                .httpOnly(true)
                .secure(false)
                .path("/")
                .maxAge(0) // removes cookie
                .sameSite("Strict")
                .build();

        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, cookie.toString())
                .body("Logged out successfully");
    }
}