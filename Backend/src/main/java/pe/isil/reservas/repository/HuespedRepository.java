package pe.isil.reservas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;
import pe.isil.reservas.model.Habitacion;
import pe.isil.reservas.model.Huesped;

import java.util.List;

@Repository
public interface HuespedRepository extends JpaRepository<Huesped, Integer> {
    boolean existsByHuespedId(Integer id);

    List<Huesped> findAllByEstadoEquals(int estado);

    @Query(
            value = "SELECT * FROM vw_huespedes_con_reserva",
            nativeQuery = true)
    List<Huesped> listarHuespedesConReservaActivaOPendiente();

    //FIXME
    //SP DEBE DEVOLVER ROW
    @Procedure("usp_editar_huesped")
    void editarHuesped(Integer id, String nombre, String apellido, Integer idTipDoc, String numDoc,
                          String telefono, String correo, String usuario, String observaciones);

    @Procedure("usp_eliminar_huesped")
    void eliminarHuesped(Integer id, String usuarioUltMod);
}
