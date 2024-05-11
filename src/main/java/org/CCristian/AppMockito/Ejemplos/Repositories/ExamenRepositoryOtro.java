package org.CCristian.AppMockito.Ejemplos.Repositories;

import org.CCristian.AppMockito.Ejemplos.Models.Examen;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class ExamenRepositoryOtro implements ExamenRepository {
    @Override
    public List<Examen> finAll() {
        try {
            System.out.println("Examen_RepositoryOtro");
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public Examen guardar(Examen examen) {
        return null;
    }
}
