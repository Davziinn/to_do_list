package br.com.davi.to_do_list.modules.usuario.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.davi.to_do_list.modules.usuario.models.UsuarioEntity;


public interface UsuarioRepository extends JpaRepository<UsuarioEntity, UUID> {
    Optional<UsuarioEntity> findByUsernameOrEmail(String username, String email);
    Optional<UsuarioEntity> findByUsername(String username);
}
