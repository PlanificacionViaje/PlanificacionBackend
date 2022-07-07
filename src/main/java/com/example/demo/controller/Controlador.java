package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.UsuariosDAO;
import com.example.demo.dao.ViajesDAO;
import com.example.demo.entity.Usuarios;
import com.example.demo.entity.Viajes;


import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;


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

	@PostMapping("/viajes/{idusuario}")
    public ResponseEntity<Viajes> createViaje(Viajes viaje,@PathVariable int idusuario) {
        try {
            if (!usuariosDAO.existsById(idusuario)) {
                return ResponseEntity.notFound().build();
            }
            viaje.setUsuarios_idUsuarios(idusuario);
            viajesDAO.save(viaje);
            return ResponseEntity.ok(viaje);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

	@PutMapping("/viajes")
	public ResponseEntity<Viajes> updateViaje(Viajes viaje) {
		try {
			if (!viajesDAO.existsById(viaje.getId())) {
				return ResponseEntity.notFound().build();
			}
			
			viajesDAO.save(viaje);
			return ResponseEntity.ok(viaje);
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/viajes/{id}")
	public ResponseEntity<Viajes> deleteViaje(@PathVariable int id) {
		if (!viajesDAO.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		viajesDAO.deleteById(id);
		return ResponseEntity.ok().build();
	}
}
