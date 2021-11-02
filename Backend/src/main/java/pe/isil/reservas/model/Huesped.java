package pe.isil.reservas.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "huesped")
public class Huesped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id_Huesped")
    private Integer huespedId;
    @Column(name = "Nombre")
    private String nombre ;
    @Column(name = "Apellido")
    private String apellido;
    @Column(name = "Telefono")
    private String telefono ;
    @Column(name = "Correo")
    private String correo;
    @Column(name = "Estado")
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
    @Column(name = "Observaciones")
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
