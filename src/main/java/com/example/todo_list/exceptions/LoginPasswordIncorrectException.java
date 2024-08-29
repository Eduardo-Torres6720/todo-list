package com.example.todo_list.exceptions;

public class LoginPasswordIncorrectException extends RuntimeException {
    public LoginPasswordIncorrectException() {super("Email ou Senha incorreto(s)");}

    public LoginPasswordIncorrectException(String messagem) {super(messagem);}
}
