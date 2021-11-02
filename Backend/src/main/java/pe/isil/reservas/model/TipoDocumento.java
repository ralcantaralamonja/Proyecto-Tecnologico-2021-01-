package pe.isil.reservas.model;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pe.isil.reservas.enums.TipoNombre;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class TipoDocumento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id_Tipo")
    private Integer tipoId;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "Nombre")
    private TipoNombre nombre;

}
