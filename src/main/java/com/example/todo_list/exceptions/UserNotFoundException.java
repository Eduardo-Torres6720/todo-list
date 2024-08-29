package com.example.todo_list.exceptions;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException() { super("Usuário não encontrado");}

    public UserNotFoundException(String message) {super(message);}
}
