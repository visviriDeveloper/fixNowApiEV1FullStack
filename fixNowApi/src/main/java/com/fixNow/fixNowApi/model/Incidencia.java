package com.fixNow.fixNowApi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

@Entity
public class Incidencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotBlank(message = "El titulo no puede estar en blanco.")
    private String titulo;
    @NotBlank(message = "La descripcion no puede estar en blanco.")
    private String descripcion;
    private Estado estado;
    private Prioridad prioridad;
    @NotBlank(message = "El nombre del usuario no puede estar vacio.")
    private String usuarioReportante;
    private LocalDateTime fechaRegistro;

    public Incidencia() {}

    public Incidencia(String titulo, String descripcion, Estado estado, Prioridad prioridad, String usuarioReportante) {
        if(titulo.length() <= 100){
            this.titulo = titulo;
        }
        this.descripcion = descripcion;
        this.estado = estado;
        this.prioridad = prioridad;
        this.usuarioReportante = usuarioReportante;
        this.fechaRegistro = LocalDateTime.now();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public Prioridad getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(Prioridad prioridad) {
        this.prioridad = prioridad;
    }

    public String getUsuarioReportante() {
        return usuarioReportante;
    }

    public void setUsuarioReportante(String usuarioReportante) {
        this.usuarioReportante = usuarioReportante;
    }

    public LocalDateTime getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDateTime fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
}
