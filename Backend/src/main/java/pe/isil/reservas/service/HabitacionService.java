package pe.isil.reservas.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.isil.reservas.model.Habitacion;
import pe.isil.reservas.repository.HabitacionRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class HabitacionService implements CrudService<Habitacion, Integer> {

    private final HabitacionRepository habitacionRepository;

    public HabitacionService(HabitacionRepository habitacionRepository) {
        this.habitacionRepository = habitacionRepository;
    }

    public List<Habitacion> findAll() {
        return habitacionRepository.findAll();
    }

    public List<Habitacion> listarDisponibles() {
        return habitacionRepository.listarDisponibles();
    }

    public Optional<Habitacion> findById(Integer id) {
        return habitacionRepository.findById(id);
    }

    public Habitacion create(Habitacion habitacion) {
        return habitacionRepository.save(habitacion);
    }

    public Habitacion update(Habitacion habitacion) {
        return habitacionRepository.save(habitacion);
    }

    public void delete(Integer id) {
        habitacionRepository.deleteById(id);
    }

    public boolean existsById(Integer id) {
        return habitacionRepository.existsById(id);
    }
}
