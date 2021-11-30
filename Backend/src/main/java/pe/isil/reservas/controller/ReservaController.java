package pe.isil.reservas.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.isil.reservas.dto.Mensaje;
import pe.isil.reservas.dto.ReservaDto;
import pe.isil.reservas.model.Reserva;
import pe.isil.reservas.service.HabitacionService;
import pe.isil.reservas.service.HuespedService;
import pe.isil.reservas.service.ReservaService;
import pe.isil.security.dto.LoginUsuario;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/reservas")
@CrossOrigin(origins = "*")
public class ReservaController {

    private final ReservaService reservaService;
    private final HuespedService huespedService;
    private final HabitacionService habitacionService;

    public ReservaController(ReservaService reservaService, HuespedService huespedService, HabitacionService habitacionService) {
        this.reservaService = reservaService;
        this.huespedService = huespedService;
        this.habitacionService = habitacionService;
    }

    @GetMapping
    public ResponseEntity<List<ReservaDto>> reservasList() {
        List<Reserva> reservas = reservaService.findAll();
        List<ReservaDto> dtoList = new ArrayList<>();
        for (Reserva reserva : reservas) {
            ReservaDto dto = toDto(reserva);
            dtoList.add(dto);
        }
        return new ResponseEntity<>(dtoList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReservaDto> getReservaById(@PathVariable("id") int id) {
        if (!reservaService.existsById(id))
            return new ResponseEntity(new Mensaje("reserva no registrada"), HttpStatus.NOT_FOUND);
        Reserva reserva = reservaService.findById(id).get();
        ReservaDto reservaDto = toDto(reserva);
        return new ResponseEntity<>(reservaDto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> createReserva(@RequestBody ReservaDto reservaDto) {
        if (!huespedService.existsById(reservaDto.getHuespedId()))
            return new ResponseEntity(new Mensaje("Huesped no registrado"), HttpStatus.NOT_FOUND);
        if (!habitacionService.existsById(reservaDto.getHabitacionId()))
            return new ResponseEntity(new Mensaje("Habitacion no registrada"), HttpStatus.NOT_FOUND);
        if (reservaDto.getFecIngreso().toLocalDate().isBefore(LocalDate.now()))
            return new ResponseEntity(new Mensaje("Solo se pueden registrar reservas a partir de la fecha actual"), HttpStatus.FORBIDDEN);
        if (reservaDto.getFecIngreso().isAfter(reservaDto.getFecSalida()))
            return new ResponseEntity(new Mensaje("La fecha de salida debe ser posterior a la fecha de ingreso"), HttpStatus.FORBIDDEN);
        Reserva reserva = reservaService.reservasPendientesPorHabitacion(reservaDto.getHabitacionId()).get(0);
        if (reserva != null) {
            if (reservaDto.getFecSalida().isAfter(reserva.getFecIngreso()) || reservaDto.getFecIngreso().isAfter(reserva.getFecIngreso()))
                return new ResponseEntity(new Mensaje("La habitacion ya se encuentra reservada para ese rango de fechas. Cambie sus fechas o intente con otra habitacion"), HttpStatus.FORBIDDEN);
        }
        reservaService.crearReserva(toReserva(reservaDto));
        return new ResponseEntity(new Mensaje("Reserva registrada con exito"), HttpStatus.CREATED);
    }

    @PutMapping("/final/{id}")
    public ResponseEntity<?> finalizarReserva(@PathVariable("id") Integer id, @RequestBody LoginUsuario loginUsuario) {
        if (!reservaService.existsById(id))
            return new ResponseEntity(new Mensaje("No se encontro la reserva"), HttpStatus.NOT_FOUND);
        reservaService.finalizarReserva(id, loginUsuario);
        return new ResponseEntity(new Mensaje("Reserva terminada"), HttpStatus.OK);
    }

    @PutMapping("/cancelar/{id}")
    public ResponseEntity<?> cancelarReserva(@PathVariable("id") Integer id, @RequestBody LoginUsuario loginUsuario) {
        if (!reservaService.existsById(id))
            return new ResponseEntity(new Mensaje("no se encontro reserva"), HttpStatus.NOT_FOUND);
        if (reservaService.findById(id).get().getEstado() != 0)
            return new ResponseEntity(new Mensaje("Solo se pueden cancelar reservas pendientes"), HttpStatus.FORBIDDEN);
        reservaService.cancelarReserva(id, loginUsuario);
        return new ResponseEntity(new Mensaje("Reserva cancelada"), HttpStatus.OK);
    }

    @GetMapping("/pendiente/{id}")
    public ResponseEntity<ReservaDto> ultimaReservaPendiente(@PathVariable("id") Integer id) {
        if (!habitacionService.existsById(id))
            return new ResponseEntity(new Mensaje("No existe habitacion"), HttpStatus.NOT_FOUND);
        List<Reserva> reservas = reservaService.reservasPendientesPorHabitacion(id);
        if (reservas.size() == 0)
            return new ResponseEntity(new Mensaje("No registra reservas pendientes"), HttpStatus.NO_CONTENT);
        ReservaDto reservaDto = toDto(reservas.get(0));
        return new ResponseEntity<ReservaDto>(reservaDto, HttpStatus.OK);
    }

    private ReservaDto toDto(Reserva reserva) {
        ReservaDto dto = new ReservaDto();
        dto.setReservaId(reserva.getReservaId());
        dto.setFecIngreso(reserva.getFecIngreso());
        dto.setFecSalida(reserva.getFecSalida());
        dto.setHuespedId(reserva.getHuespedId());
        dto.setHabitacionId(reserva.getHabitacionId());
        dto.setUsuarioRegistro(reserva.getUsuarioRegistro());
        dto.setFechaRegistro(reserva.getFecha_Registro());
        dto.setUsuarioUltModificacion(reserva.getUsuarioUltModificacion());
        dto.setFechaUltModificacion(reserva.getFechaUltModificacion());
        dto.setEstado(reserva.getEstado());
        return dto;
    }

    //solo para crear!!
    private Reserva toReserva(ReservaDto reservaDto) {
        return new Reserva(reservaDto.getReservaId(), reservaDto.getFecIngreso(), reservaDto.getFecSalida(),
                reservaDto.getHuespedId(), reservaDto.getHabitacionId(), reservaDto.getUsuarioRegistro(),
                reservaDto.getFechaRegistro(), reservaDto.getUsuarioUltModificacion(),
                reservaDto.getFechaUltModificacion(), reservaDto.getEstado(), null, null);
    }
}