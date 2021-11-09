package pe.isil.reservas.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ReservaDto {
    private Integer reservaId;
    private LocalDateTime fecIngreso;
    private LocalDateTime fecSalida;
    private Integer huespedId;
    private Integer habitacionId;
    @NotNull
    private String usuarioRegistro;
    private LocalDateTime fecha_Registro;
    private String usuarioUltModificacion;
    private LocalDateTime fechaUltModificacion;
    private int estado;
}