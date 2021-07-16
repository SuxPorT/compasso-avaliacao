package br.com.alura.avaliacaosprint4.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

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
import org.springframework.web.util.UriComponentsBuilder;

import br.com.alura.avaliacaosprint4.controller.dto.ProdutoDto;
import br.com.alura.avaliacaosprint4.controller.form.ProdutoForm;
import br.com.alura.avaliacaosprint4.model.Produto;
import br.com.alura.avaliacaosprint4.repository.ProdutoRepository;

@RestController
@RequestMapping("/protected/produto")
public class ProdutoController {

	@Autowired
	private ProdutoRepository produtoRepository;
	
	@GetMapping
	public List<ProdutoDto> listar() {
		List<Produto> produtos = produtoRepository.findAll();
		
		return ProdutoDto.converter(produtos);
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<ProdutoDto> cadastrar(@Valid @RequestBody ProdutoForm produtoForm,
			UriComponentsBuilder uriBuilder) {
		
		Produto produto = produtoForm.converter(produtoRepository);
		produtoRepository.save(produto);
		
		URI uri = uriBuilder.path("/protected/produto/{id}")
							.buildAndExpand(produto.getId())
							.toUri();
		
		return ResponseEntity.created(uri).body(new ProdutoDto(produto));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ProdutoDto> detalhar(@PathVariable Long id) {
		Optional<Produto> produto = produtoRepository.findById(id);
		
		if (produto.isPresent()) {
			return ResponseEntity.ok(new ProdutoDto(produto.get()));
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<ProdutoDto> atualizar(@PathVariable Long id,
			@Valid @RequestBody ProdutoForm produtoForm) {
		
		Produto produto = produtoForm.atualizar(id, produtoRepository);
		
		if (produto != null) {
			return ResponseEntity.ok(new ProdutoDto(produto));
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("{id}")
	@Transactional
	public ResponseEntity<?> remover(@PathVariable Long id) {
		Optional<Produto> optional = produtoRepository.findById(id);
		
		if (optional.isPresent()) {
			produtoRepository.deleteById(id);
			
			return ResponseEntity.ok().build();
		}
		
		return ResponseEntity.notFound().build();
	}
	
}
