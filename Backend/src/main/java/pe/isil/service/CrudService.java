package pe.isil.service;

import pe.isil.model.Huesped;
import pe.isil.repository.HuespedRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface CrudService <T, K>{
    List<T> findAll();
    Optional<T> findById(K k);
    T createOrUpdate(T t);
    void delete(K k);
    boolean existsById(K k);
}
