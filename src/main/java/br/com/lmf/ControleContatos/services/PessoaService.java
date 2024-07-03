package br.com.lmf.ControleContatos.services;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.lmf.ControleContatos.client.ViaCepClient;
import br.com.lmf.ControleContatos.dto.PessoaSimplesDto;
import br.com.lmf.ControleContatos.entities.Pessoa;
import br.com.lmf.ControleContatos.exceptions.ResourceNotFoundException;
import br.com.lmf.ControleContatos.repositories.PessoaRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class PessoaService {

	@Autowired
	private PessoaRepository pessoaRepository;

	@Autowired
	private ViaCepClient viaCepClient;

	public PessoaService(PessoaRepository pessoaRepository, ViaCepClient viaCepClient) {
		this.pessoaRepository = pessoaRepository;
		this.viaCepClient = viaCepClient;
	}

	public List<Pessoa> findAll() {
		return pessoaRepository.findAll();
	}

	public Pessoa findById(Long id) {

		Optional<Pessoa> obj = pessoaRepository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}

	public Pessoa insert(PessoaSimplesDto dto) throws Exception {

		Optional<Pessoa> findByNomeAndCep = pessoaRepository.findByNomeAndCep(dto.getNome(), dto.getCep());

		if (findByNomeAndCep.isPresent()) {
			throw new Exception("Nome e CEP já existe");
		}

		Pessoa pessoa = new Pessoa();
		pessoa.setNome(dto.getNome());
		pessoa.setCep(dto.getCep());

		atualizarEndereco(dto, pessoa);

		return pessoaRepository.save(pessoa);
	}

	public Pessoa update(PessoaSimplesDto dto, Long id) {

		try {
			Pessoa uptPessoa = pessoaRepository.getReferenceById(id);

				Optional<Pessoa> findByNomeAndCep = pessoaRepository.findByNomeAndCep(dto.getNome(), dto.getCep());

				if (findByNomeAndCep.isPresent()) {
					throw new ResourceNotFoundException("Nome e CEP já existe");
				}				

				uptPessoa.setNome(dto.getNome());
				uptPessoa.setCep(dto.getCep());

				atualizarEndereco(dto, uptPessoa);

				return pessoaRepository.save(uptPessoa);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(e.getMessage());
		}

	}

	public void delete(Long id) {
		pessoaRepository.deleteById(id);
	}

	private void atualizarEndereco(PessoaSimplesDto dto, Pessoa newPessoa) {

		Map<String, Object> endereco = viaCepClient.buscarEndereco(dto.getCep());
		newPessoa.setEndereco((String) endereco.get("logradouro"));
		newPessoa.setCidade((String) endereco.get("localidade"));
		newPessoa.setUf((String) endereco.get("uf"));
	}

}
