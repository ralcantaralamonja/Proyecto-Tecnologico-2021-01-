package pe.isil.consultas.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
public class DetalleReservaHabDto {
    private Integer huespedId;
    private String nombre;
    private String apellido;
    private Integer habitacionId;
    private String habitacionTipoNombre;
    private Integer reservaId;
    private LocalDateTime fechaSolicita;
    private LocalDateTime fechaIngreso;
    private LocalDateTime fechaSalida;
    private Integer estadoReserva;
}