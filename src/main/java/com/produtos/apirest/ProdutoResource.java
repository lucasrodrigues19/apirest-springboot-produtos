package com.produtos.apirest;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.produtos.apirest.models.Produto;
import com.produtos.apirest.repository.ProdutoRepository;

@RestController
@RequestMapping(value = "/api")
public class ProdutoResource {

	ProdutoRepository prodRepo;
	
	@Autowired
	public ProdutoResource(ProdutoRepository prodRepo) {
		this.prodRepo = prodRepo;
	}
	
	@GetMapping(value = "/produtos")
	public ResponseEntity<List<Produto>> listaProdutos(){
		return ResponseEntity.ok().body(prodRepo.findAll());
	}
	
	@GetMapping(value = "/produtos/{id}")
	public ResponseEntity <Produto> listaProdutosUnico(@PathVariable Long id){
		Optional<Produto> obj = prodRepo.findById(id);
		return ResponseEntity.ok().body(obj.get());
	}
	@PostMapping("/produtos")
	public ResponseEntity<Produto> salvar(@RequestBody Produto prod){
		Produto obj = prodRepo.save(prod);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(prod);
	}
	@DeleteMapping("/produtos")
	public ResponseEntity<Produto> delete(@RequestBody Produto prod){
		prodRepo.delete(prod);
		return ResponseEntity.noContent().build();
	}
	@PutMapping("/produtos")
	public ResponseEntity<Produto> update(@RequestBody Produto prod){
		Produto obj = prodRepo.save(prod);
		return ResponseEntity.ok().body(obj);
	}
}
