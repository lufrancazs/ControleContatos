package br.com.lmf.ControleContatos.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.lmf.ControleContatos.dto.ContatoSimplesDto;
import br.com.lmf.ControleContatos.entities.Contatos;
import br.com.lmf.ControleContatos.services.ContatoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Contatos", description = "Ações Relacionadas a Contatos")
@RestController
@RequestMapping("/api/contatos")
public class ContatoResource {

	@Autowired
	private ContatoService contatoService;

	public ContatoResource(ContatoService contatoService) {
		this.contatoService = contatoService;
	}

	@Operation(summary = "Busca por Todos os Contatos")
	@GetMapping
	public ResponseEntity<List<Contatos>> findAll() {

		List<Contatos> list = contatoService.findAll();

		if (list == null || list.size() == 0) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(list);
	}

	@Operation(summary = "Busca de Contato por ID")
	@GetMapping("/{id}")
	public ResponseEntity<Contatos> findById(@PathVariable Long id) {

		Contatos contato = contatoService.findById(id);

		return ResponseEntity.ok(contato);
	}

	@Operation(summary = "Busca de Contato por Pessoa ID")
	@GetMapping("/pessoa/{id}")
	public ResponseEntity<List<Contatos>> findContatoPorPessoa(@PathVariable Long id) {

		List<Contatos> list = contatoService.findAllByPessoa(id);

		if (list == null || list.size() == 0) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(list);
	}

	@Operation(summary = "Cadastro de Contato por Pessoa")
	@PostMapping
	public ResponseEntity<Contatos> insert(@RequestBody ContatoSimplesDto dto) {

		Contatos newContato = contatoService.insert(dto);
		return ResponseEntity.status(HttpStatus.CREATED).body(newContato);
	}

	@Operation(summary = "Atualizar Contato por ID")
	@PutMapping("/{id}")
	public ResponseEntity<Contatos> update(@PathVariable Long id, @RequestBody ContatoSimplesDto dto) {

		Contatos uptContato = contatoService.update(dto, id);
		return ResponseEntity.ok(uptContato);

	}

	@Operation(summary = "Excluir Contato por ID")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {

		contatoService.delete(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
