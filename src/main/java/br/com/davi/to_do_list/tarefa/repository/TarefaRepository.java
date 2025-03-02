package br.com.davi.to_do_list.tarefa.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.davi.to_do_list.tarefa.models.TarefaEntity;

public interface TarefaRepository extends JpaRepository<TarefaEntity, UUID>{
    
}
