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

import br.com.alura.avaliacaosprint4.controller.dto.PessoaDto;
import br.com.alura.avaliacaosprint4.controller.form.AtualizacaoPessoaForm;
import br.com.alura.avaliacaosprint4.controller.form.PessoaForm;
import br.com.alura.avaliacaosprint4.model.Pessoa;
import br.com.alura.avaliacaosprint4.repository.EnderecoRepository;
import br.com.alura.avaliacaosprint4.repository.PessoaRepository;

@RestController
@RequestMapping("/pessoa")
public class PessoaController {
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@GetMapping
	public List<PessoaDto> listar() {
		List<Pessoa> pessoas = pessoaRepository.findAll();
		
		return PessoaDto.converter(pessoas);
	}
	
	@PostMapping
	public ResponseEntity<PessoaDto> cadastrar(@Valid @RequestBody PessoaForm pessoaForm,
			UriComponentsBuilder uriBuilder) {
		
		Optional<Pessoa> pessoaEncontrada = 
				pessoaRepository.findById(pessoaForm.getCpf());
		
		if (pessoaEncontrada.isEmpty()) {
			Pessoa pessoa = pessoaForm.save(pessoaRepository, enderecoRepository);
			
			URI uri = uriBuilder.path("/pessoa/{id}")
								.buildAndExpand(pessoa.getCpf())
								.toUri();
			
			return ResponseEntity.created(uri).body(new PessoaDto(pessoa));
		}
		
		return ResponseEntity.badRequest().build();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<PessoaDto> detalhar(@PathVariable("id") String cpf) {
		Optional<Pessoa> pessoa = pessoaRepository.findById(cpf);
		
		if (pessoa.isPresent()) {
			return ResponseEntity.ok(new PessoaDto(pessoa.get()));
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<PessoaDto> atualizar(@PathVariable("id") String cpf,
			@Valid @RequestBody AtualizacaoPessoaForm pessoaForm) {
		
		Pessoa pessoa = pessoaForm.atualizar(cpf, pessoaRepository, enderecoRepository);
		
		if (pessoa != null) {
			return ResponseEntity.ok(new PessoaDto(pessoa));
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> remover(@PathVariable("id") String cpf) {
		Optional<Pessoa> optional = pessoaRepository.findById(cpf);
		
		if (optional.isPresent()) {
			pessoaRepository.deleteById(cpf);
			
			return ResponseEntity.ok().build();
		}
		
		return ResponseEntity.notFound().build();
	}
	
}
