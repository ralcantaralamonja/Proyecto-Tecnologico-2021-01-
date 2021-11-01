package pe.isil.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Huesped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id_Huesped")
    private Integer huespedId;
    private String nombre ;
    private String apellido;
    private String telefono ;
    private String correo;
    private int estado;
    @NotNull
    @Column(name = "Usuario_Registro")
    private String usuarioRegistro;
    @NotNull
    @Column(name = "Fecha_Registro")
    private LocalDateTime fecha_Registro ;
    @Column(name = "Usuario_Ult_Modificacion")
    private String usuarioUltModificacion;
    @Column(name = "Fecha_Ult_Modificacion")
    private LocalDateTime fechaUltModificacion ;
    private String observaciones ;

    @OneToOne(mappedBy = "huesped")
    private Documento documento;

    public Huesped(String nombre, String apellido, String telefono, String correo, String usuarioRegistro, String observaciones) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.correo = correo;
        this.estado = 1;
        this.usuarioRegistro = usuarioRegistro;
        this.fecha_Registro = LocalDateTime.now();
        this.observaciones = observaciones;
    }
}
