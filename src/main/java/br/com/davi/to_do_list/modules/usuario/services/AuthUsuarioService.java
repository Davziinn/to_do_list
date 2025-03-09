package br.com.davi.to_do_list.modules.usuario.services;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;

import javax.naming.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import br.com.davi.to_do_list.modules.usuario.dto.AuthUsuarioRequestDTO;
import br.com.davi.to_do_list.modules.usuario.dto.AuthUsuarioResponseDTO;
import br.com.davi.to_do_list.modules.usuario.repository.UsuarioRepository;

@Service
public class AuthUsuarioService {
    
    @Value("${security.token.secret}")
    private String secretKey;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public AuthUsuarioResponseDTO execute(AuthUsuarioRequestDTO authUsuarioRequestDTO) throws AuthenticationException {
        var usuario = this.usuarioRepository.findByUsername(authUsuarioRequestDTO.username()).orElseThrow (() -> {
            throw new RuntimeException("Username/Password incorrect");
        });

        var passwordMatches = passwordEncoder.matches(authUsuarioRequestDTO.password(), usuario.getPassword());
        
        if (!passwordMatches) {
            throw new BadCredentialsException("Username/Password incorrect");
        }

        var roles = Arrays.asList("USUARIO");

        var expiresIn = Instant.now().plus(Duration.ofMinutes(30));

        Algorithm algorithm = Algorithm.HMAC256(secretKey);

        var token = JWT.create()
            .withIssuer("todo")
            .withSubject(usuario.getId().toString())
            .withExpiresAt(expiresIn)
            .withClaim("roles", roles)
            .sign(algorithm)
        ;

        var authUsuarioResponse = AuthUsuarioResponseDTO.builder()
            .access_token(token)
            .expiress_in(expiresIn.toEpochMilli())
            .roles(roles)
            .build()    
        ;

        return authUsuarioResponse;
    }
}
