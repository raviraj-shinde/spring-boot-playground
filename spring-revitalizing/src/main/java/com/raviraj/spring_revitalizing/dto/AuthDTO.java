package com.raviraj.spring_revitalizing.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class AuthDTO {
    private String username;
    private String password;
    private LocalDateTime createdAt;
}
