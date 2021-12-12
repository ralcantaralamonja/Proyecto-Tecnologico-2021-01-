package pe.isil.consultas.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class HabitacionConsultaDto {
    Integer habitacionId;
    LocalDate fechaIni;
    LocalDate fechaFin;
}
