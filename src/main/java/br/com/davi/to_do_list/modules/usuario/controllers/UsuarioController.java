package br.com.davi.to_do_list.modules.usuario.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.davi.to_do_list.modules.usuario.models.UsuarioEntity;
import br.com.davi.to_do_list.modules.usuario.services.UsuarioService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/user")
public class UsuarioController {
    
    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/")
    public Object cadastrar(@Valid @RequestBody UsuarioEntity usuarioEntity) {
        try {
            var result = this.usuarioService.cadastrar(usuarioEntity);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/")
    public Object listarTodos() {
        try {
            var result = this.usuarioService.listarTodos();
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public Object buscarPorId(@PathVariable UUID id) {
        try {
            var result = this.usuarioService.buscarPorId(id);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public Object atualizar(@PathVariable UUID id, @RequestBody UsuarioEntity usuarioEntity) {
        try {
            var result = this.usuarioService.atualizar(id, usuarioEntity);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable UUID id) {
        this.usuarioService.deletar(id);
    }
}
