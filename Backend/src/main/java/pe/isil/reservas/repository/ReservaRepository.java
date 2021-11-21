package pe.isil.reservas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.isil.reservas.model.Reserva;
import pe.isil.security.dto.LoginUsuario;
import pe.isil.security.model.UsuarioPrincipal;
import pe.isil.security.model.entity.Usuario;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Integer> {

    @Procedure("usp_crear_reserva")
    void crearReserva(LocalDateTime fecIngreso, LocalDateTime fecSalida, Integer huespedId, Integer habitacionId, String usuarioRegistro);
    @Procedure("usp_finalizar_reserva")
    void finalizarReserva(Integer id, String usuarioUltMod);
    @Procedure("usp_cancelar_reserva")
    void cancelarReserva(Integer id, String usuarioUltMod);
    @Query(value = "SELECT * FROM reserva WHERE Estado = 0",
    nativeQuery = true)
    List<Reserva> listarPendientes();

    @Query(value = "{call usp_consultar_reservas_habitacion_entre_fechas(:vid_hab, :fec_ini, :fec_fin)}", nativeQuery = true)
    List<Reserva> listarReservasPorHabitacionEntreFechas(@Param("vid_hab") Integer habitacionId, @Param("fec_ini") LocalDate fecIni, @Param("fec_fin") LocalDate fecFin);

}