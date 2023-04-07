package com.example.demo.model;

import lombok.*;

@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class JwtRequest {
    String username;
    String password;
}
