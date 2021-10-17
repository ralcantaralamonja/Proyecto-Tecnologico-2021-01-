package pe.isil.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.isil.dto.HuespedDto;
import pe.isil.dto.Mensaje;
import pe.isil.model.Documento;
import pe.isil.model.Huesped;
import pe.isil.service.DocumentoService;
import pe.isil.service.HuespedService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/huespedes")
@CrossOrigin(origins = "*")
public class HuespedController {

    //METODOS CON PERMISOS DE ADMIN
    //@PreAuthorize("hasRole('ADMIN')")

    private final HuespedService huespedService;
    private final DocumentoService documentoService;

    public HuespedController(HuespedService huespedService, DocumentoService documentoService) {
        this.huespedService = huespedService;
        this.documentoService = documentoService;
    }

    @GetMapping
    public ResponseEntity<List<HuespedDto>> huespedesList() {
        List<Huesped> huespedes = huespedService.findAll();
        List<HuespedDto> dtoList = new ArrayList<>();
        for (Huesped huesped : huespedes) {
            HuespedDto dto = toDto(huesped);
            dtoList.add(dto);
        }
        return new ResponseEntity<List<HuespedDto>>(dtoList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<HuespedDto> getHuespedById(@PathVariable("id") int id) {
        if (!huespedService.existsById(id))
            return new ResponseEntity(new Mensaje("huesped no registrado"), HttpStatus.NOT_FOUND);
        Huesped huesped = huespedService.findById(id).get();
        HuespedDto huespedDto = toDto(huesped);
        return new ResponseEntity<HuespedDto>(huespedDto, HttpStatus.OK);
    }

    //TODO
    //AGREGAR DOCUMENTO Y RELACIONARLO
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

    //TODO
    //AGREGAR DOCUMENTO Y RELACIONARLO
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

    //TODO
    //ELIMINAR DOCUMENTO TAMBIEN
    @DeleteMapping("/{id}")
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteHuesped(@PathVariable("id") Integer id) {
        if (!huespedService.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        huespedService.delete(id);
        return new ResponseEntity(new Mensaje("Huesped eliminado"), HttpStatus.OK);
    }

    private HuespedDto toDto(Huesped huesped) {
        Documento doc = documentoService.findByHuespedId(huesped.getHuespedId());
        HuespedDto dto = new HuespedDto();
        dto.setHuespedId(huesped.getHuespedId());
        dto.setNombre(huesped.getNombre());
        dto.setApellido(huesped.getApellido());
        dto.setTelefono(huesped.getTelefono());
        dto.setCorreo(huesped.getCorreo());
        dto.setUsuarioRegistro(huesped.getUsuarioRegistro());
        dto.setFecha_Registro(huesped.getFecha_Registro());
        dto.setUsuarioUltModificacion(huesped.getUsuarioUltModificacion());
        dto.setFechaUltModificacion(huesped.getFechaUltModificacion());
        dto.setObservaciones(huesped.getObservaciones());
        dto.setTipoDocumento(doc.getTipoDocumento().getNombre());
        dto.setNumeroDocumento(doc.getNumeroDocumento());
        dto.setEstado(huesped.getEstado());
        return dto;
    }
}
