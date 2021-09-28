package pe.isil.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class HuespedDto {
    @NotBlank
    private String nombre ;
    @NotBlank
    private String apellido;
    private String telefono ;
    private String correo;
    @NotBlank
    private String usuarioRegistro;
    private String usuarioUltModificacion;
    private String observaciones ;
}
