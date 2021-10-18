package pe.isil.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.isil.model.Documento;
import pe.isil.model.Huesped;

import java.util.List;

@Repository
public interface DocumentoRepository extends JpaRepository<Documento, Integer> {
    Documento findByHuespedId(Integer id);
    boolean existsByDocumentoId(Integer id);
    boolean existsByHuespedId(Integer id);
    Documento findByNumeroDocumento(String numeroDoc);
}
