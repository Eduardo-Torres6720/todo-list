package com.example.todo_list.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.todo_list.domain.user.AuthenticationDTO;
import com.example.todo_list.domain.user.LoginResponseDTO;
import com.example.todo_list.domain.user.User;
import com.example.todo_list.exceptions.ExistingEmailException;
import com.example.todo_list.exceptions.LoginPasswordIncorrectException;
import com.example.todo_list.exceptions.UserNotFoundException;
import com.example.todo_list.infra.security.TokenService;
import com.example.todo_list.repositories.UserRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository repository;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid AuthenticationDTO data) {
        try {
            UsernamePasswordAuthenticationToken usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
            Authentication auth = this.authenticationManager.authenticate(usernamePassword);
            String token = tokenService.generationToken((User) auth.getPrincipal());

            User user = repository.findByLogin(data.login());
    
            return ResponseEntity.ok(new LoginResponseDTO(token, user.getId()));
        } catch (Exception e) {
            throw new LoginPasswordIncorrectException();
        }

    }

    @PostMapping("/register")
    public ResponseEntity<LoginResponseDTO> register(@RequestBody @Valid AuthenticationDTO data) {

        if (repository.findByLogin(data.login()) != null) {throw new ExistingEmailException();}
        
        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        User newUser = new User(data.login(), encryptedPassword);
        String token = tokenService.generationToken((User) newUser);
        
        repository.save(newUser);
        
        return ResponseEntity.ok(new LoginResponseDTO(token, newUser.getId()));
        
    }
}
