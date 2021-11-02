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
@Table(name = "habitacion")
public class Habitacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id_Hab")
    private Integer habitacionId;
    @Column(name = "Numero")
    private String numero ;
    @Column(name = "Foto")
    private String foto ;
    @Column(name = "Id_Tipo")
    private int tipoId;
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
    @Column(name ="Estado")
    private int estado;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "Id_Tipo", insertable = false, updatable = false)
    private TipoHabitacion tipoHabitacion;

    //para crear
    public Habitacion(String numero, String foto, int tipoId, String usuarioRegistro, int estado) {
        this.numero = numero;
        this.foto = foto;
        this.tipoId = tipoId;
        this.usuarioRegistro = usuarioRegistro;
        this.fecha_Registro = LocalDateTime.now();
        this.estado = estado;
    }
}
