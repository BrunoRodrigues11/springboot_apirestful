package com.example.springboot.controllers;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.springboot.dtos.UserRecordDto;
import com.example.springboot.models.UserModel;
import com.example.springboot.repositories.UserRepository;

import jakarta.validation.Valid;

@RestController
public class UserController {
    @Autowired
	UserRepository UserRepository;
	
	@GetMapping("/usuarios")
	public ResponseEntity<List<UserModel>> getAllUsuarios(){
		List<UserModel> usuariosList = UserRepository.findAll();
		return ResponseEntity.status(HttpStatus.OK).body(usuariosList);
	}

	@GetMapping("/usuarios/{id}")
	public ResponseEntity<Object> getOneUsuario(@PathVariable(value="id") UUID id){
		Optional<UserModel> usuarioO = UserRepository.findById(id);
		return ResponseEntity.status(HttpStatus.OK).body(usuarioO.get());
	}
	
	@PostMapping("/usuarios")
	public ResponseEntity<UserModel> saveUsuario(@RequestBody @Valid UserRecordDto UserRecordDto) {
		var UserModel = new UserModel();
		BeanUtils.copyProperties(UserRecordDto, UserModel);
		return ResponseEntity.status(HttpStatus.CREATED).body(UserRepository.save(UserModel));
	}
	
	@DeleteMapping("/usuarios/{id}")
	public ResponseEntity<Object> deleteUsuario(@PathVariable(value="id") UUID id) {
		Optional<UserModel> usuarioO = UserRepository.findById(id);
		return ResponseEntity.status(HttpStatus.OK).body("Usuario deleted successfully.");
	}
	
	@PutMapping("/usuarios/{id}")
	public ResponseEntity<Object> updateUsuario(@PathVariable(value="id") UUID id, @RequestBody @Valid UserRecordDto UserRecordDto) {
		Optional<UserModel> usuarioO = UserRepository.findById(id);
		var UserModel = usuarioO.get();
		BeanUtils.copyProperties(UserRecordDto, UserModel);
		return ResponseEntity.status(HttpStatus.OK).body(UserRepository.save(UserModel));
	} 
}
