package pe.isil.security.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.isil.security.model.entity.Usuario;
import pe.isil.security.repository.UsuarioRepository;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@Transactional
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Optional<Usuario> getByUsername(String username) {
        return usuarioRepository.findByUsername(username);
    }

    public Optional<Usuario> getById(Integer id) {
        return usuarioRepository.findById(id);
    }

    public boolean existByUsername(String username) {
        return usuarioRepository.existsByUsername(username);
    }

    public boolean existByCorreo(String correo) {
        return usuarioRepository.existsByCorreo(correo);
    }

    public boolean existsById(Integer id) {
        return usuarioRepository.existsByUsuarioId(id);
    }

    public void save(Usuario usuario) {
        usuarioRepository.save(usuario);
    }

    public void delete(Integer id) {
        usuarioRepository.findById(id).ifPresent(
                u -> {
                    u.setEstado(3);
                    usuarioRepository.save(u);
                }
        );
    }

    public List<Usuario> findAll(){
        return usuarioRepository.findAll();
    }

}
