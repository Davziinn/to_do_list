package br.com.davi.to_do_list.usuario.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.davi.to_do_list.usuario.models.UsuarioEntity;
import br.com.davi.to_do_list.usuario.services.UsuarioService;

@RestController
@RequestMapping("/user")
public class UsuarioController {
    
    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/")
    public UsuarioEntity cadastrar(@RequestBody UsuarioEntity usuarioEntity) {
        return this.usuarioService.cadastrar(usuarioEntity);
    }

    @GetMapping("/")
    public List<UsuarioEntity> listarTodos() {
        return this.usuarioService.listarTodos();
    }

    @GetMapping("/{id}")
    public UsuarioEntity buscarPorId(@PathVariable UUID id) {
        return this.usuarioService.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public UsuarioEntity atualizar(@PathVariable UUID id, @RequestBody UsuarioEntity usuarioEntity) {
        return this.usuarioService.atualizar(id, usuarioEntity);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable UUID id) {
        this.usuarioService.deletar(id);
    }
}
