package org.CCristian.AppMockito.Ejemplos.Services;

import org.CCristian.AppMockito.Ejemplos.Models.Examen;
import org.CCristian.AppMockito.Ejemplos.Repositories.Examen_Repository;
import java.util.Optional;

public class Examen_Services_Impl implements Examen_Service{

    private Examen_Repository examenRepository;

    public Examen_Services_Impl(Examen_Repository examenRepository) {
        this.examenRepository = examenRepository;
    }

    @Override
    public Examen finExamenPorNombre(String nombre) {
        Optional<Examen> examenOptional = examenRepository.finAll()
                                                            .stream()
                                                            .filter(e -> e.getNombre().contains(nombre))
                                                            .findFirst();
        Examen examen = null;
        if (examenOptional.isPresent()){
            examen = examenOptional.orElseThrow();
        }
        return examen;
    }
}
