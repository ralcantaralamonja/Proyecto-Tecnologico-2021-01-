package pe.isil.reservas.service;

import java.util.List;
import java.util.Optional;

public interface CrudService <T, K>{
    List<T> findAll();
    Optional<T> findById(K k);
    T create(T t);
    T update(T t);
    boolean existsById(K k);
}
