package pe.isil.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pe.isil.model.TipoDocumento;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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
    private LocalDateTime fecha_Registro ;
    private String usuarioUltModificacion;
    private LocalDateTime fechaUltModificacion ;
    private String observaciones ;

    private String tipoDocumento;
    private String numeroDocumento;

    private int estado;
}
