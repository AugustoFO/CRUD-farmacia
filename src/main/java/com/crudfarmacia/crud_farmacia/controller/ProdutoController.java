package com.crudfarmacia.crud_farmacia.controller;

import java.util.List;
import java.util.Optional;

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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.crudfarmacia.crud_farmacia.model.Categoria;
import com.crudfarmacia.crud_farmacia.model.Produto;
import com.crudfarmacia.crud_farmacia.repository.CategoriaRepository;
import com.crudfarmacia.crud_farmacia.repository.ProdutoRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/produtos")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProdutoController {
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@GetMapping
	public ResponseEntity<List<Produto>> buscaTodos(){
		
		return ResponseEntity.ok(produtoRepository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Produto> buscaPorId(@PathVariable Long id){
		
		return produtoRepository.findById(id)
				.map(produto -> ResponseEntity.ok(produto))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}
	
	@GetMapping("/nome/{nome}")
	public ResponseEntity<List<Produto>> buscaPorNome(@PathVariable String nome){
		
		return ResponseEntity.ok(produtoRepository.findAllByNomeProdutoContainingIgnoreCase(nome));
	}
	
	@PostMapping("/cadastrar")
	public ResponseEntity<Produto> criaProduto(@RequestBody Produto produto){
		
		Categoria categoria = categoriaRepository.findById(produto.getCategoria().getId())
		        .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Categoria não encontrada"));
		    produto.setCategoria(categoria);
		
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(produtoRepository.save(produto));
	}
	
	@PutMapping("/atualizar")
	public ResponseEntity<Produto> put(@Valid @RequestBody Produto produto){
		
		return produtoRepository.findById(produto.getId())
				.map(resposta -> ResponseEntity.status(HttpStatus.OK)
					.body(produtoRepository.save(produto)))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		
		Optional<Produto> postagem = produtoRepository.findById(id);
		
		if(postagem.isEmpty()) { throw new ResponseStatusException(HttpStatus.NOT_FOUND);}
		
		produtoRepository.deleteById(id);
	}
}
