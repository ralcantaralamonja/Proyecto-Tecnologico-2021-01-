package pe.isil.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pe.isil.security.model.entity.Usuario;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    Optional<Usuario> findByUsername(String username);
    boolean existsByUsername(String username);
    boolean existsByCorreo(String correo);
    boolean existsByUsuarioId(Integer id);
    @Query(value = "SELECT * FROM usuario WHERE Estado <> 3",
    nativeQuery = true)
    List<Usuario> listarSinEliminados(int estado);
}
