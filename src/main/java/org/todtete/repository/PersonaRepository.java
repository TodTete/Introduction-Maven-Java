package org.todtete.repository;

import org.todtete.model.Persona;

import java.util.List;
import java.util.Optional;

/**
 * Contrato de acceso a datos de {@link Persona}.
 *
 * <p>Al ser una interfaz, en las pruebas se puede simular con Mockito.
 */
public interface PersonaRepository {

    void guardar(Persona persona);

    Optional<Persona> buscarPorEmail(String email);

    List<Persona> listar();
}
