package pe.isil.consultas.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
public class HabitacionConsultaDto {
    Integer habitacionId;
    LocalDate fechaIni;
    LocalDate fechaFin;
}
