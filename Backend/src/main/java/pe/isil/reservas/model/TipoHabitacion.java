package pe.isil.reservas.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tipo_habitacion")
public class TipoHabitacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id_Tipo")
    private Integer tipoId;
    @Column(name = "Nombre")
    private String nombre ;
    @Column(name = "Aforo")
    private int aforo;
    @Column(name = "Banio")
    private int banio;
    @Column(name = "Precio")
    private double precio;

    @OneToMany(mappedBy = "tipoHabitacion")
    private List<Habitacion> habitaciones;
}
