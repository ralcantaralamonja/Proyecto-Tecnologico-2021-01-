package pe.isil.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.isil.model.Huesped;

import java.util.List;

@Repository
public interface HuespedRepository extends JpaRepository<Huesped, Integer> {
    boolean existsByHuespedId(Integer id);
    List<Huesped> findAllByEstadoEquals(int estado);
}
