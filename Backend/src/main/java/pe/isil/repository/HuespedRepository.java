package pe.isil.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.isil.model.Huesped;

@Repository
public interface HuespedRepository extends JpaRepository<Huesped, Integer> {
    boolean existsByHuespedId(Integer id);
}
