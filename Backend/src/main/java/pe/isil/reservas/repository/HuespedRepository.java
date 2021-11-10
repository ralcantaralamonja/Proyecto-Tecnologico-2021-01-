package pe.isil.reservas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pe.isil.reservas.model.Habitacion;
import pe.isil.reservas.model.Huesped;

import java.util.List;

@Repository
public interface HuespedRepository extends JpaRepository<Huesped, Integer> {
    boolean existsByHuespedId(Integer id);
    List<Huesped> findAllByEstadoEquals(int estado);

    @Query(
            value = "SELECT * FROM vw_huespedesconreserva",
            nativeQuery = true)
    List<Huesped> listarHuespedesConReserva();
}
