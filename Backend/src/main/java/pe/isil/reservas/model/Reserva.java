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
@Table(name = "reserva")
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id_Reserva")
    private Integer reservaId;
    @Column(name = "Fec_Ingreso")
    private LocalDateTime fecIngreso;
    @Column(name = "Fec_Salida")
    private LocalDateTime fecSalida;
    @Column(name = "Id_Huesped")
    private Integer huespedId;
    @Column(name = "Id_Hab")
    private Integer habitacionId;
    @NotNull
    @Column(name = "Usuario_Registro")
    private String usuarioRegistro;
    @Column(name = "Fecha_Registro")
    private LocalDateTime fecha_Registro;
    @Column(name = "Usuario_Ult_Modificacion")
    private String usuarioUltModificacion;
    @Column(name = "Fecha_Ult_Modificacion")
    private LocalDateTime fechaUltModificacion;
    @Column(name = "Estado")
    private int estado; // 1 -> activa | 2 -> finalizada | 3 -> cancelada

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "Id_Huesped", insertable = false, updatable = false)
    private Huesped huesped;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "Id_Hab", insertable = false, updatable = false)
    private Habitacion habitacion;

    //para crear
    public Reserva(LocalDateTime fecIngreso, LocalDateTime fecSalida,
                   Integer huespedId, Integer habitacionId, String usuarioRegistro, int estado) {
        this.fecIngreso = fecIngreso;
        this.fecSalida = fecSalida;
        this.huespedId = huespedId;
        this.habitacionId = habitacionId;
        this.usuarioRegistro = usuarioRegistro;
        this.fecha_Registro = LocalDateTime.now();
        this.estado = estado;
    }
}