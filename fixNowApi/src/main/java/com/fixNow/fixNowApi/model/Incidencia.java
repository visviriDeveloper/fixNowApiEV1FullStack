package com.fixNow.fixNowApi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

@Entity
public class Incidencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotBlank(message = "El titulo no puede estar en blanco.")
    @Size(max = 100, message = "El titulo no puede superar los 100 caracteres.")
    @Column(length = 100, nullable = false)
    private String titulo;

    @NotBlank(message = "La descripcion no puede estar en blanco.")
    @Column(nullable = false)
    private String descripcion;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Estado estado;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Prioridad prioridad;

    @NotBlank(message = "El nombre del usuario no puede estar vacio.")
    @Column(nullable = false)
    private String usuarioReportante;

    @Column(nullable = false, updatable = false)
    private LocalDateTime fechaRegistro;

    public Incidencia() {}

    public Incidencia(String titulo, String descripcion, Estado estado, Prioridad prioridad, String usuarioReportante) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.estado = estado;
        this.prioridad = prioridad;
        this.usuarioReportante = usuarioReportante;
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
