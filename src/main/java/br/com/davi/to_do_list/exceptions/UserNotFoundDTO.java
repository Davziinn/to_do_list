package br.com.davi.to_do_list.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserNotFoundDTO {
    private String message;
}
