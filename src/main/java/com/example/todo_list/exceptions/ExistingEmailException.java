package com.example.todo_list.exceptions;

public class ExistingEmailException extends RuntimeException {
    public ExistingEmailException() { super("Email já existente, utilize outro");}
    
    public ExistingEmailException(String message) { super(message);}
}
