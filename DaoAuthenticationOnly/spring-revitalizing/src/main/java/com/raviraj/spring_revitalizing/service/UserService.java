package com.raviraj.spring_revitalizing.service;

import com.raviraj.spring_revitalizing.dto.AuthDTO;
import com.raviraj.spring_revitalizing.entity.UserEntity;
import com.raviraj.spring_revitalizing.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    public AuthDTO registerUser(AuthDTO authDTO){
        UserEntity newUser = toEntity(authDTO);
        newUser = userRepository.save(newUser);
        return toDTO(newUser);
    }

    public UserEntity getCurrentUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return userRepository.findByUsername(authentication.getName())
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username"));
    }

    public AuthDTO getCurrentUserDTO() {
        UserEntity userEntity = getCurrentUser();
        return toDTO(userEntity);
    }

    //helper method
    private AuthDTO toDTO(UserEntity userEntity){
        return AuthDTO.builder()
                .username(userEntity.getUsername())
                .password(null)
                .createdAt(userEntity.getCreatedAt())
                .build();
    }

    private UserEntity toEntity(AuthDTO authDTO){
        return UserEntity.builder()
                .username(authDTO.getUsername())
                .password(encoder.encode(authDTO.getPassword()))
                .build();
    }


}
