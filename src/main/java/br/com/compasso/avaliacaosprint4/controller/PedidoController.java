package br.com.compasso.avaliacaosprint4.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
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

import br.com.compasso.avaliacaosprint4.controller.dto.PedidoDto;
import br.com.compasso.avaliacaosprint4.controller.form.PedidoForm;
import br.com.compasso.avaliacaosprint4.model.Pedido;
import br.com.compasso.avaliacaosprint4.repository.PedidoRepository;
import br.com.compasso.avaliacaosprint4.repository.ProdutoRepository;

@RestController
@RequestMapping("/protected/pedido")
public class PedidoController {

	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@GetMapping
	@Cacheable(value = "listaDePedidos")
	public List<PedidoDto> listar() {
		List<Pedido> pedidos = pedidoRepository.findAll();
		
		return PedidoDto.converter(pedidos);
	}
	
	@PostMapping
	@Transactional
	@CacheEvict(value = "listaDePedidos", allEntries = true)
	public ResponseEntity<PedidoDto> cadastrar(
			@Valid @RequestBody PedidoForm pedidoForm,
			UriComponentsBuilder uriBuilder) {
		
		Pedido pedido = pedidoForm.converter(produtoRepository);
		pedidoRepository.save(pedido);

		URI uri = uriBuilder.path("/protected/pedido/{id}")
							.buildAndExpand(pedido.getId())
							.toUri();
		
		return ResponseEntity.created(uri).body(new PedidoDto(pedido));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<PedidoDto> detalhar(@PathVariable Long id) {
		Optional<Pedido> pedido = pedidoRepository.findById(id);
		
		if (pedido.isPresent()) {
			return ResponseEntity.ok(new PedidoDto(pedido.get()));
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@PutMapping("/{id}")
	@Transactional
	@CacheEvict(value = "listaDePedidos", allEntries = true)
	public ResponseEntity<PedidoDto> atualizar(@PathVariable Long id,
			@Valid @RequestBody PedidoForm pedidoForm) {
		
		Pedido pedido = pedidoForm.atualizar(id, pedidoRepository, produtoRepository);
		
		if (pedido != null) {
			return ResponseEntity.ok(new PedidoDto(pedido));
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("{id}")
	@Transactional
	@CacheEvict(value = "listaDePedidos", allEntries = true)
	public ResponseEntity<?> remover(@PathVariable Long id) {
		Optional<Pedido> pedido = pedidoRepository.findById(id);
		
		if (pedido.isPresent()) {
			pedidoRepository.deleteById(id);
			
			return ResponseEntity.ok().build();
		}
		
		return ResponseEntity.notFound().build();
	}
	
}
