package br.com.davi.to_do_list.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TaskNotFoundDTO {
    private String message;
}
