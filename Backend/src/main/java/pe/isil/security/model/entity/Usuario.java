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
    private String nombres;
    @NotNull
    private String apellidos;

    //private String telefono;
    //private String foto;

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

    @NotNull
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name ="rol_usuario", joinColumns = @JoinColumn(name = "id_usuario"),
            inverseJoinColumns = @JoinColumn(name = "id_rol"))
    private Set<Rol> roles = new HashSet<>();

    public Usuario(String nombres, String apellidos, String username, String password, String correo) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.username = username;
        this.password = password;
        this.fechaCreacion = LocalDateTime.now();
        this.correo = correo;
        this.estado = 2; //por defecto los usuarios se crean inactivos
                         //hasta ser aprobados
    }
}
