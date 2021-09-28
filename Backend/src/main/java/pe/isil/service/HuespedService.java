package pe.isil.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.isil.model.Huesped;
import pe.isil.repository.HuespedRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class HuespedService {

    private final HuespedRepository huespedRepository;

    public HuespedService(HuespedRepository huespedRepository) {
        this.huespedRepository = huespedRepository;
    }

    public List<Huesped> findAll(){
        return huespedRepository.findAll();
    }

    public Optional<Huesped> findById(Integer id){
        return huespedRepository.findById(id);
    }

    public void createOrUpdate(Huesped huesped){
        huespedRepository.save(huesped);
    }

    public void delete(Integer id){
        huespedRepository.deleteById(id);
    }

    public boolean existsById(Integer id){
        return huespedRepository.existsById(id);
    }

}
