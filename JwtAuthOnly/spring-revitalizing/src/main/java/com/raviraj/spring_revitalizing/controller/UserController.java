package com.raviraj.spring_revitalizing.controller;

import com.raviraj.spring_revitalizing.dto.AuthDTO;
import com.raviraj.spring_revitalizing.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<AuthDTO> registerUser(@RequestBody AuthDTO authDTO){
        AuthDTO registeredUser  = userService.registerUser(authDTO);
        return  ResponseEntity.status(HttpStatus.CREATED).body(registeredUser);
    }


    @GetMapping("/myinfo")
    public ResponseEntity<AuthDTO> getUserInfo(){
        AuthDTO authDTO = userService.getCurrentUserDTO(null);
        return ResponseEntity.status(HttpStatus.FOUND).body(authDTO);
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> loginUser(@RequestBody AuthDTO authDTO){
        Map<String, Object> response= userService.AuthenticationAndGenerateToken(authDTO);
        return ResponseEntity.ok(response);
    }
}
