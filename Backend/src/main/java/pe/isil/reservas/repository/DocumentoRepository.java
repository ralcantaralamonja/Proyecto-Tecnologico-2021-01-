package pe.isil.reservas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.isil.reservas.model.Documento;

@Repository
public interface DocumentoRepository extends JpaRepository<Documento, Integer> {
    Documento findByHuespedId(Integer id);
    boolean existsByDocumentoId(Integer id);
    boolean existsByHuespedId(Integer id);
    Documento findByNumeroDocumento(String numeroDoc);
}
