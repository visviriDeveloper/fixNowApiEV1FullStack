package com.fixNow.fixNowApi.repository;

import com.fixNow.fixNowApi.model.Estado;
import com.fixNow.fixNowApi.model.Incidencia;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IncidenciaRepository extends JpaRepository<Incidencia, Integer> {

    List<Incidencia> findByEstado(Estado estado);
}
