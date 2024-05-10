package org.CCristian.AppMockito.Ejemplos.Repositories;

import org.CCristian.AppMockito.Ejemplos.Models.Examen;

import java.util.Arrays;
import java.util.List;

public class Examen_Repository_Impl implements Examen_Repository{
    @Override
    public List<Examen> finAll() {
        return Arrays.asList(
                new Examen(5L, "Matemáticas"),
                new Examen(6L,"Lenguaje"),
                new Examen(7L,"Historia"));
    }
}
