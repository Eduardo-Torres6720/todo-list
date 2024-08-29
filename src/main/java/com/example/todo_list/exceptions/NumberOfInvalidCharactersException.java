package com.example.todo_list.exceptions;

public class NumberOfInvalidCharactersException extends RuntimeException {
    public NumberOfInvalidCharactersException() {super("Tamanho dos dados fornecidos acima do requerido");}

    public NumberOfInvalidCharactersException(String message) {super(message);}
}
