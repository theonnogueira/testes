package org.com.brkdesenvolvimento.brk.controller;

import java.util.List;


import org.com.brkdesenvolvimento.brk.models.Automovel;
import org.com.brkdesenvolvimento.brk.repository.AutomovelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/automoveis")
@CrossOrigin(origins = "*",allowedHeaders="*")
public class AutomovelController {
	
	@Autowired
	public AutomovelRepository repository;
	
	@GetMapping
	public ResponseEntity<List<Automovel>> GetAll(){
		return ResponseEntity.ok(repository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Automovel> GetById(@PathVariable long id){
		return repository.findById(id)
				.map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
	}

	@PostMapping
	public ResponseEntity<Automovel> post (@RequestBody Automovel automovel){
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(automovel));
	}
	
	@PutMapping
	public ResponseEntity<Automovel> put (@RequestBody Automovel automovel){
		return ResponseEntity.status(HttpStatus.OK).body(repository.save(automovel));
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable long id) {
		repository.deleteById(id);
	}
	
	
}
