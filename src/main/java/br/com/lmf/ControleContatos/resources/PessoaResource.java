package br.com.lmf.ControleContatos.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.lmf.ControleContatos.entities.Pessoa;
import br.com.lmf.ControleContatos.services.PessoaService;

@RestController
@RequestMapping("api/pessoas")
public class PessoaResource {
	
	@Autowired
	private PessoaService pessoaService;
	
	@GetMapping
	public ResponseEntity<List<Pessoa>> findAll(){
		List<Pessoa> list = pessoaService.findAll();
		
		if(list == null || list.size() == 0) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(list);
	}
	
	
}
