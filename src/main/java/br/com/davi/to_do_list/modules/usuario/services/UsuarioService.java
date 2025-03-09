package br.com.davi.to_do_list.modules.usuario.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.davi.to_do_list.exceptions.UserNotFoundException;
import br.com.davi.to_do_list.modules.usuario.repository.UsuarioRepository;
import br.com.davi.to_do_list.modules.usuario.models.UsuarioEntity;


@Service
public class UsuarioService {
    
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UsuarioEntity cadastrar(UsuarioEntity usuarioEntity) {
        if (usuarioRepository.findByUsernameOrEmail(usuarioEntity.getUsername(), usuarioEntity.getEmail()).isPresent()) {
            throw new RuntimeException("Username/Email já existe");
        }

        var password = passwordEncoder.encode(usuarioEntity.getPassword());
        usuarioEntity.setPassword(password);
        
        return this.usuarioRepository.save(usuarioEntity);
    }

    public List<UsuarioEntity> listarTodos() {
        List<UsuarioEntity> usuarios = this.usuarioRepository.findAll();

        if (usuarios.isEmpty()) {
            throw new RuntimeException("Nenhum usuátio foi encontrado");
        }

        return usuarios;
    }

    public UsuarioEntity buscarPorId(UUID id) {
        return this.usuarioRepository.findById(id).orElseThrow(
            () -> {
                throw new RuntimeException("Usuário não encontrado");
            }
        );
    }

    public UsuarioEntity atualizar(UUID id, UsuarioEntity novoUsuarioEntity) {
        Optional<UsuarioEntity> existe = usuarioRepository.findById(id);
        if (existe.isPresent()) {
            UsuarioEntity existeUsuario = existe.get();
            existeUsuario.setUsername(novoUsuarioEntity.getUsername());
            existeUsuario.setEmail(novoUsuarioEntity.getEmail());
            existeUsuario.setName(novoUsuarioEntity.getName());
            existeUsuario.setPassword(novoUsuarioEntity.getPassword());

            return this.usuarioRepository.save(existeUsuario);
        }

        throw new UserNotFoundException();
    }

    public void deletar(UUID id) {
        if (this.usuarioRepository.findById(id).isEmpty()) {
            throw new UserNotFoundException();
        }
        this.usuarioRepository.deleteById(id);
    }
}
