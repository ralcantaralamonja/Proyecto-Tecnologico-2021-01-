package pe.isil.reservas.controller;

import com.sun.istack.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.isil.reservas.dto.HabitacionDto;
import pe.isil.reservas.dto.Mensaje;
import pe.isil.reservas.model.Habitacion;
import pe.isil.reservas.model.Reserva;
import pe.isil.reservas.model.TipoHabitacion;
import pe.isil.reservas.service.HabitacionService;
import pe.isil.reservas.service.ReservaService;
import pe.isil.reservas.service.TipoHabitacionService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/habitaciones")
@CrossOrigin(origins = "*")
public class HabitacionController {

    private final HabitacionService habitacionService;
    private final TipoHabitacionService tipoHabitacionService;
    private final ReservaService reservaService;

    public HabitacionController(HabitacionService habitacionService, TipoHabitacionService tipoHabitacionService, ReservaService reservaService) {
        this.habitacionService = habitacionService;
        this.tipoHabitacionService = tipoHabitacionService;
        this.reservaService = reservaService;
    }

    @GetMapping
    public ResponseEntity<List<HabitacionDto>> habitacionesList() {
        List<Reserva> reservas = reservaService.listarPendientes();
        for (Reserva r : reservas) {
            if (!r.getFecIngreso().isAfter(LocalDateTime.now()) && r.getFecSalida().isAfter(LocalDateTime.now())) {
                r.setEstado(1);
                habitacionService.findById(r.getHabitacionId()).ifPresent(
                        habitacion -> {
                            habitacion.setEstado(2);
                            habitacionService.update(habitacion);
                        }
                );
            }
        }

        List<Habitacion> habitaciones = habitacionService.findAll();
        List<HabitacionDto> dtoList = getHabitacionDtos(habitaciones);
        return new ResponseEntity<>(dtoList, HttpStatus.OK);
    }

    @GetMapping("/disponibles")
    public ResponseEntity<List<HabitacionDto>> habitacionesDisponiblesList() {
        List<Habitacion> habitaciones = habitacionService.listarDisponibles();
        List<HabitacionDto> dtoList = getHabitacionDtos(habitaciones);
        return new ResponseEntity<>(dtoList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<HabitacionDto> getHabitacionById(@PathVariable("id") int id) {
        if (!habitacionService.existsById(id))
            return new ResponseEntity(new Mensaje("habitacion no registrada"), HttpStatus.NOT_FOUND);
        Habitacion habitacion = habitacionService.findById(id).get();
        HabitacionDto habitacionDto = toDto(habitacion);
        return new ResponseEntity<>(habitacionDto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> createHabitacion(@RequestBody HabitacionDto habitacionDto) {
        Habitacion habitacion = habitacionService.create(toHabitacion(habitacionDto));
        habitacionService.create(habitacion);
        return new ResponseEntity(new Mensaje("registro exitoso"), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateHabitacion(@PathVariable("id") Integer id, @RequestBody HabitacionDto habitacionDto) {
        if (!habitacionService.existsById(id))
            return new ResponseEntity(new Mensaje("no se encontro habitacion"), HttpStatus.NOT_FOUND);
        Habitacion habitacion = habitacionService.findById(id).get();
        habitacion.setNumero(habitacionDto.getNumero());
        if (!habitacionDto.getFoto().equals("") || habitacionDto.getFoto() != null)
            habitacion.setFoto(habitacionDto.getFoto());
        habitacion.setTipoId(habitacionDto.getTipoId());
        habitacion.setUsuarioUltModificacion(habitacionDto.getUsuarioUltModificacion());
        habitacion.setFechaUltModificacion(LocalDateTime.now());
        habitacion.setEstado(habitacionDto.getEstado());
        habitacionService.update(habitacion);
        return new ResponseEntity(new Mensaje("Habitacion actualizado"), HttpStatus.OK);
    }

    @PutMapping("/mantenimiento/{id}")
    public ResponseEntity<?> activarHabitacion(@PathVariable("id") Integer id) {
        if (!habitacionService.existsById(id))
            return new ResponseEntity(new Mensaje("No se encontro habitacion"), HttpStatus.NOT_FOUND);
        Habitacion habitacion = habitacionService.findById(id).get();
        if (habitacion.getEstado() == 2)
            return new ResponseEntity(new Mensaje("La habitacion aun se encuentra ocupada"), HttpStatus.FORBIDDEN);
        habitacion.setEstado(1);
        habitacionService.update(habitacion);
        return new ResponseEntity(new Mensaje("La habitacion se encuentra nuevamente disponible"), HttpStatus.OK);
    }

    public ResponseEntity<?> deleteHabitacion(@PathVariable("id") Integer id) {
        if (!habitacionService.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        habitacionService.delete(id);
        return new ResponseEntity(new Mensaje("Habitacion eliminada"), HttpStatus.OK);
    }

    @NotNull
    private List<HabitacionDto> getHabitacionDtos(List<Habitacion> habitaciones) {
        List<HabitacionDto> dtoList = new ArrayList<>();
        for (Habitacion habitacion : habitaciones) {
            HabitacionDto dto = toDto(habitacion);
            dtoList.add(dto);
        }
        return dtoList;
    }

    private HabitacionDto toDto(Habitacion habitacion) {
        HabitacionDto dto = new HabitacionDto();
        dto.setHabitacionId(habitacion.getHabitacionId());
        dto.setNumero(habitacion.getNumero());
        dto.setFoto(habitacion.getFoto());
        dto.setTipoId(habitacion.getTipoId());
        dto.setUsuarioRegistro(habitacion.getUsuarioRegistro());
        dto.setFecha_Registro(habitacion.getFecha_Registro());
        dto.setUsuarioUltModificacion(habitacion.getUsuarioUltModificacion());
        dto.setFechaUltModificacion(habitacion.getFechaUltModificacion());
        dto.setEstado(habitacion.getEstado());

        if (tipoHabitacionService.existsById(habitacion.getTipoId())) {
            TipoHabitacion tipoHabitacion = tipoHabitacionService.findById(habitacion.getTipoId()).get();
            dto.setTipo(tipoHabitacion.getNombre());
            dto.setAforo(tipoHabitacion.getAforo());
            dto.setBanio(tipoHabitacion.getBanio());
            dto.setPrecio(tipoHabitacion.getPrecio());
        }
        return dto;
    }

    //solo para crear!!
    private Habitacion toHabitacion(HabitacionDto habitacionDto) {
        return new Habitacion(habitacionDto.getNumero(), habitacionDto.getFoto(), habitacionDto.getTipoId(),
                habitacionDto.getUsuarioRegistro(), habitacionDto.getEstado());
    }
}