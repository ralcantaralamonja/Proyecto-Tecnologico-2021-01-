package pe.isil.reservas.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.isil.reservas.dto.ReservaDto;
import pe.isil.reservas.model.Reserva;
import pe.isil.reservas.repository.ReservaRepository;
import pe.isil.security.dto.LoginUsuario;
import pe.isil.security.model.UsuarioPrincipal;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ReservaService implements CrudService<Reserva, Integer> {

    private final ReservaRepository reservaRepository;

    public ReservaService(ReservaRepository reservaRepository) {
        this.reservaRepository = reservaRepository;
    }

    @Override
    public List<Reserva> findAll() {
        return reservaRepository.findAll();
    }

    @Override
    public Optional<Reserva> findById(Integer id) {
        return reservaRepository.findById(id);
    }

    @Override
    public Reserva create(Reserva id) {
        return reservaRepository.save(id);
    }

    @Override
    public Reserva update(Reserva id) {
        return reservaRepository.save(id);
    }

    public void delete(Integer id) {
        findById(id).map(
                r -> {
                    r.setEstado(3);
                    reservaRepository.save(r);
                    return null;
                }
        );
    }

    @Override
    public boolean existsById(Integer id) {
        return reservaRepository.existsById(id);
    }

    public void crearReserva(Reserva reserva){
        reservaRepository.crearReserva(reserva.getFecSalida(),
                reserva.getHuespedId(), reserva.getHabitacionId(), reserva.getUsuarioRegistro());
    }
    public void finalizarReserva(Integer id, LoginUsuario loginUsuario) {
        reservaRepository.finalizarReserva(id, loginUsuario.getUsername());
    }
    public void cancelarReserva(Integer id, LoginUsuario loginUsuario) {
        reservaRepository.cancelarReserva(id, loginUsuario.getUsername());
    }

}
