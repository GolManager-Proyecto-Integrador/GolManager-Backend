package co.golmanager.gestorweb.service;

import co.golmanager.gestorweb.repository.OrganizadorRepository;
import co.golmanager.gestorweb.entity.Organizador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrganizadorService {

    private final OrganizadorRepository organizadorRepository;

    @Autowired
    public OrganizadorService(OrganizadorRepository organizadorRepository) {
        this.organizadorRepository = organizadorRepository;
    }

    public List<Organizador> findAll() {
        return organizadorRepository.findAll();
    }

    public Optional<Organizador> findById(Long id) {
        return organizadorRepository.findById(id);
    }

    public Organizador save(Organizador organizador) {
        return organizadorRepository.save(organizador);
    }

    public void deleteById(Long id) {
        organizadorRepository.deleteById(id);
    }
}
