package org.CCristian.AppMockito.Ejemplos.Services;

import org.CCristian.AppMockito.Ejemplos.Models.Examen;
import org.CCristian.AppMockito.Ejemplos.Repositories.ExamenRepository;
import org.CCristian.AppMockito.Ejemplos.Repositories.PreguntaRepository;

import java.util.List;
import java.util.Optional;

public class ExamenServicesImpl implements ExamenService {

    private ExamenRepository examenRepository;
    private PreguntaRepository preguntaRepository;

    public ExamenServicesImpl(ExamenRepository examenRepository, PreguntaRepository preguntaRepository) {
        this.examenRepository = examenRepository;
        this.preguntaRepository = preguntaRepository;
    }

    @Override
    public Optional<Examen> finExamenPorNombre(String nombre) {
        return examenRepository.finAll()
                                .stream()
                                .filter(e -> e.getNombre().contains(nombre))
                                .findFirst();
    }

    @Override
    public Examen findExamenPorNombreConPreguntas(String nombre) {
        Optional<Examen> examenOptional = finExamenPorNombre(nombre);
        Examen examen = null;
        if (examenOptional.isPresent()){
            examen = examenOptional.orElseThrow();
            List<String> preguntas = preguntaRepository.findPreguntasPorExamenId(examen.getId());
            examen.setPreguntas(preguntas);
        }
        return examen;
    }

    @Override
    public Examen guardar(Examen examen) {
        if (!examen.getPreguntas().isEmpty()){
            preguntaRepository.guardarVarias(examen.getPreguntas());
        }
        return examenRepository.guardar(examen);
    }
}
