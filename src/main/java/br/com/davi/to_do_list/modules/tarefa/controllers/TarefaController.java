package br.com.davi.to_do_list.modules.tarefa.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.davi.to_do_list.modules.tarefa.services.TarefaService;
import br.com.davi.to_do_list.modules.tarefa.models.TarefaEntity;

import org.springframework.web.bind.annotation.PutMapping;



@RestController
@RequestMapping("/tarefas")
public class TarefaController {
    
    @Autowired
    private TarefaService tarefaService;

    @PostMapping("/")
    public Object criar(@RequestBody TarefaEntity tarefaEntity) {
        try {
            var result = this.tarefaService.criar(tarefaEntity);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/")
    public Object buscarTodos() {
        try {
            var result = this.tarefaService.listarTodos();
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public Object buscarPorId (@PathVariable UUID id) {
        try {
            var result = this.tarefaService.buscarPorId(id);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public Object atualizar(@PathVariable UUID id, @RequestBody TarefaEntity tarefaEntity) {
        try {
            var result = this.tarefaService.atualizar(id, tarefaEntity);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        this.tarefaService.deletar(id);
    }
}
