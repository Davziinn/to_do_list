package br.com.davi.to_do_list.modules.usuario.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthUsuarioResponseDTO {
    private String access_token;
    private Long expiress_in;
    private List<String> roles;
}
