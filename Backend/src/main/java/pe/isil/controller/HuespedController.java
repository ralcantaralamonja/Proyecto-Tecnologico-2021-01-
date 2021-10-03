package pe.isil.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.isil.dto.HuespedDto;
import pe.isil.dto.Mensaje;
import pe.isil.model.Huesped;
import pe.isil.service.HuespedService;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/huespedes")
@CrossOrigin(origins = "*")
public class HuespedController {

    //METODOS CON PERMISOS DE ADMIN
    //@PreAuthorize("hasRole('ADMIN')")

    private final HuespedService huespedService;

    public HuespedController(HuespedService huespedService) {
        this.huespedService = huespedService;
    }

    @GetMapping
    public ResponseEntity<List<Huesped>> huespedesList() {
        List<Huesped> huespedes = huespedService.findAll();
        return new ResponseEntity(huespedes, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Huesped> getHuespedById(@PathVariable("id") int id) {
        if (!huespedService.existsById(id))
            return new ResponseEntity(new Mensaje("huesped no registrado"), HttpStatus.NOT_FOUND);
        Huesped huesped = huespedService.findById(id).get();
        return new ResponseEntity(huesped, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> createHuesped(@RequestBody HuespedDto huespedDto) {
        if (huespedDto.getNombre().isBlank())
            return new ResponseEntity(new Mensaje("el nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        if (huespedDto.getApellido().isBlank())
            return new ResponseEntity(new Mensaje("el apellido es obligatorio"), HttpStatus.BAD_REQUEST);
        Huesped huesped = new Huesped(huespedDto.getNombre(), huespedDto.getApellido(), huespedDto.getTelefono(),
                huespedDto.getCorreo(), huespedDto.getUsuarioRegistro(), huespedDto.getObservaciones());
        huespedService.createOrUpdate(huesped);
        return new ResponseEntity(new Mensaje("registro exitoso"), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateHuesped(@PathVariable("id") Integer id, @RequestBody HuespedDto huespedDto) {
        if (!huespedService.existsById(id))
            return new ResponseEntity(new Mensaje("no se encontro Huesped"), HttpStatus.NOT_FOUND);
        if (huespedDto.getNombre().isBlank())
            return new ResponseEntity(new Mensaje("el nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        if (huespedDto.getApellido().isBlank())
            return new ResponseEntity(new Mensaje("el apellido es obligatorio"), HttpStatus.BAD_REQUEST);

        Huesped huesped = huespedService.findById(id).get();
        huesped.setNombre(huespedDto.getNombre());
        huesped.setApellido(huespedDto.getApellido());
        huesped.setTelefono(huespedDto.getTelefono());
        huesped.setCorreo(huespedDto.getCorreo());
        huesped.setUsuarioUltModificacion(huespedDto.getUsuarioUltModificacion());
        huesped.setFechaUltModificacion(LocalDateTime.now());
        huesped.setObservaciones(huespedDto.getObservaciones());
        huespedService.createOrUpdate(huesped);
        return new ResponseEntity(new Mensaje("Huesped actualizado"), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteHuesped(@PathVariable("id") Integer id) {
        if (!huespedService.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        huespedService.delete(id);
        return new ResponseEntity(new Mensaje("Huesped eliminado"), HttpStatus.OK);
    }

}
