package com.example.todo_list.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.todo_list.domain.user.User;


public interface UserRepository extends JpaRepository<User, String>{
    UserDetails findByLogin(String login);
}
