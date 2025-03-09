package br.com.davi.to_do_list.modules.usuario.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.davi.to_do_list.modules.usuario.dto.AuthUsuarioRequestDTO;
import br.com.davi.to_do_list.modules.usuario.services.AuthUsuarioService;

@RestController
@RequestMapping("/user")
public class AuthUsuarioController {

    @Autowired
    private AuthUsuarioService authUsuarioService;
    
    @PostMapping("/auth")
    public ResponseEntity<Object> auth(@RequestBody AuthUsuarioRequestDTO authUsuarioRequestDTO) {

        try {
            var result = this.authUsuarioService.execute(authUsuarioRequestDTO);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
