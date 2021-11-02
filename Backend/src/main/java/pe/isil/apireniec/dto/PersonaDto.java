package pe.isil.apireniec.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PersonaDto {
    private String numero;
    private String nombre_completo;
    private String nombres;
    private String apellido_paterno;
    private String apellido_materno;
    private Integer codigo_verificacion;
    private String departamento;
    private String provincia;
    private String distrito;
    private String direccion;
    private String direccion_completa;
    private String ubigeo_reniec;
    private String ubigeo_sunat;
    private List<String> ubigeo;
}
