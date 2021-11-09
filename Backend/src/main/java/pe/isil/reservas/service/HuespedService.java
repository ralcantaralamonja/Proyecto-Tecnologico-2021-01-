package pe.isil.reservas.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.isil.reservas.model.Huesped;
import pe.isil.reservas.repository.HuespedRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class HuespedService implements CrudService<Huesped, Integer> {

    private final HuespedRepository huespedRepository;

    public HuespedService(HuespedRepository huespedRepository) {
        this.huespedRepository = huespedRepository;
    }

    public List<Huesped> findAll() {
        return huespedRepository.findAllByEstadoEquals(1);
    }

    public List<Huesped> listarHuespedesSinReserva() {
        return huespedRepository.listarHuespedesSinReserva();
    }

    public Optional<Huesped> findById(Integer id) {
        return huespedRepository.findById(id);
    }

    public Huesped create(Huesped huesped) {
        return huespedRepository.save(huesped);
    }

    public Huesped update(Huesped huesped) {
        return huespedRepository.save(huesped);
    }

    public void delete(Integer id) {
        Huesped huesped = findById(id).get();
        huesped.setEstado(2);
        huesped.setFechaUltModificacion(LocalDateTime.now());
        huespedRepository.save(huesped);
    }

    public boolean existsById(Integer id) {
        return huespedRepository.existsByHuespedId(id);
    }

}
