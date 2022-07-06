package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.UsuariosDAO;
import com.example.demo.entity.Usuarios;

@RestController
@RequestMapping("/")
public class Controlador {
	@Autowired
	UsuariosDAO usuariosDAO;
	
	@GetMapping("/usuarios/{id}")
	public ResponseEntity<Optional<Usuarios>> findUser(@PathVariable int id) {
		return ResponseEntity.ok(usuariosDAO.findById(id));
	}

	@GetMapping("/usuarios/")
	public ResponseEntity<List<Usuarios>> listUsers() {
		return ResponseEntity.ok(usuariosDAO.findAll());
	}

	@PostMapping("/usuarios/")
	public ResponseEntity<Usuarios> createUser(Usuarios usuario) {
		try {
			if (usuariosDAO.existsById(usuario.getId())) {
				return ResponseEntity.notFound().build();
			}

			usuariosDAO.save(usuario);
			return ResponseEntity.ok(usuario);
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}

	@PutMapping("/usuarios/")
	public ResponseEntity<Usuarios> updateUser(Usuarios usuario) {
		try {
			if (!usuariosDAO.existsById(usuario.getId())) {
				return ResponseEntity.notFound().build();
			}

			usuariosDAO.save(usuario);
			return ResponseEntity.ok(usuario);
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}
}
