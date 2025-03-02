package br.com.davi.to_do_list.usuario.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.davi.to_do_list.usuario.models.UsuarioEntity;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, UUID> {

}
