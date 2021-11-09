package pe.isil.reservas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pe.isil.reservas.model.Habitacion;

import java.util.List;

@Repository
public interface HabitacionRepository extends JpaRepository<Habitacion, Integer> {
    //TODO
    // metodos para validar disponibilidad de habitaciones
    // usar native query

    @Query(
            value = "SELECT * FROM vw_habitacionesDisponibles",
            nativeQuery = true)
    List<Habitacion> listarDisponibles();

}