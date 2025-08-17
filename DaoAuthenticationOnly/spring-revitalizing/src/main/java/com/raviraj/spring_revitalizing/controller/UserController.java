package com.raviraj.spring_revitalizing.controller;

import com.raviraj.spring_revitalizing.dto.AuthDTO;
import com.raviraj.spring_revitalizing.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        AuthDTO authDTO = userService.getCurrentUserDTO();
        return ResponseEntity.status(HttpStatus.FOUND).body(authDTO);
    }
}
