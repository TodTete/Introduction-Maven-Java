package org.todtete.repository;

import org.todtete.model.Persona;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/** Implementación simple que guarda las personas en un {@link Map} en memoria. */
public class PersonaRepositoryEnMemoria implements PersonaRepository {

    private final Map<String, Persona> personas = new LinkedHashMap<>();

    @Override
    public void guardar(Persona persona) {
        personas.put(persona.email(), persona);
    }

    @Override
    public Optional<Persona> buscarPorEmail(String email) {
        return Optional.ofNullable(personas.get(email));
    }

    @Override
    public List<Persona> listar() {
        return new ArrayList<>(personas.values());
    }
}
