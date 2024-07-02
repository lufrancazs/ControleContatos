package br.com.lmf.ControleContatos.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.lmf.ControleContatos.entities.Pessoa;
import br.com.lmf.ControleContatos.repositories.PessoaRepository;

@Service
public class PessoaService {
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	public List<Pessoa> findAll() {
		return pessoaRepository.findAll();
	}
	
	public Optional<Pessoa> findById(Long id) {
		return pessoaRepository.findById(id);
	}
	
	public Pessoa insert(Pessoa pessoa) {
		return pessoaRepository.save(pessoa);
	}
	
	public Pessoa update(Pessoa pessoa, Long id) throws Exception{
			Optional<Pessoa> findPessoa = pessoaRepository.findById(id);
			
			if(findPessoa.isPresent()) {
				Pessoa uptPessoa = findPessoa.get();
				uptPessoa.setNome(pessoa.getNome());
				uptPessoa.setEndereco(pessoa.getEndereco());
				uptPessoa.setCep(pessoa.getCep());
				uptPessoa.setCidade(pessoa.getCidade());
				uptPessoa.setUf(pessoa.getUf());
				return pessoaRepository.save(uptPessoa);
			} else {
				throw new Exception("Pessoa não existe para este id: " + id);
			}

	}
	
	public void delete(Long id) {
		pessoaRepository.deleteById(id);
	}

}
