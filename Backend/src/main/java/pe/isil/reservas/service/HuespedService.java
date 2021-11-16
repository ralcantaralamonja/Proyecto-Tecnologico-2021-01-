package pe.isil.reservas.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.isil.reservas.model.Huesped;
import pe.isil.reservas.repository.HuespedRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
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
        List<Huesped> sinReserva = new ArrayList<>();
        List<Huesped> conReserva = huespedRepository.listarHuespedesConReserva();
        for (Huesped huesped : huespedRepository.findAll()) {
            for (int i = 0; i < conReserva.size(); i++) {
                if (huesped == conReserva.get(i))
                    continue;
                sinReserva.add(huesped);
            }
        }
        System.out.println("sinReserva = " + conReserva);
        return sinReserva;
    }

    public Optional<Huesped> findById(Integer id) {
        return huespedRepository.findById(id);
    }

    public Huesped create(Huesped huesped) {
        return huespedRepository.save(huesped);
    }

    public Huesped update(Huesped huesped) {
        huespedRepository.editarHuesped(huesped.getHuespedId(), huesped.getNombre(), huesped.getApellido(),
                huesped.getTipoDocId(), huesped.getNumeroDocumento(), huesped.getTelefono(), huesped.getCorreo(),
                huesped.getUsuarioUltModificacion(), huesped.getObservaciones());
        return findById(huesped.getHuespedId()).get();
    }

    public void delete(Integer id, String usuario) {
        if (existsById(id)) {
            huespedRepository.eliminarHuesped(id, usuario);
        }
    }

    public boolean existsById(Integer id) {
        return huespedRepository.existsByHuespedId(id);
    }
}
