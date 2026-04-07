package com.fixNow.fixNowApi.controller;

import com.fixNow.fixNowApi.model.Estado;
import com.fixNow.fixNowApi.model.Incidencia;
import com.fixNow.fixNowApi.service.IncidenciaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("api/v1/incidencias")
public class IncidenciaController {

    private final IncidenciaService incidenciaService;

    /**
     * Controller que se encarga de gestionar las request de los usuarios y de proporcionar la response
     * correspondiente para cada peticion mediante un formato JSON, clave y valor.
     *
     * El controller no tiene acceso a la logica de negocio, simplemente utiliza al service
     * para podeer cumplir con su cometido.
     *
     * Se le inyecta como atributo al Service: incidenciaService
     *
     */
    public IncidenciaController(IncidenciaService incidenciaService) {
        this.incidenciaService = incidenciaService;
    }

    // METODOS GET (LISTAR TODAS , POR ID Y ESTADO)

    // Retorna la lista completa de incidencias.
    @GetMapping
    public ResponseEntity<?> listarIncidencias() {
        try{
            List<Incidencia> incidencias = incidenciaService.listarIncidencias();
            return ResponseEntity.ok(incidencias); // Retorna estado 200;
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al obtener las incidencias");
        }
    }

    // Retorna la incidencia buscada por id.
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarIncidenciaPorId(@PathVariable Integer id) {
        try {
            Incidencia incidenciaBuscada = incidenciaService.buscarIncidenciaPorId(id)
                    .orElseThrow(() -> new NoSuchElementException("Incidencia no encontrada"));
            return ResponseEntity.ok(incidenciaBuscada);
        } catch (NoSuchElementException e) {
            // Capturamos específicamente el 404 antes de la excepcion generica
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al obtener la incidencia por ID: " + id);
        }
    }

    // Retorna incidencias filtradas por estado.
    @GetMapping("/estado/{estado}")
    public ResponseEntity<?> buscarIncidenciaPorEstado(@PathVariable Estado estado) {
        try{
            List<Incidencia> listaIncidencias = incidenciaService.buscarIncidenciasPorEstado(estado);
            return ResponseEntity.ok(listaIncidencias);
        }catch(Exception e){
            //retorna estado 500 (error db)
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al obtener las incidencias por estado: " + estado);

        }
    }

    /*
    El @Valid sera ocupado para poder utilizar el Validation haciendo que los campos, en caso de ser un tipo de dato primitivo, no puedan estar vacios o null.
     */
    // METODO POST
    // Agregar nueva incidencia.
    @PostMapping
    public ResponseEntity<?> agregarIncidencia(@Valid @RequestBody Incidencia incidencia) {
        try{
            Incidencia nuevaIncidencia = incidenciaService.guardarIncidencia(incidencia);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevaIncidencia);
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al agregar incidencia: " + incidencia);
        }
    }

    // METODO PUT
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarIncidencia(@PathVariable Integer id,@Valid @RequestBody Incidencia incidenciaActualizada) {
        try{
            Incidencia incidencia = incidenciaService.actualizarIncidencia(id,incidenciaActualizada)
                    .orElseThrow(() -> new NoSuchElementException("Incidencia no encontrada por ID: " + id));
            return ResponseEntity.ok(incidencia);
        }catch (NoSuchElementException e){
            // Capturo el codigo 404
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Incidencia no encontrada por ID: " + id);
        }catch(Exception e){
            //Envio codigo 500 (generico)
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al actualizar incidencia ");
        }
    }
    // METODO DELETE
    // Eliminar incidencia por id.
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarIncidencia(@PathVariable Integer id) {
        try{
            if(incidenciaService.eliminarIncidencia(id)){
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("La Incidencia buscada no ha sido encontrada");
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al eliminar incidencia: " + id);
        }
    }

}
