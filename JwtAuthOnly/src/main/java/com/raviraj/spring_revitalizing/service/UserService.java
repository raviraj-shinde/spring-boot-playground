package com.raviraj.spring_revitalizing.service;

import com.raviraj.spring_revitalizing.dto.AuthDTO;
import com.raviraj.spring_revitalizing.entity.UserEntity;
import com.raviraj.spring_revitalizing.repository.UserRepository;
import com.raviraj.spring_revitalizing.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    public AuthDTO registerUser(AuthDTO authDTO) {
        UserEntity newUser = toEntity(authDTO);
        newUser = userRepository.save(newUser);
        return toDTO(newUser);
    }

    public UserEntity getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return userRepository.findByUsername(authentication.getName())
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username"));
    }

    public AuthDTO getCurrentUserDTO(String username) {
        UserEntity userEntity = null;
        if (username != null) {
            userEntity = userRepository.findByUsername(username)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found with username"));
        } else {
            userEntity = getCurrentUser();
        }

        return toDTO(userEntity);
    }

    public Map<String, Object> AuthenticationAndGenerateToken(AuthDTO authDTO) {
        // authenticate user, generate JWT - Token using JwtUtil
        try {
            //Authenticate Manually as you don't have Security By default filter
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authDTO.getUsername(), authDTO.getPassword()));
            //Generate JwtToken
            String token = jwtUtil.generateToken(authDTO.getUsername());
            AuthDTO user = getCurrentUserDTO(authDTO.getUsername());

            return Map.of(
                    "token", token,
                    "user", user
            );
        } catch (Exception e) {
            throw new RuntimeException("Invalid username or Password" + e.getMessage());
        }
    }

    //helper method
    private AuthDTO toDTO(UserEntity userEntity) {
        return AuthDTO.builder()
                .username(userEntity.getUsername())
                .password(null)
                .createdAt(userEntity.getCreatedAt())
                .build();
    }

    private UserEntity toEntity(AuthDTO authDTO) {
        return UserEntity.builder()
                .username(authDTO.getUsername())
                .password(encoder.encode(authDTO.getPassword()))
                .build();
    }
}
