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

import br.com.lmf.ControleContatos.dto.PessoaMalaDiretaDto;
import br.com.lmf.ControleContatos.dto.PessoaSimplesDto;
import br.com.lmf.ControleContatos.entities.Pessoa;
import br.com.lmf.ControleContatos.services.PessoaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Pessoa", description = "Ações Relacionadas a Pessoa")
@RestController
@RequestMapping("api/pessoas")
public class PessoaResource {

	@Autowired
	private PessoaService pessoaService;

	public PessoaResource(PessoaService pessoaService) {
		this.pessoaService = pessoaService;
	}

	@Operation(summary = "Busca de Todas as Pessoas")
	@GetMapping
	public ResponseEntity<List<Pessoa>> findAll() {
		List<Pessoa> list = pessoaService.findAll();

		if (list == null || list.size() == 0) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(list);
	}

	@Operation(summary = "Busca de Pessoa por ID")
	@GetMapping("/{id}")
	public ResponseEntity<Pessoa> findById(@PathVariable Long id) {
		Pessoa pessoa = pessoaService.findById(id);

		return ResponseEntity.ok(pessoa);
	}

	@Operation(summary = "Busca de Pessoa Mala Direta por ID")
	@GetMapping("/maladireta/{id}")
	public ResponseEntity<PessoaMalaDiretaDto> malaDireta(@PathVariable Long id) {

		PessoaMalaDiretaDto dto = pessoaService.getPessoaMalaDireta(id);
		return ResponseEntity.ok(dto);
	}

	@Operation(summary = "Cadastro de Pessoa")
	@PostMapping
	public ResponseEntity<Pessoa> insert(@RequestBody PessoaSimplesDto dto) {

		Pessoa newPessoa = pessoaService.insert(dto);
		return ResponseEntity.status(HttpStatus.CREATED).body(newPessoa);

	}

	@Operation(summary = "Atualizar Pessoa por ID")
	@PutMapping("/{id}")
	public ResponseEntity<Pessoa> update(@RequestBody PessoaSimplesDto dto, @PathVariable Long id) {

		Pessoa uptPessoa = pessoaService.update(dto, id);
		return ResponseEntity.ok(uptPessoa);

	}

	@Operation(summary = "Excluir Pessoa por ID")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		pessoaService.delete(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
