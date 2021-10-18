package pe.isil.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.isil.model.Documento;
import pe.isil.model.Huesped;
import pe.isil.repository.DocumentoRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DocumentoService implements CrudService<Documento, Integer> {

    private final DocumentoRepository documentoRepository;

    public DocumentoService(DocumentoRepository documentoRepository) {
        this.documentoRepository = documentoRepository;
    }

    @Override
    public List<Documento> findAll() {
        return documentoRepository.findAll();
    }

    @Override
    public Optional<Documento> findById(Integer id) {
        return documentoRepository.findById(id);
    }

    @Override
    public Documento createOrUpdate(Documento id) {
        return documentoRepository.save(id);
    }

    @Override
    public void delete(Integer id) {
        documentoRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Integer id) {
        return documentoRepository.existsById(id);
    }

    public boolean existsByHuespedId(Integer id) {
        return documentoRepository.existsByHuespedId(id);
    }

    public Documento findByHuespedId(Integer id){
        return documentoRepository.findByHuespedId(id);
    }

    public boolean existsByDocumento(Integer id){
        return documentoRepository.existsByDocumentoId(id);
    }

    public Documento findByNumeroDocumento(String numeroDoc){
        return documentoRepository.findByNumeroDocumento(numeroDoc);
    }
}
