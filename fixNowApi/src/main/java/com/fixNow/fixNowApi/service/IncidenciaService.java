package com.fixNow.fixNowApi.service;

import com.fixNow.fixNowApi.model.Estado;
import com.fixNow.fixNowApi.model.Incidencia;
import com.fixNow.fixNowApi.repository.IncidenciaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class IncidenciaService {

    private final IncidenciaRepository incidenciaRepository;

    public IncidenciaService(IncidenciaRepository incidenciaRepository) {
        this.incidenciaRepository = incidenciaRepository;
    }

    // Listar todas las incidencias
    public List<Incidencia> listarIncidencias() {
        return incidenciaRepository.findAll();
    }

    // Listar incidencias por id
    public Optional<Incidencia> buscarIncidenciaPorId(Integer id) {
        return incidenciaRepository.findById(id);
    }

    public List<Incidencia> buscarIncidenciasPorEstado(Estado estado) {
        return incidenciaRepository.findByEstado(estado);
    }

    // Registar una nueva incidencia
    public Incidencia guardarIncidencia(Incidencia incidencia) {
        incidencia.setFechaRegistro(LocalDateTime.now());
        return incidenciaRepository.save(incidencia);
    }

    // Actualizar registro completo de model (incidencia)
    public  Optional<Incidencia> actualizarIncidencia(Integer id, Incidencia incidenciaActualizada) {
        return incidenciaRepository.findById(id).map(incidencia ->{
            incidencia.setTitulo(incidenciaActualizada.getTitulo());
            incidencia.setDescripcion(incidenciaActualizada.getDescripcion());
            incidencia.setEstado(incidenciaActualizada.getEstado());
            incidencia.setPrioridad(incidenciaActualizada.getPrioridad());
            incidencia.setUsuarioReportante(incidenciaActualizada.getUsuarioReportante());
            return incidenciaRepository.save(incidencia);
        });
    }

    // Eliminar registro de incidencia
    public boolean eliminarIncidencia(Integer id) {
        boolean encontrado = incidenciaRepository.existsById(id);
        if (!encontrado) {
            return false;
        }
        incidenciaRepository.deleteById(id);
        return true;
    }
}
