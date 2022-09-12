package io.github.Speciallist.vendasapi.rest.clientes;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.github.Speciallist.vendasapi.model.Cliente;
import io.github.Speciallist.vendasapi.model.repository.ClienteRepository;

@RestController
@RequestMapping("/api/clientes")
@CrossOrigin("http://localhost:3000")
public class ClienteController {

	@Autowired
	private ClienteRepository repository;

	@SuppressWarnings("rawtypes")
	@PostMapping
	public ResponseEntity salvar(@RequestBody ClienteFormRequest request) {
		Cliente cliente = request.toModel();
		repository.save(cliente);
		return ResponseEntity.ok(ClienteFormRequest.fromModel(cliente));
	}

	@PutMapping("{id}")
	public ResponseEntity<Void> atualizar(@PathVariable Long id, @RequestBody ClienteFormRequest request) {
		Optional<Cliente> optional = repository.findById(id);
		if (optional.isEmpty()) {
			return ResponseEntity.notFound().build();
		} else {
			Cliente cliente = request.toModel();
			cliente.setId(id);
			repository.save(cliente);
			return ResponseEntity.noContent().build();
		}
	}

	@GetMapping("{id}")
	public ResponseEntity<ClienteFormRequest> getById(@PathVariable Long id) {
		return repository.findById(id).map(ClienteFormRequest::fromModel)
				.map(clienteFormRequest -> ResponseEntity.ok(clienteFormRequest))
				.orElseGet(() -> ResponseEntity.notFound().build());
	}

	@DeleteMapping("{id}")
	public ResponseEntity<Object> delete(@PathVariable Long id) {
		return repository.findById(id).map(cliente -> {
			repository.delete(cliente);
			return ResponseEntity.noContent().build();
		}).orElseGet(() -> ResponseEntity.notFound().build());
	}

	@GetMapping
	public Page<ClienteFormRequest> getLista(
			@RequestParam(value = "nome", required = false, defaultValue = "") String nome,
			@RequestParam(value = "cpf", required = false, defaultValue = "") String cpf, Pageable pageable) {
		return repository.buscarPorNomeCPF("%" + nome + "%", "%" + cpf + "%", pageable)
				.map(ClienteFormRequest::fromModel);
	}

}
