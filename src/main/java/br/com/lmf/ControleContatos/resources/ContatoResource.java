package br.com.lmf.ControleContatos.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.lmf.ControleContatos.dto.ContatoSimplesDto;
import br.com.lmf.ControleContatos.entities.Contatos;
import br.com.lmf.ControleContatos.services.ContatoService;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/contatos")
public class ContatoResource {
	
	@Autowired
	private ContatoService contatoService;
	
	@Operation(summary = "Busca por Todos os Contatos")
	@GetMapping
	public ResponseEntity<List<Contatos>> findAll(){
		List<Contatos> list = contatoService.findAll();
		
		if(list == null || list.size() == 0) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(list);
	}
	
	@Operation(summary = "Busca de Contato por ID")
	@GetMapping("/{id}")
	public ResponseEntity<Contatos> findById(@PathVariable Long id){
		Contatos contato = contatoService.findById(id);
		
		return ResponseEntity.ok(contato);
	}
	
	
	
	
	@Operation(summary = "Cadastro de Contato por Pessoa")
	@PostMapping
	public ResponseEntity<Contatos> insert(@RequestBody ContatoSimplesDto dto){
		Contatos newContato = contatoService.insert(dto);
		return ResponseEntity.status(HttpStatus.CREATED).body(newContato);
	}
	
	

}