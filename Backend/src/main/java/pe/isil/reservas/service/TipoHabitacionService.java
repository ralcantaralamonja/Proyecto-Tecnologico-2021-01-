package pe.isil.reservas.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.isil.reservas.model.TipoHabitacion;
import pe.isil.reservas.repository.TipoHabitacionRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TipoHabitacionService implements CrudService<TipoHabitacion, Integer> {

    private final TipoHabitacionRepository tipoHabitacionRepository;

    public TipoHabitacionService(TipoHabitacionRepository tipoHabitacionRepository) {
        this.tipoHabitacionRepository = tipoHabitacionRepository;
    }

    public List<TipoHabitacion> findAll() {
        return tipoHabitacionRepository.findAll();
    }

    public Optional<TipoHabitacion> findById(Integer id) {
        return tipoHabitacionRepository.findById(id);
    }

    public TipoHabitacion create(TipoHabitacion tipoHabitacion) {
        return tipoHabitacionRepository.save(tipoHabitacion);
    }

    public TipoHabitacion update(TipoHabitacion tipoHabitacion) {
        return tipoHabitacionRepository.save(tipoHabitacion);
    }

    public void delete(Integer id) {
        tipoHabitacionRepository.deleteById(id);
    }

    public boolean existsById(Integer id) {
        return tipoHabitacionRepository.existsById(id);
    }
}
