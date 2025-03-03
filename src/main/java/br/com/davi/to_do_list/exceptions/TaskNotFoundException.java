package br.com.davi.to_do_list.exceptions;

public class TaskNotFoundException extends RuntimeException {
    public TaskNotFoundException() {
        super("Tarefa não encontrada");
    }
}
