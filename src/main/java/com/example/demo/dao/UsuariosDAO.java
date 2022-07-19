package com.example.demo.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Usuarios;

public interface UsuariosDAO extends JpaRepository<Usuarios, Integer> {
    public boolean existsByCorreo(String correo);
    public Optional<Usuarios> findByCorreoIgnoreCaseAndContrasena(String correo, String contrasena);
}
