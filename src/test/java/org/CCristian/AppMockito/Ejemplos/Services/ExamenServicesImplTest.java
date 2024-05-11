package org.CCristian.AppMockito.Ejemplos.Services;

import org.CCristian.AppMockito.Ejemplos.Models.Examen;
import org.CCristian.AppMockito.Ejemplos.Repositories.ExamenRepository;
import org.CCristian.AppMockito.Ejemplos.Repositories.PreguntaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class ExamenServicesImplTest {

    ExamenRepository repository;
    ExamenService service;
    PreguntaRepository preguntaRepository;

    @BeforeEach
    void setUp() {
        repository = mock(ExamenRepository.class); /*MOCKITO*/
        preguntaRepository = mock(PreguntaRepository.class); /*MOCKITO*/
        service = new ExamenServicesImpl(repository, preguntaRepository);
    }

    @Test
    void findExamenPorNombre() {
        /*DATOS DE PRUEBA*/
        when(repository.finAll()).thenReturn(Datos.EXAMENES);
        /*'when' se usa para especificar el comportamiento esperado cuando se llama a un método en un mock.*/
        /*'thenReturn' indica qué valor debe devolver el método cuando se llame en el mock.*/
        /*repository.finAll() solo esta simulando el método 'findAll' pero no lo está ejecutando en realidad*/
        Optional<Examen> examen = service.finExamenPorNombre("Matemáticas");

        assertTrue(examen.isPresent());  /*Comprobando que 'examen' no sea null*/
        assertEquals(5L,examen.orElseThrow().getId());    /*Comprobando que el valor del 'Id' sea 5*/
        assertEquals("Matemáticas",examen.get().getNombre()); /*Comprobando que el valor del 'Nombre' sea Matemáticas*/
    }

    @Test
    void findExamenPorNombreListaVacia() {
        /*DATOS DE PRUEBA*/
        List<Examen> datos = Collections.emptyList();

        when(repository.finAll()).thenReturn(datos);
        Optional<Examen> examen = service.finExamenPorNombre("Matemáticas");
        assertFalse(examen.isPresent());
    }

    @Test
    void testPreguntasExamen() {
        when(repository.finAll()).thenReturn(Datos.EXAMENES);
        when(preguntaRepository.findPreguntasPorExamenId(anyLong())).thenReturn(Datos.PREGUNTAS);

        Examen examen = service.findExamenPorNombreConPreguntas("Matemáticas");

        assertEquals(5, examen.getPreguntas().size());
        assertTrue(examen.getPreguntas().contains("Integrales"));
    }

    @Test
    void testPreguntasExamenVerify() {
        when(repository.finAll()).thenReturn(Datos.EXAMENES);
        when(preguntaRepository.findPreguntasPorExamenId(anyLong())).thenReturn(Datos.PREGUNTAS);
        Examen examen = service.findExamenPorNombreConPreguntas("Matemáticas");
        assertEquals(5, examen.getPreguntas().size());
        assertTrue(examen.getPreguntas().contains("Integrales"));

        verify(repository).finAll();    /*Verifica que se llame al método "findAll"*/
        verify(preguntaRepository).findPreguntasPorExamenId(anyLong());    /*Verifica que se llame al método "findPreguntasPorExamenId"*/
    }

    @Test
    void testNoExisteExamenVerify() {
        when(repository.finAll()).thenReturn(Collections.emptyList());
        when(preguntaRepository.findPreguntasPorExamenId(anyLong())).thenReturn(Datos.PREGUNTAS);
        Examen examen = service.findExamenPorNombreConPreguntas("Matemáticas2");

        assertNull(examen);

        verify(repository).finAll();    /*Verifica que se llame al método "findAll"*/
        verify(preguntaRepository).findPreguntasPorExamenId(5L);    /*Verifica que se llame al método "findPreguntasPorExamenId"*/
    }
}