package com.example.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.ItemsViaje;

public interface ItemsViajeDAO extends JpaRepository<ItemsViaje, Integer> {
    public List<ItemsViaje> findAllByIdviaje(int idviaje);
}