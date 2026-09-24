package org.todtete.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.todtete.model.Persona;
import org.todtete.repository.PersonaRepository;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Pruebas con Mockito: el repositorio se reemplaza por un mock para probar
 * solo la lógica del servicio, sin depender de la implementación real.
 */
@ExtendWith(MockitoExtension.class)
class PersonaServiceTest {

    @Mock
    private PersonaRepository repository;   // Objeto simulado

    @InjectMocks
    private PersonaService service;         // Recibe el mock por constructor

    private final Persona ana = new Persona("Ana", "ana@correo.com", 28);
    private final Persona luis = new Persona("Luis", "luis@correo.com", 17);

    @Test
    void registraPersonaNueva() {
        // Given (preparación): el email no existe
        when(repository.buscarPorEmail(ana.email())).thenReturn(Optional.empty());

        // When (acción)
        service.registrar(ana);

        // Then (verificación): se llamó a guardar con esa persona
        verify(repository).guardar(ana);
    }

    @Test
    void noRegistraEmailDuplicado() {
        when(repository.buscarPorEmail(ana.email())).thenReturn(Optional.of(ana));

        assertThatThrownBy(() -> service.registrar(ana))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining(ana.email());

        verify(repository, never()).guardar(any());
    }

    @Test
    void filtraMayoresDeEdad() {
        when(repository.listar()).thenReturn(List.of(ana, luis));

        assertThat(service.mayoresDeEdad())
                .hasSize(1)
                .containsExactly(ana);
    }

    @Test
    void listaDelegaEnRepositorio() {
        when(repository.listar()).thenReturn(List.of(ana));

        assertThat(service.listar()).containsExactly(ana);
    }
}
