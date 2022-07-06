package com.example.demo.entity;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name= "viajes")
public class Viajes {
    @Id
    @Column(name = "idviaje")    
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
    private String nombre;
    private String descripcion;
    @Column(name = "fechainicio") 
    private Date fechaInicio;
    @Column(name = "fechafin") 
    private Date fechaFin;
    @Column(name = "usuarios_idusuario")
    private int idUsuarios;

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return this.descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechaInicio() {
        return this.fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public   Date getFechaFin() {
        return this.fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public int getUsuarios_idUsuarios() {
        return this.idUsuarios;
    }

    public void setUsuarios_idUsuarios(int Usuarios_idUsuarios) {
        this.idUsuarios = Usuarios_idUsuarios;
    }    
}
