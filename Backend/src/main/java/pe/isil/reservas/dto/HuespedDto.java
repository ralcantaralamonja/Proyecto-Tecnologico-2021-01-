package pe.isil.reservas.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class HuespedDto {
    private Integer huespedId;
    @NotBlank
    private String nombre ;
    @NotBlank
    private String apellido;
    private String telefono ;
    @Email
    private String correo;
    @NotBlank
    private String usuarioRegistro;
    private LocalDateTime fechaRegistro ;
    private String usuarioUltModificacion;
    private LocalDateTime fechaUltModificacion ;
    private String observaciones ;

    @NotBlank
    private String tipoDocumento;
    @NotBlank
    private String numeroDocumento;

    private int estado;
}
