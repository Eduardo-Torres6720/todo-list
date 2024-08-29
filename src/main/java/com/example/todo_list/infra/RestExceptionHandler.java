package com.example.todo_list.infra;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.todo_list.exceptions.ExistingEmailException;
import com.example.todo_list.exceptions.LoginPasswordIncorrectException;
import com.example.todo_list.exceptions.NumberOfInvalidCharactersException;
import com.example.todo_list.exceptions.TaskNotFoundException;
import com.example.todo_list.exceptions.UserNotFoundException;


@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler{

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> userNotFoundHandler(UserNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Usuário não encontrado");
    }

    @ExceptionHandler(ExistingEmailException.class)
    public ResponseEntity<String> existingEmailHandler(ExistingEmailException exception) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Email já cadastrado, tente outro");
    }

    @ExceptionHandler(NumberOfInvalidCharactersException.class)
    public ResponseEntity<String> numberOfInvalidCharactersHandler(NumberOfInvalidCharactersException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Tamanho dos dados acima do requerido");
    }

    @ExceptionHandler(TaskNotFoundException.class)
    public ResponseEntity<String> taskNotFoundHandler(TaskNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tarefa não encontrada");
    }

    @ExceptionHandler(LoginPasswordIncorrectException.class)
    public ResponseEntity<String> loginPasswordIncorrectException(LoginPasswordIncorrectException exception) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Email ou senha incorretos");
    }
}
