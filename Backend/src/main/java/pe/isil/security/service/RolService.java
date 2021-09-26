package pe.isil.security.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.isil.security.enums.RolNombre;
import pe.isil.security.model.entity.Rol;
import pe.isil.security.repository.RolRepository;

import java.util.Optional;

@Service
@Transactional
public class RolService {
    private final RolRepository rolRepository;

    public RolService(RolRepository rolRepository) {
        this.rolRepository = rolRepository;
    }

    public Optional<Rol> getByRolNombre(RolNombre rolNombre){
        return rolRepository.findByRolNombre(rolNombre);
    }

}
