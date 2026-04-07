package com.fixNow.fixNowApi.repository;

import com.fixNow.fixNowApi.model.Estado;
import com.fixNow.fixNowApi.model.Incidencia;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


// Aca ocurre la magia, el JPA me proporciona un CRUD para poder realizar todas las operaciones necesarias.
public interface IncidenciaRepository extends JpaRepository<Incidencia, Integer> {

    // Jpa me permite crear una consulta personalizada a la DB, en este caso la busqueda por estado.
    List<Incidencia> findByEstado(Estado estado);
}
