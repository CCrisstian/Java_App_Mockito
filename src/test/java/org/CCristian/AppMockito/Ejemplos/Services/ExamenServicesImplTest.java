package org.CCristian.AppMockito.Ejemplos.Services;

import org.CCristian.AppMockito.Ejemplos.Models.Examen;
import org.CCristian.AppMockito.Ejemplos.Repositories.ExamenRepository;
import org.CCristian.AppMockito.Ejemplos.Repositories.PreguntaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import static org.mockito.Mockito.*;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ExamenServicesImplTest {

    @Mock
    ExamenRepository repository;    /*MOCK -> MOCKITO*/
    @Mock
    PreguntaRepository preguntaRepository;  /*MOCK -> MOCKITO*/
    @InjectMocks
    ExamenServicesImpl service;  /*Inyecta los MOCK´s a service*/

//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);             /*Habilitar el uso de anotaciones de Mockito*/
////        repository = mock(ExamenRepository.class); /*MOCK -> MOCKITO*/
////        preguntaRepository = mock(PreguntaRepository.class); /*MOCK -> MOCKITO*/
////        service = new ExamenServicesImpl(repository, preguntaRepository);
//    }

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
        //Given
        when(repository.finAll()).thenReturn(Collections.emptyList());
        when(preguntaRepository.findPreguntasPorExamenId(anyLong())).thenReturn(Datos.PREGUNTAS);

        //When
        Examen examen = service.findExamenPorNombreConPreguntas("Matemáticas2");

        //Then
        assertNull(examen);
        verify(repository).finAll();    /*Verifica que se llame al método "findAll"*/
        verify(preguntaRepository).findPreguntasPorExamenId(5L);    /*Verifica que se llame al método "findPreguntasPorExamenId"*/
    }

    @Test
    void testGuardarExamen() {
        //Given (Pre-Condiciones del entorno de prueba)
        Examen newExamen = Datos.EXAMEN;
        newExamen.setPreguntas(Datos.PREGUNTAS);
        when(repository.guardar(any(Examen.class))).then(new Answer<Examen>() {
            Long secuencia = 8L;
            @Override
            public Examen answer(InvocationOnMock invocationOnMock) throws Throwable {
                Examen examen = invocationOnMock.getArgument(0);
                examen.setId(secuencia++);
                return examen;
            }
        });

        //When (Cuando se ejecuta un método real)
        Examen examen = service.guardar(newExamen);

        //Then (Se valida)
        assertNotNull(examen.getId());
        assertEquals(8L, examen.getId());
        assertEquals("Física",examen.getNombre());

        verify(repository).guardar(any(Examen.class));
        verify(preguntaRepository).guardarVarias(anyList());
    }
}