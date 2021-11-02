package pe.isil.reservas.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class HabitacionDto {

    private Integer habitacionId;
    private String numero ;
    private int tipoId;
    @NotNull
    private String usuarioRegistro;
    @NotNull
    private LocalDateTime fecha_Registro ;
    private String usuarioUltModificacion;
    private LocalDateTime fechaUltModificacion ;
    private int estado;

    private String tipo ;
    private int aforo;
    private int banio;
    private double precio;

}
