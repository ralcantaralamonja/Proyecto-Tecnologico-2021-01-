package pe.isil.apireniec.service;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import pe.isil.apireniec.dto.PersonaDto;
import pe.isil.apireniec.model.Response;

@Service
public class PersonaService {

    private static final String API_DNI = "https://apiperu.dev/api/dni";
    private final RestTemplate restTemplate;

    public PersonaService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public PersonaDto getPersona(String dni) {
        try {
            ResponseEntity<Response> response = restTemplate.getForEntity(API_DNI + "/" + dni, Response.class);
            Response body = response.getBody();
            assert body != null;
            return addPersonaDto(body);
        } catch (Exception e) {
            return null;
        }
    }

    private PersonaDto addPersonaDto(Response body) {
        PersonaDto dto = new PersonaDto();
        dto.setNumero(body.getData().getNumero());
        dto.setNombre_completo(body.getData().getNombre_completo());
        dto.setNombres(body.getData().getNombres());
        dto.setApellido_paterno(body.getData().getApellido_paterno());
        dto.setApellido_materno(body.getData().getApellido_materno());
        dto.setCodigo_verificacion(body.getData().getCodigo_verificacion());
        dto.setDepartamento(body.getData().getDepartamento());
        dto.setProvincia(body.getData().getProvincia());
        dto.setDistrito(body.getData().getDistrito());
        dto.setDireccion(body.getData().getDireccion());
        dto.setDireccion_completa(body.getData().getDireccion_completa());
        dto.setUbigeo_reniec(body.getData().getUbigeo_reniec());
        dto.setUbigeo_sunat(body.getData().getUbigeo_sunat());
        dto.setUbigeo(body.getData().getUbigeo());
        return dto;
    }

}
