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
    //@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime fecIngreso;
    //@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime fecSalida;
    private Integer huespedId;
    private Integer habitacionId;
    @NotNull
    private String usuarioRegistro;
    private LocalDateTime fechaRegistro;
    private String usuarioUltModificacion;
    private LocalDateTime fechaUltModificacion;
    private int estado;
}