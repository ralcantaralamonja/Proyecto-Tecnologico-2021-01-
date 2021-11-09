package pe.isil.reservas.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.isil.reservas.dto.Mensaje;
import pe.isil.reservas.dto.ReservaDto;
import pe.isil.reservas.model.Reserva;
import pe.isil.reservas.service.ReservaService;
import pe.isil.security.dto.LoginUsuario;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/reservas")
@CrossOrigin(origins = "*")
public class ReservaController {

    private final ReservaService reservaService;

    public ReservaController(ReservaService reservaService) {
        this.reservaService = reservaService;
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
        reservaService.crearReserva(toReserva(reservaDto));
        return new ResponseEntity(new Mensaje("Reserva registrada con exito"), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> finalizarReserva(@PathVariable("id") Integer id, @RequestBody LoginUsuario loginUsuario) {
        if (!reservaService.existsById(id))
            return new ResponseEntity(new Mensaje("No se encontro la reserva"), HttpStatus.NOT_FOUND);
        reservaService.finalizarReserva(id, loginUsuario);
        return new ResponseEntity(new Mensaje("Reserva terminada"), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> cancelarReserva(@PathVariable("id") Integer id, @RequestBody LoginUsuario loginUsuario) {
        if (!reservaService.existsById(id))
            return new ResponseEntity(new Mensaje("no se encontro reserva"), HttpStatus.NOT_FOUND);
        reservaService.cancelarReserva(id, loginUsuario);
        return new ResponseEntity(new Mensaje("Reserva actualizado"), HttpStatus.OK);
    }

    private ReservaDto toDto(Reserva reserva) {
        ReservaDto dto = new ReservaDto();
        dto.setReservaId(reserva.getReservaId());
        dto.setFecIngreso(reserva.getFecIngreso());
        dto.setFecSalida(reserva.getFecSalida());
        dto.setHuespedId(reserva.getHuespedId());
        dto.setHabitacionId(reserva.getHabitacionId());
        dto.setUsuarioRegistro(reserva.getUsuarioRegistro());
        dto.setFecha_Registro(reserva.getFecha_Registro());
        dto.setUsuarioUltModificacion(reserva.getUsuarioUltModificacion());
        dto.setFechaUltModificacion(reserva.getFechaUltModificacion());
        dto.setEstado(reserva.getEstado());
        return dto;
    }

    //solo para crear!!
    private Reserva toReserva(ReservaDto reservaDto) {
        return new Reserva(reservaDto.getReservaId(), reservaDto.getFecIngreso(), reservaDto.getFecSalida(),
                reservaDto.getHuespedId(), reservaDto.getHabitacionId(), reservaDto.getUsuarioRegistro(),
                reservaDto.getFecha_Registro(), reservaDto.getUsuarioUltModificacion(),
                reservaDto.getFechaUltModificacion(), reservaDto.getEstado(), null, null);
    }
}