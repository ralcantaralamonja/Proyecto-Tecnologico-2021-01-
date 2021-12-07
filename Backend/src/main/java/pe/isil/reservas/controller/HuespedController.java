package pe.isil.reservas.controller;

import com.sun.istack.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.isil.reservas.dto.HuespedDto;
import pe.isil.reservas.dto.Mensaje;
import pe.isil.reservas.enums.TipoNombre;
import pe.isil.reservas.model.Huesped;
import pe.isil.reservas.service.HuespedService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/huespedes")
@CrossOrigin(origins = "*")
public class HuespedController {

    private final HuespedService huespedService;

    public HuespedController(HuespedService huespedService) {
        this.huespedService = huespedService;
    }

    @GetMapping
    public ResponseEntity<List<HuespedDto>> huespedesList() {
        List<Huesped> huespedes = huespedService.findAll();
        List<HuespedDto> dtoList = getHuespedDtos(huespedes);
        return new ResponseEntity<List<HuespedDto>>(dtoList, HttpStatus.OK);
    }

    @GetMapping("/sin-reserva")
    public ResponseEntity<List<HuespedDto>> huespedesSinReservaList() {
        List<Huesped> huespedes = huespedService.listarHuespedesSinReserva();
        List<HuespedDto> dtoList = getHuespedDtos(huespedes);
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

    @PostMapping
    public ResponseEntity<?> createHuesped(@RequestBody HuespedDto huespedDto) {
        if (huespedDto.getNombre().isBlank())
            return new ResponseEntity(new Mensaje("el nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        if (huespedDto.getApellido().isBlank())
            return new ResponseEntity(new Mensaje("el apellido es obligatorio"), HttpStatus.BAD_REQUEST);
        huespedService.create(toHuesped(huespedDto));
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

        Huesped huesped = new Huesped(id, huespedDto.getNombre(), huespedDto.getApellido(), getIdTipo(huespedDto),
                huespedDto.getNumeroDocumento(), huespedDto.getTelefono(), huespedDto.getCorreo(),
                huespedDto.getUsuarioUltModificacion(), huespedDto.getObservaciones());
        Huesped huesped1 = huespedService.update(huesped);
        System.out.println(huesped1);

        return new ResponseEntity(new Mensaje("Huesped actualizado"), HttpStatus.OK);
    }

    //TODO
    // ************ agregar validaciones antes de eliminar ************
    // ************* validar reservas y compras ***********************
    @DeleteMapping("/{id}")
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteHuesped(@PathVariable("id") Integer id) {
        if (!huespedService.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        if (huespedService.validarHuespedSinReserva(id))
            return new ResponseEntity(new Mensaje("No es posible eliminar un huesped con reserva"), HttpStatus.METHOD_NOT_ALLOWED);
        huespedService.delete(id, " ");
        return new ResponseEntity(new Mensaje("Huesped eliminado"), HttpStatus.OK);
    }

    private HuespedDto toDto(Huesped huesped) {
        HuespedDto dto = new HuespedDto();
        dto.setHuespedId(huesped.getHuespedId());
        dto.setNombre(huesped.getNombre());
        dto.setApellido(huesped.getApellido());
        dto.setTelefono(huesped.getTelefono());
        dto.setCorreo(huesped.getCorreo());
        dto.setUsuarioRegistro(huesped.getUsuarioRegistro());
        dto.setFechaRegistro(huesped.getFecha_Registro());
        dto.setUsuarioUltModificacion(huesped.getUsuarioUltModificacion());
        dto.setFechaUltModificacion(huesped.getFechaUltModificacion());
        dto.setObservaciones(huesped.getObservaciones());
        String s = enumToString(huesped.getTipoDocumento().getNombre());
        System.out.println("s = " + s);
        dto.setTipoDocumento(s);
        dto.setNumeroDocumento(huesped.getNumeroDocumento());
        dto.setEstado(huesped.getEstado());
        return dto;
    }

    //solo para crear
    private Huesped toHuesped(HuespedDto huespedDto) {
        return new Huesped(huespedDto.getNombre(), huespedDto.getApellido(), getIdTipo(huespedDto),
                huespedDto.getNumeroDocumento(), huespedDto.getTelefono(), huespedDto.getCorreo(),
                huespedDto.getUsuarioRegistro(), huespedDto.getObservaciones());
    }

    private int getIdTipo(HuespedDto huespedDto) {
        int idTipo;
        switch (huespedDto.getTipoDocumento()) {
            case "DNI":
                idTipo = 1;
                break;
            case "PASAPORTE":
                idTipo = 2;
                break;
            case "CARNET DE EXTRANJERIA":
                idTipo = 3;
                break;
            default:
                idTipo = 0;
        }
        return idTipo;
    }

    private Huesped clonarHuesped(Huesped huesped) {
        Huesped h = new Huesped();
        h.setNombre(huesped.getNombre());
        h.setApellido(huesped.getApellido());
        h.setTelefono(huesped.getTelefono());
        h.setCorreo(huesped.getCorreo());
        h.setEstado(1);
        h.setUsuarioRegistro(huesped.getUsuarioRegistro());
        h.setFecha_Registro(huesped.getFecha_Registro());
        h.setUsuarioUltModificacion(huesped.getUsuarioUltModificacion());
        h.setFechaUltModificacion(LocalDateTime.now());
        h.setObservaciones(huesped.getObservaciones());
        h.setTipoDocumento(huesped.getTipoDocumento());
        h.setNumeroDocumento(huesped.getNumeroDocumento());
        return h;
    }

    @NotNull
    private List<HuespedDto> getHuespedDtos(List<Huesped> huespedes) {
        List<HuespedDto> dtoList = new ArrayList<>();
        for (Huesped huesped : huespedes) {
            HuespedDto dto = toDto(huesped);
            dtoList.add(dto);
        }
        return dtoList;
    }

    private String enumToString(TipoNombre tipoNombre) {
        if (tipoNombre == TipoNombre.DNI) {
            return "DNI";
        } else if (tipoNombre == TipoNombre.PASAPORTE) {
            return "PASAPORTE";
        } else if (tipoNombre == TipoNombre.CARNET_EXTRANJERIA) {
            return "CARNET DE EXTRANJERIA";
        } else {
            return null;
        }
    }
}
