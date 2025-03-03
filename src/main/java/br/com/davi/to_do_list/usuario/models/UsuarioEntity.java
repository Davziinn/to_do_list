package br.com.davi.to_do_list.usuario.models;

import java.util.UUID;

import org.hibernate.validator.constraints.Length;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Entity
@Table(name = "usuario_tb")
@Data
public class UsuarioEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;

    @Pattern(regexp="\\S+", message="O campo [username] não pode conter espaços")
    private String username;
    
    @Length(min = 10, message = "O campo [password] deve possuir no máximo 10 dígitos")
    private String password;
    
    @Email(message = "O campo [e-mail] de conter um e-mail valido")
    private String email;
}
