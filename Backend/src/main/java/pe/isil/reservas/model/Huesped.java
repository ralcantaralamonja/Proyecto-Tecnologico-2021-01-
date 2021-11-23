package pe.isil.reservas.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

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
    @Column(name = "Id_Tipo_Doc")
    private Integer tipoDocId;
    @Column(name = "Numero_Documento")
    private String numeroDocumento;

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

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "Id_Tipo_Doc", insertable = false, updatable = false)
    private TipoDocumento tipoDocumento;

    @OneToMany(mappedBy = "huesped")
    private List<Reserva> reserva;

    //para crear
    public Huesped(String nombre, String apellido, Integer tipoDocId, String numeroDocumento, String telefono, String correo, String usuarioRegistro, String observaciones) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.tipoDocId = tipoDocId;
        this.numeroDocumento = numeroDocumento;
        this.telefono = telefono;
        this.correo = correo;
        this.estado = 1;
        this.usuarioRegistro = usuarioRegistro;
        this.fecha_Registro = LocalDateTime.now();
        this.observaciones = observaciones;
    }

    //para actualizar
    public Huesped(Integer huespedId, String nombre, String apellido, Integer tipoDocId, String numeroDocumento, String telefono, String correo, String usuarioUltModificacion, String observaciones) {
        this.huespedId = huespedId;
        this.nombre = nombre;
        this.apellido = apellido;
        this.tipoDocId = tipoDocId;
        this.numeroDocumento = numeroDocumento;
        this.telefono = telefono;
        this.correo = correo;
        this.usuarioUltModificacion = usuarioUltModificacion;
        this.observaciones = observaciones;
    }
}