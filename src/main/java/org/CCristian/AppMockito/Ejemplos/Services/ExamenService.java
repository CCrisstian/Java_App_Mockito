package org.CCristian.AppMockito.Ejemplos.Services;

import org.CCristian.AppMockito.Ejemplos.Models.Examen;

import java.util.Optional;

public interface ExamenService {
/*----------------MÉTODOS----------------*/
Optional<Examen> finExamenPorNombre(String nombre);

Examen findExamenPorNombreConPreguntas(String nombre);
}
