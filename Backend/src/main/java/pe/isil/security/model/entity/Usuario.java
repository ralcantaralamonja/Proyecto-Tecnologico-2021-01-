package pe.isil.security.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
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
    //@DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion;
    @NotNull
    private String correo;

    private int estado;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name ="rol_usuario", joinColumns = @JoinColumn(name = "id_usuario"), inverseJoinColumns = @JoinColumn(name = "id_rol"))
    private Set<Rol> roles = new HashSet<>();

    public Usuario(String username, String password, String correo) {
        this.username = username;
        this.password = password;
        this.fechaCreacion = LocalDateTime.now();
        this.correo = correo;
        this.estado = 1; //por defecto los usuarios se crean activos
    }
}
