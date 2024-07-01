package br.com.lmf.ControleContatos.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.lmf.ControleContatos.entities.Pessoa;
import br.com.lmf.ControleContatos.services.PessoaService;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("api/pessoas")
public class PessoaResource {
	
	@Autowired
	private PessoaService pessoaService;
	
	@Operation(summary = "Busca de Todas as Pessoas")
	@GetMapping
	public ResponseEntity<List<Pessoa>> findAll(){
		List<Pessoa> list = pessoaService.findAll();
		
		if(list == null || list.size() == 0) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(list);
	}
	
	@Operation(summary = "Cadastro de Pessoa")
	@PostMapping
	public ResponseEntity<Pessoa> insert(@RequestBody Pessoa pessoa){
		
		Pessoa newPessoa = pessoaService.insert(pessoa);
		
		if(newPessoa == null) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(newPessoa);
	}
	
	
}
