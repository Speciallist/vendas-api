package io.github.Speciallist.vendasapi.rest.produtos;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
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

import io.github.Speciallist.vendasapi.model.Produto;
import io.github.Speciallist.vendasapi.model.repository.ProdutoRepository;

@RestController
@RequestMapping("/api/produtos")
@CrossOrigin("http://localhost:3000")
public class ProdutoController {

	@Autowired
	private ProdutoRepository repository;

	@GetMapping
	public List<ProdutoFormRequest> listar() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return repository.findAll().stream().map(ProdutoFormRequest::fromModel).collect(Collectors.toList());
	}

	@GetMapping("{id}")
	public ResponseEntity<ProdutoFormRequest> getById(@PathVariable Long id) {
		Optional<Produto> optional = repository.findById(id);
		if (optional.isEmpty()) {
			return ResponseEntity.notFound().build();
		} else {
			ProdutoFormRequest produto = optional.map(ProdutoFormRequest::fromModel).get();
			return ResponseEntity.ok(produto);
		}
	}

	@PostMapping
	public ProdutoFormRequest salvar(@RequestBody ProdutoFormRequest produto) {
		Produto produto2 = produto.toModel();
		repository.save(produto2);
		produto.setId(produto2.getId());
		return ProdutoFormRequest.fromModel(produto2);
	}

	@PutMapping("{id}")
	public ResponseEntity<Void> atualizar(@PathVariable Long id, @RequestBody ProdutoFormRequest produto) {
		Optional<Produto> optional = repository.findById(id);
		if (optional.isEmpty()) {
			return ResponseEntity.notFound().build();
		} else {
			Produto produto2 = produto.toModel();
			produto2.setId(id);
			repository.save(produto2);
			return ResponseEntity.ok().build();
		}
	}

	@DeleteMapping("{id}")
	public ResponseEntity<Void> deletar(@PathVariable Long id) {
		Optional<Produto> produto = repository.findById(id);
		if (produto.isEmpty()) {
			return ResponseEntity.notFound().build();
		} else {
			repository.delete(produto.get());
			return ResponseEntity.noContent().build();
		}
	}

}
