package org.todtete.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.todtete.model.Persona;
import org.todtete.repository.PersonaRepository;

import java.util.List;

/**
 * Lógica de negocio sobre personas.
 *
 * <p>Recibe el repositorio por constructor (inyección de dependencias),
 * lo que permite reemplazarlo por un mock en las pruebas.
 */
public class PersonaService {

    private static final Logger log = LoggerFactory.getLogger(PersonaService.class);

    private final PersonaRepository repository;

    public PersonaService(PersonaRepository repository) {
        this.repository = repository;
    }

    /**
     * Registra una persona si su email no existe todavía.
     *
     * @throws IllegalStateException si el email ya está registrado.
     */
    public void registrar(Persona persona) {
        if (repository.buscarPorEmail(persona.email()).isPresent()) {
            throw new IllegalStateException("Ya existe una persona con email " + persona.email());
        }
        repository.guardar(persona);
        log.debug("Persona registrada: {}", persona);
    }

    public List<Persona> listar() {
        return repository.listar();
    }

    public List<Persona> mayoresDeEdad() {
        return repository.listar().stream()
                .filter(Persona::esMayorDeEdad)
                .toList();
    }
}
