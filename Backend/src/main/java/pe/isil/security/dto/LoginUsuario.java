package pe.isil.security.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class LoginUsuario {
    @NotBlank
    private String username;
    @NotBlank
    private String password;
}
