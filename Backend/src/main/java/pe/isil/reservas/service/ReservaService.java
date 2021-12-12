package pe.isil.reservas.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.isil.consultas.dto.HabitacionConsultaDto;
import pe.isil.reservas.model.Habitacion;
import pe.isil.reservas.model.Reserva;
import pe.isil.reservas.repository.ReservaRepository;
import pe.isil.security.dto.LoginUsuario;

import java.util.List;
import java.util.Optional;

import static java.time.temporal.ChronoUnit.DAYS;

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

    public List<Reserva> listarPendientes() {
        return reservaRepository.listarPendientes();
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

    public Reserva crearReserva(Reserva reserva) {
        return reservaRepository.crearReserva(reserva.getFecIngreso(), reserva.getFecSalida(),
                reserva.getHuespedId(), reserva.getHabitacionId(), reserva.getUsuarioRegistro());
    }

    public void finalizarReserva(Integer id, LoginUsuario loginUsuario) {
        reservaRepository.finalizarReserva(id, loginUsuario.getUsername());
    }

    public void cancelarReserva(Integer id, LoginUsuario loginUsuario) {
        reservaRepository.cancelarReserva(id, loginUsuario.getUsername());
    }

    public List<Reserva> listarReservasPorHabitacionEntreFechas(HabitacionConsultaDto dto) {
        return reservaRepository.listarReservasPorHabitacionEntreFechas(dto.getHabitacionId(), dto.getFechaIni(), dto.getFechaFin());
    }

    public Reserva verDetalleReservaHabitacion(Integer habitacionId) {
        return reservaRepository.verDetalleReservaHabitacion(habitacionId);
    }

    public List<Reserva> reservasPendientesPorHabitacion(Integer habitacionId) {
        return reservaRepository.reservasPendientesPorHabitacion(habitacionId);
    }

    public Double porcentajeOcupacionHabitacionEntreFechas(HabitacionConsultaDto dto) {
        int totalDias = (int) DAYS.between(dto.getFechaIni(), dto.getFechaFin());
        int numeroDeDiasOcupados = reservaRepository.fechasEnQueSeOcupoHabitacionPorRango(dto.getHabitacionId(), dto.getFechaIni(), dto.getFechaFin()).size();
        return numeroDeDiasOcupados * 1.0 / totalDias;
    }

}
