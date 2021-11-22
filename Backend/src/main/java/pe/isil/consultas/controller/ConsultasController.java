package pe.isil.consultas.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.isil.consultas.dto.HabitacionConsultaDto;
import pe.isil.consultas.dto.DetalleReservaHabDto;
import pe.isil.reservas.dto.Mensaje;
import pe.isil.reservas.model.Habitacion;
import pe.isil.reservas.model.Huesped;
import pe.isil.reservas.model.Reserva;
import pe.isil.reservas.model.TipoHabitacion;
import pe.isil.reservas.service.HabitacionService;
import pe.isil.reservas.service.HuespedService;
import pe.isil.reservas.service.ReservaService;
import pe.isil.reservas.service.TipoHabitacionService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/consultas")
@CrossOrigin(origins = "*")
public class ConsultasController {

    private final ReservaService reservaService;
    private final HabitacionService habitacionService;
    private final HuespedService huespedService;
    private final TipoHabitacionService tipoHabitacionService;

    public ConsultasController(ReservaService reservaService, HabitacionService habitacionService, HuespedService huespedService, TipoHabitacionService tipoHabitacionService) {
        this.reservaService = reservaService;
        this.habitacionService = habitacionService;
        this.huespedService = huespedService;
        this.tipoHabitacionService = tipoHabitacionService;
    }

    @GetMapping("/habitacion/huespedes-fechas/{id}")
    public ResponseEntity<List<DetalleReservaHabDto>> huespedesPorHabitacionList(@PathVariable Integer id, @RequestBody HabitacionConsultaDto dto) {
        if (!habitacionService.existsById(id))
            return new ResponseEntity(new Mensaje("habitacion no registrada"), HttpStatus.NOT_FOUND);

        List<DetalleReservaHabDto> consultaDtoList = new ArrayList<>();
        List<Reserva> reservas = reservaService.listarReservasPorHabitacionEntreFechas(dto);
        for (Reserva reserva : reservas) {
            DetalleReservaHabDto consultaDto = getDetalleReservaHabDto(reserva);
            consultaDtoList.add(consultaDto);
        }
        return new ResponseEntity<>(consultaDtoList, HttpStatus.OK);
    }

    @GetMapping("/habitacion/reserva-activa/{id}")
    public ResponseEntity<DetalleReservaHabDto> reservaActivaPorHabitacion(@PathVariable Integer id) {
        if (!habitacionService.existsById(id))
            return new ResponseEntity(new Mensaje("habitacion no registrada"), HttpStatus.NOT_FOUND);
        Reserva reserva = reservaService.verDetalleReservaHabitacion(id);
        if (reserva == null)
            return new ResponseEntity(new Mensaje("No existe reserva activa en este momento"), HttpStatus.NOT_FOUND);
        DetalleReservaHabDto consultaDto = getDetalleReservaHabDto(reserva);
        return new ResponseEntity<DetalleReservaHabDto>(consultaDto, HttpStatus.OK);
    }

    private DetalleReservaHabDto getDetalleReservaHabDto(Reserva reserva) {
        Huesped huesped = huespedService.findById(reserva.getHuespedId()).get();
        Habitacion habitacion = habitacionService.findById(reserva.getHabitacionId()).get();
        TipoHabitacion tipoHabitacion = tipoHabitacionService.findById(habitacion.getTipoId()).get();

        DetalleReservaHabDto consultaDto = new DetalleReservaHabDto();
        consultaDto.setHuespedId(huesped.getHuespedId());
        consultaDto.setNombre(huesped.getNombre());
        consultaDto.setApellido(huesped.getApellido());
        consultaDto.setHabitacionId(habitacion.getHabitacionId());
        consultaDto.setHabitacionTipoNombre(tipoHabitacion.getNombre());
        consultaDto.setReservaId(reserva.getReservaId());
        consultaDto.setFechaSolicita(reserva.getFecha_Registro());
        consultaDto.setFechaIngreso(reserva.getFecIngreso());
        consultaDto.setFechaSalida(reserva.getFecSalida());
        consultaDto.setEstadoReserva(reserva.getEstado());
        return consultaDto;
    }
}
