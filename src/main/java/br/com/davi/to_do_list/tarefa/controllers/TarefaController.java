package br.com.davi.to_do_list.tarefa.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.davi.to_do_list.tarefa.models.TarefaEntity;
import br.com.davi.to_do_list.tarefa.services.TarefaService;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
@RequestMapping("/tarefas")
public class TarefaController {
    
    @Autowired
    private TarefaService tarefaService;

    @PostMapping("/")
    public TarefaEntity criar(@RequestBody TarefaEntity tarefaEntity) {
        return this.tarefaService.criar(tarefaEntity);
    }

    @GetMapping("/")
    public List<TarefaEntity> buscarTodos() {
        return this.tarefaService.listarTodos();
    }

    @GetMapping("/{id}")
    public TarefaEntity buscarPorId (@PathVariable UUID id) {
        return this.tarefaService.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public TarefaEntity atualizar(@PathVariable UUID id, @RequestBody TarefaEntity tarefaEntity) {
        return this.tarefaService.atualizar(id, tarefaEntity);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        this.tarefaService.deletar(id);
    }
}
