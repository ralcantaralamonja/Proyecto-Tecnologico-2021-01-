package pe.isil.security.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
public class UsuarioDto {
    private Integer usuarioId;
    private String nombres;
    private String apellidos;
    private String username;
    private LocalDateTime fechaCreacion;
    private String correo;
    private int estado;
    private String rol;
}
