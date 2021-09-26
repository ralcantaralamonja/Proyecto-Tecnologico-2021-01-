package pe.isil.security.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id_Usuario")
    private Integer usuarioId;
    @NotNull
    @Column(unique = true)
    private String username;
    @NotNull
    private String password;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "fecha_creacion")
    private LocalDate fechaCreacion;
    @NotNull
    private String correo;

    private int estado;

    @ManyToMany
    @JoinTable(name ="rol_usuario", joinColumns = @JoinColumn(name = "id_usuario"), inverseJoinColumns = @JoinColumn(name = "id_rol"))
    private Set<Rol> roles = new HashSet<>();

    public Usuario(String username, String password, String correo) {
        this.username = username;
        this.password = password;
        this.fechaCreacion = LocalDate.now();
        this.correo = correo;
        this.estado = 1;
    }
}
