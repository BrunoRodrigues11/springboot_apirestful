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

import com.example.springboot.dtos.FornecedorRecord;
import com.example.springboot.models.FornecedorModel;
import com.example.springboot.repositories.FornecedorRepository;

import jakarta.validation.Valid;

@RestController
public class FornecedorController {
    @Autowired
	FornecedorRepository FornecedorRepository;
	
	@GetMapping("/fornecedores")
	public ResponseEntity<List<FornecedorModel>> getAllClientes(){
		List<FornecedorModel> fornecedoresList = FornecedorRepository.findAll();
		return ResponseEntity.status(HttpStatus.OK).body(fornecedoresList);
	}

	@GetMapping("/fornecedores/{id}")
	public ResponseEntity<Object> getOneCliente(@PathVariable(value="id") UUID id){
		Optional<FornecedorModel> fornecedorO = FornecedorRepository.findById(id);
		return ResponseEntity.status(HttpStatus.OK).body(fornecedorO.get());
	}
	
	@PostMapping("/fornecedores")
	public ResponseEntity<FornecedorModel> saveCliente(@RequestBody @Valid FornecedorRecord FornecedorRecord) {
		var FornecedorModel = new FornecedorModel();
		BeanUtils.copyProperties(FornecedorRecord, FornecedorModel);
		return ResponseEntity.status(HttpStatus.CREATED).body(FornecedorRepository.save(FornecedorModel));
	}
	
	@DeleteMapping("/fornecedores/{id}")
	public ResponseEntity<Object> deleteCliente(@PathVariable(value="id") UUID id) {
		Optional<FornecedorModel> fornecedorO = FornecedorRepository.findById(id);
		return ResponseEntity.status(HttpStatus.OK).body("Fornecedor deleted successfully.");
	}
	
	@PutMapping("/fornecedores/{id}")
	public ResponseEntity<Object> updateCliente(@PathVariable(value="id") UUID id,
													  @RequestBody @Valid FornecedorRecord FornecedorRecord) {
		Optional<FornecedorModel> fornecedorO = FornecedorRepository.findById(id);
		var FornecedorModel = fornecedorO.get();
		BeanUtils.copyProperties(FornecedorRecord, FornecedorModel);
		return ResponseEntity.status(HttpStatus.OK).body(FornecedorRepository.save(FornecedorModel));
	}
}
