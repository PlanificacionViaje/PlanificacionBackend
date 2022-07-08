package com.example.demo.entity;

import java.sql.Time;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "itemsviaje")
public class ItemsViaje {
    @Id
    @Column(name = "iditemsviaje")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nombre;
    private String descripcion;
    private Time hora;
    private double precio;
    private double ubicacionlatitud;
    private double ubicacionlongitud;
    @Column(name = "viajes_idviaje")
    private int idviaje;

    public void setUbicacionlatitud(double ubicacionlatitud) {
        this.ubicacionlatitud = ubicacionlatitud;
    }

    public void setUbicacionlongitud(double ubicacionlongitud) {
        this.ubicacionlongitud = ubicacionlongitud;
    }

    public void setIdviajes(int idviaje) {
        this.idviaje = idviaje;
    }

    public double getUbicacionlatitud() {
        return ubicacionlatitud;
    }

    public double getUbicacionlongitud() {
        return ubicacionlongitud;
    }

    public int getIdviajes() {
        return idviaje;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Time getHora() {
        return hora;
    }

    public double getPrecio() {
        return precio;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setHora(Time hora) {
        this.hora = hora;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

}
