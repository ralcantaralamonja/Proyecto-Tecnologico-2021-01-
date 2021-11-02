package pe.isil.reservas.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.isil.reservas.dto.HabitacionDto;
import pe.isil.reservas.dto.Mensaje;
import pe.isil.reservas.enums.TipoNombre;
import pe.isil.reservas.model.TipoHabitacion;
import pe.isil.reservas.model.Habitacion;
import pe.isil.reservas.service.TipoHabitacionService;
import pe.isil.reservas.service.HabitacionService;

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

    public HabitacionController(HabitacionService habitacionService, TipoHabitacionService tipoHabitacionService) {
        this.habitacionService = habitacionService;
        this.tipoHabitacionService = tipoHabitacionService;
    }

    @GetMapping
    public ResponseEntity<List<HabitacionDto>> habitacionesList() {
        List<Habitacion> habitaciones = habitacionService.findAll();
        List<HabitacionDto> dtoList = new ArrayList<>();
        for (Habitacion habitacion : habitaciones) {
            HabitacionDto dto = toDto(habitacion);
            dtoList.add(dto);
        }
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
        habitacion.setTipoId(habitacionDto.getTipoId());
        habitacion.setUsuarioUltModificacion(habitacionDto.getUsuarioUltModificacion());
        habitacion.setFechaUltModificacion(LocalDateTime.now());
        habitacion.setEstado(habitacionDto.getEstado());
        habitacionService.update(habitacion);
        return new ResponseEntity(new Mensaje("Habitacion actualizado"), HttpStatus.OK);
    }

    public ResponseEntity<?> deleteHabitacion(@PathVariable("id") Integer id) {
        if (!habitacionService.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        habitacionService.delete(id);
        return new ResponseEntity(new Mensaje("Habitacion eliminada"), HttpStatus.OK);
    }

    private HabitacionDto toDto(Habitacion habitacion) {
        HabitacionDto dto = new HabitacionDto();
        dto.setHabitacionId(habitacion.getHabitacionId());
        dto.setNumero(habitacion.getNumero());
        dto.setTipoId(habitacion.getTipoId());
        dto.setUsuarioRegistro(habitacion.getUsuarioRegistro());
        dto.setFecha_Registro(habitacion.getFecha_Registro());
        dto.setUsuarioUltModificacion(habitacion.getUsuarioUltModificacion());
        dto.setFechaUltModificacion(habitacion.getFechaUltModificacion());
        dto.setEstado(habitacion.getEstado());

        if (tipoHabitacionService.existsById(habitacion.getTipoId())){
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
        return new Habitacion(habitacionDto.getNumero(), habitacionDto.getTipoId(),
                habitacionDto.getUsuarioRegistro(), habitacionDto.getEstado());
    }
}