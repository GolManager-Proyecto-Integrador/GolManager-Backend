package co.golmanager.gestorweb.controller;

import co.golmanager.gestorweb.entity.Organizador;
import co.golmanager.gestorweb.service.OrganizadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
class OrganizadorControlller {

    private final OrganizadorService organizadorService;

    @Autowired
    public OrganizadorControlller(OrganizadorService organizadorService) {
        this.organizadorService = organizadorService;
    }

    @GetMapping
    public List<Organizador> getAllUsers() {
        return organizadorService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Organizador> getUserById(@PathVariable Long id) {
        return organizadorService.findById(id);
    }

    @PostMapping
    public Organizador createUser(@RequestBody Organizador organizador) {
        return organizadorService.save(organizador);
    }

    @PutMapping("/{id}")
    public Organizador updateUser(@PathVariable Long id, @RequestBody Organizador organizador) {
        organizador.setId(id);
        return organizadorService.save(organizador);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        organizadorService.deleteById(id);
    }
}
