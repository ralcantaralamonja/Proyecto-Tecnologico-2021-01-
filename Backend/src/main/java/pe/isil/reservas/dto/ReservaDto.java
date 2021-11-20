package pe.isil.reservas.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static java.time.format.DateTimeFormatter.ISO_LOCAL_DATE_TIME;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ReservaDto {
    private Integer reservaId;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime fecIngreso;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
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