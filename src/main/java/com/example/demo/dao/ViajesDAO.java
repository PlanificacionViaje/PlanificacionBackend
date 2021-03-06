package com.example.demo.dao;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Viajes;

public interface ViajesDAO extends JpaRepository<Viajes, Integer> {
    public List<Viajes> findAllByIdusuarios(int idusuarios);
    public List<Viajes> findAllByIdusuariosOrderByFechainicio(int idusuarios);
    public List<Viajes> findAllByIdusuariosAndFechainicioAfterOrderByFechainicio(int idusuarios, Date fechainicio);
}
