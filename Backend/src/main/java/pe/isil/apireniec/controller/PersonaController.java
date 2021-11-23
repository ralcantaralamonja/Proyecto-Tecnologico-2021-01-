package pe.isil.apireniec.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.isil.apireniec.dto.PersonaDto;
import pe.isil.apireniec.service.PersonaService;

@RestController
@RequestMapping("/api/dni")
@CrossOrigin(origins = "*")
public class PersonaController {

    private final PersonaService personaService;

    public PersonaController(PersonaService personaService) {
        this.personaService = personaService;
    }

    @GetMapping("/{dni}")
    public ResponseEntity<PersonaDto> bookById(@PathVariable String dni){
        PersonaDto personaDto = personaService.getPersona(dni);
        if (personaDto==null){
            return new ResponseEntity("Dni no encontrado", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(personaDto, HttpStatus.OK);
    }
}
