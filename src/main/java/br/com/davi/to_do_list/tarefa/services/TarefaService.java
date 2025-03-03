package br.com.davi.to_do_list.tarefa.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.davi.to_do_list.exceptions.TaskNotFoundException;
import br.com.davi.to_do_list.tarefa.models.TarefaEntity;
import br.com.davi.to_do_list.tarefa.repository.TarefaRepository;

@Service
public class TarefaService {
    
    @Autowired
    private TarefaRepository tarefaRepository;

    public TarefaEntity criar(TarefaEntity tarefaEntity) {
        return this.tarefaRepository.save(tarefaEntity);
    }

    public List<TarefaEntity> listarTodos() {
        List<TarefaEntity> tarefas = this.tarefaRepository.findAll();        
        if (tarefas.isEmpty()) {
            throw new RuntimeException("Nenhuma tarefa foi encontrada");
        }

        return tarefas;
    }

    public TarefaEntity buscarPorId(UUID id) {
        return this.tarefaRepository.findById(id)
            .orElseThrow(
                () -> {
                    throw new RuntimeException("Tarefa não encontrada!");
                }
            );
    }

    public TarefaEntity atualizar(UUID id, TarefaEntity novaTarefa) {
        Optional<TarefaEntity> existe = tarefaRepository.findById(id);
        if (existe.isPresent()) {
            TarefaEntity tarefa = existe.get();
            tarefa.setTitulo(novaTarefa.getTitulo());
            tarefa.setDescricao(novaTarefa.getDescricao());
            tarefa.setConcluida(novaTarefa.isConcluida());
            return this.tarefaRepository.save(novaTarefa);
        }

        throw new TaskNotFoundException();
    }

    public void deletar(UUID id) {
        if (this.tarefaRepository.findById(id).isEmpty()) {
            throw new TaskNotFoundException();
        }
        
        this.tarefaRepository.deleteById(id);
    }
}
