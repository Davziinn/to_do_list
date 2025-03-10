package br.com.davi.to_do_list.modules.tarefa.models;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Table(name = "tarefa_tb")
@Data
public class TarefaEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String titulo;

    private String descricao;

    @NotNull(message = "O status da tarefa deve ser informado")
    private boolean concluida;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
