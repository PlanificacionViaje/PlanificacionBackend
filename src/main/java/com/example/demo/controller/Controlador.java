package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.UsuariosDAO;
import com.example.demo.dao.ViajesDAO;
import com.example.demo.entity.Usuarios;
import com.example.demo.entity.Viajes;

@RestController
@RequestMapping("/")
public class Controlador {
	@Autowired
	UsuariosDAO usuariosDAO;
	@Autowired
	ViajesDAO viajesDAO;
	
	@GetMapping("/usuarios")
	public ResponseEntity<List<Usuarios>> listUsuarios() {
		return ResponseEntity.ok(usuariosDAO.findAll());
	}

	@GetMapping("/viajes")
	public ResponseEntity<List<Viajes>> listViajes() {
		return ResponseEntity.ok(viajesDAO.findAll());
	}
}
