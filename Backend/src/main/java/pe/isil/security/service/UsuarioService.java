package pe.isil.security.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.isil.security.model.entity.Usuario;
import pe.isil.security.repository.UsuarioRepository;

import java.util.Optional;

@Service
@Transactional
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Optional<Usuario> getByUsername(String username){
        return usuarioRepository.findByUsername(username);
    }

    public boolean existByUsername(String username){
        return usuarioRepository.existsByUsername(username);
    }

    public boolean existByCorreo(String correo){
        return usuarioRepository.existsByCorreo(correo);
    }

    public void save(Usuario usuario){
        usuarioRepository.save(usuario);
    }
}
