package pe.isil.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Documento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id_Documento")
    private Integer documentoId;
    @Column(name = "Numero_Documento")
    private String numeroDocumento;

    @Column(name = "Id_Tipo")
    private Integer tipoId;
    @Column(name = "Id_Huesped")
    private Integer huespedId;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "Id_Tipo", insertable = false, updatable = false)
    private TipoDocumento tipoDocumento;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Id_Huesped", referencedColumnName = "Id_Huesped", insertable = false, updatable = false)
    private Huesped huesped;
}
