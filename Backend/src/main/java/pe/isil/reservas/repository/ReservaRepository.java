package pe.isil.reservas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;
import pe.isil.reservas.model.Reserva;
import pe.isil.security.dto.LoginUsuario;
import pe.isil.security.model.UsuarioPrincipal;
import pe.isil.security.model.entity.Usuario;

import java.time.LocalDateTime;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Integer> {

    @Procedure("usp_CrearReserva")
    void crearReserva(LocalDateTime fecIngreso, LocalDateTime fecSalida, Integer huespedId, Integer habitacionId, String usuarioRegistro);
    @Procedure("usp_FinalizarReserva")
    void finalizarReserva(Integer id, String usuarioUltMod);
    @Procedure("usp_CancelarReserva")
    void cancelarReserva(Integer id, String usuarioUltMod);
}