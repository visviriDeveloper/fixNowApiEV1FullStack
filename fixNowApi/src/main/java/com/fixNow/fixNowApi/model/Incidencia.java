package com.fixNow.fixNowApi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

@Entity
public class Incidencia {
    /**
     * Clase principal, la cual se encarga de definir los atributos de la entidad. No contiene logica de negocios.
     */

    // Gracias a la notacion @Id y @GeneratedValue el id es autogenerativo.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotBlank(message = "El campo titulo no puede estar vacio")
    //Se pedia que el atributo titulo tuviera una longitud maxima de 100 caracteres.
    @Size(max = 100, message = "El titulo no puede superar los 100 caracteres.")

    /**
     * El uso de @Column es para darle una capa adicional de seguridad a nuestra API, al estar trabajando directamente con H2 y el JPA,
     * podemos definir los atributos de la TABLA (entidad) Incidencia, haciendo que los atributos no sean nulo
     */
    @Column(length = 100, nullable = false)
    private String titulo;

    @NotBlank(message = "La descripcion no puede estar en blanco.")
    @Column(nullable = false)
    private String descripcion;
    /**
     * Segun lo que indage sobre los Enum, es preferible "transformarlo" a un tipo String en vez de ocuparlo con un valor numerico
     *como si fuera un indice. Esto es por si agregaramos o quitaramos un nuevo Estado o Prioridad no queden desplazados los valores numericos.
     */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Estado estado;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Prioridad prioridad;

    @NotBlank(message = "El campo usuario reportante no puede estar vacio.")
    @Column(nullable = false)
    private String usuarioReportante;

    @Column(nullable = false, updatable = false)
    private LocalDateTime fechaRegistro;

    /**
     * Constructor vacio necesario por JPA
     */
    public Incidencia() {}
    // Constructor sobrecargado, sin los atributos de Id ni de fechaRegistro.
    public Incidencia(String titulo, String descripcion, Estado estado, Prioridad prioridad, String usuarioReportante) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.estado = estado;
        this.prioridad = prioridad;
        this.usuarioReportante = usuarioReportante;
    }
    //Getters y Setters (Importantisimos para que funciona nuestra API)
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
