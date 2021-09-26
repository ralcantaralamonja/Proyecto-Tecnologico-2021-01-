package pe.isil.security.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
public class NuevoUsuario {
    @NotBlank
    private String username;
    @NotBlank
    private String password;
    @Email
    private String correo;
    private Set<String> roles = new HashSet<>();
}
