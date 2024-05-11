package org.CCristian.AppMockito.Ejemplos.Repositories;

import org.CCristian.AppMockito.Ejemplos.Models.Examen;

import java.util.List;

public interface ExamenRepository {
/*----------------MÉTODOS----------------*/
    List<Examen> finAll();

    Examen guardar(Examen examen);
}
