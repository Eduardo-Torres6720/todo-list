package com.example.todo_list.exceptions;

public class TaskNotFoundException extends RuntimeException {
    public TaskNotFoundException() {super("Tarefa não encontrada");}

    public TaskNotFoundException(String message) {super(message);}
}
