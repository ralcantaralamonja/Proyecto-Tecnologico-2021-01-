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

    @Query(value = "{call usp_crear_reserva(:vfec_ing, :vfec_sal, :vid_huesped, :vid_hab, :vusu_reg)}", nativeQuery = true)
    Reserva crearReserva(@Param("vfec_ing") LocalDateTime fecIngreso, @Param("vfec_sal") LocalDateTime fecSalida, @Param("vid_huesped") Integer huespedId, @Param("vid_hab") Integer habitacionId, @Param("vusu_reg") String usuarioRegistro);

    @Procedure("usp_finalizar_reserva")
    void finalizarReserva(Integer id, String usuarioUltMod);

    @Procedure("usp_cancelar_reserva")
    void cancelarReserva(Integer id, String usuarioUltMod);

    @Query(value = "SELECT * FROM reserva WHERE Estado = 0",
            nativeQuery = true)
    List<Reserva> listarPendientes();

    @Query(value = "{call usp_consultar_reservas_habitacion_entre_fechas(:vid_hab, :fec_ini, :fec_fin)}", nativeQuery = true)
    List<Reserva> listarReservasPorHabitacionEntreFechas(@Param("vid_hab") Integer habitacionId, @Param("fec_ini") LocalDate fecIni, @Param("fec_fin") LocalDate fecFin);

    @Query(value = "{call usp_ver_detalle_reserva_habitacion(:id_hab)}", nativeQuery = true)
    Reserva verDetalleReservaHabitacion(@Param("id_hab") Integer habitacionId);

    @Query(value = "SELECT * FROM reserva WHERE estado=0 and Id_Hab=?1",
            nativeQuery = true)
    List<Reserva> reservasPendientesPorHabitacion(Integer habitacionId);
}