package com.example.todo_list.infra.security;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.todo_list.domain.user.User;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    public String generationToken(User user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            String token = JWT.create()
                .withIssuer("todo_list-api")
                .withSubject(user.getLogin())
                .withExpiresAt(generationTime())
                .sign(algorithm);
            return token;
        } catch (Exception e) {
            throw new RuntimeException("Error while generation token", e);
        }
    }

    public String validationToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                .withIssuer("todo_list-api")
                .build()
                .verify(token)
                .getSubject();
        } catch (Exception e){
            throw new RuntimeException("Error while validate token", e);
        }
    }

    public Instant generationTime() {
        return LocalDateTime.now().plusHours(3).toInstant(ZoneOffset.of("-03:00"));
    }
}
