package pe.isil.security.model;

import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import pe.isil.security.model.entity.Usuario;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public class UsuarioPrincipal implements UserDetails {

    private String username;
    private String password;
    private LocalDate fechaCreacion;
    private String correo;
    private int estado;
    private Collection<? extends GrantedAuthority> authorities;

    public static UsuarioPrincipal build(Usuario usuario) {
        List<GrantedAuthority> authorities =
                usuario.getRoles().stream().map(
                        rol -> new SimpleGrantedAuthority(rol
                                .getRolNombre().name())).collect(Collectors.toList());
        return new UsuarioPrincipal(usuario.getUsername(), usuario.getPassword(), usuario.getFechaCreacion(), usuario.getCorreo(), usuario.getEstado(), authorities);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public String getCorreo() {
        return correo;
    }

    public int getEstado() {
        return estado;
    }
}
