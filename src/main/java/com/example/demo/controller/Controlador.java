package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.UsuariosDAO;
import com.example.demo.entity.Usuarios;

@RestController
@RequestMapping("/")
public class Controlador {
	@Autowired
	UsuariosDAO usuariosDAO;
	
	@GetMapping("/")
	public ResponseEntity<List<Usuarios>> listShops() {
		return ResponseEntity.ok(usuariosDAO.findAll());
	}
}
