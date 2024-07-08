package br.com.lmf.ControleContatos.services;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.lmf.ControleContatos.client.ViaCepClient;
import br.com.lmf.ControleContatos.dto.PessoaMalaDiretaDto;
import br.com.lmf.ControleContatos.dto.PessoaSimplesDto;
import br.com.lmf.ControleContatos.entities.Pessoa;
import br.com.lmf.ControleContatos.exceptions.CepNotFoundException;
import br.com.lmf.ControleContatos.exceptions.NomeAndCepDataAlreadyExistsException;
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
		return obj.orElseThrow(() -> new ResourceNotFoundException("Pessoa não encontrada pelo ID: " + id));
	}

	public Pessoa insert(PessoaSimplesDto dto) {

		Optional<Pessoa> findByNomeAndCep = pessoaRepository.findByNomeAndCep(dto.getNome(), dto.getCep());

		if (findByNomeAndCep.isPresent()) {
			throw new NomeAndCepDataAlreadyExistsException(toString());
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
				throw new NomeAndCepDataAlreadyExistsException(toString());
			}

			uptPessoa.setNome(dto.getNome());
			uptPessoa.setCep(dto.getCep());

			atualizarEndereco(dto, uptPessoa);

			return pessoaRepository.save(uptPessoa);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Pessoa não encontrada pelo ID: " + id);
		}

	}

	public void delete(Long id) {
		pessoaRepository.deleteById(id);
	}	
	
	
	public PessoaMalaDiretaDto getPessoaMalaDireta(Long id) {
		try {
		Pessoa pessoa = pessoaRepository.getReferenceById(id);
		
		String malaDireta = formatEndereco(pessoa);
		
		return new PessoaMalaDiretaDto(pessoa.getId(), pessoa.getNome(), malaDireta);
		
		}catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Pessoa não encontrada pelo ID: " + id);
		}
	}
	

	private void atualizarEndereco(PessoaSimplesDto dto, Pessoa newPessoa) {

		Map<String, Object> endereco = viaCepClient.buscarEndereco(dto.getCep());

		if (endereco == null || endereco.get("logradouro") == null || endereco.get("localidade") == null
				|| endereco.get("uf") == null) {
			throw new CepNotFoundException(toString());
		}

		newPessoa.setEndereco((String) endereco.get("logradouro"));
		newPessoa.setCidade((String) endereco.get("localidade"));
		newPessoa.setUf((String) endereco.get("uf"));
	}
	
	public String formatEndereco(Pessoa pessoa) {
		String malaDireta = pessoa.getEndereco() 
				+ ", CEP: " + pessoa.getCep() + " - " 
				+ pessoa.getCidade() + "/" + pessoa.getUf();
		return malaDireta;
	}

}
