package br.com.lmf.ControleContatos.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.lmf.ControleContatos.dto.ContatoSimplesDto;
import br.com.lmf.ControleContatos.entities.Contatos;
import br.com.lmf.ControleContatos.entities.Pessoa;
import br.com.lmf.ControleContatos.entities.TipoContatos;
import br.com.lmf.ControleContatos.exceptions.ResourceNotFoundException;
import br.com.lmf.ControleContatos.repositories.ContatoRepository;
import br.com.lmf.ControleContatos.repositories.PessoaRepository;
import br.com.lmf.ControleContatos.repositories.TipoContatoRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class ContatoService {

	@Autowired
	private ContatoRepository contatoRepository;

	@Autowired
	private PessoaRepository pessoaRepository;

	@Autowired
	private TipoContatoRepository tipoContatoRepository;

	public ContatoService(ContatoRepository contatoRepository, PessoaRepository pessoaRepository,
			TipoContatoRepository tipoContatoRepository) {
		this.contatoRepository = contatoRepository;
		this.pessoaRepository = pessoaRepository;
		this.tipoContatoRepository = tipoContatoRepository;
	}

	public List<Contatos> findAll() {
		return contatoRepository.findAll();
	}

	public Contatos findById(Long id) {

		Optional<Contatos> obj = contatoRepository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}

	public List<Contatos> findAllByPessoa(Long id) {
		Pessoa pessoa = pessoaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));

		return contatoRepository.findAllByPessoa(pessoa);

	}

	public Contatos insert(ContatoSimplesDto dto) {

		Pessoa findPessoa = pessoaRepository.findById(dto.getPessoaId())
				.orElseThrow(() -> new ResourceNotFoundException(dto.getPessoaId()));

		TipoContatos findTipoContato = tipoContatoRepository.findById(dto.getTipoContatosId())
				.orElseThrow(() -> new ResourceNotFoundException(dto.getTipoContatosId()));

		Contatos newContato = new Contatos();
		newContato.setContato(dto.getContato());
		newContato.setTipoContatos(findTipoContato);
		newContato.setPessoa(findPessoa);

		return contatoRepository.save(newContato);
	}

	public Contatos update(ContatoSimplesDto dto, Long id) {

		try {
			
			Contatos uptContato = contatoRepository.getReferenceById(id);
			
			TipoContatos findTipoContato = tipoContatoRepository.findById(dto.getTipoContatosId())
					.orElseThrow(() -> new ResourceNotFoundException(dto.getTipoContatosId()));

			Pessoa findPessoa = pessoaRepository.findById(dto.getPessoaId())
					.orElseThrow(() -> new ResourceNotFoundException(dto.getPessoaId()));

			uptContato.setContato(dto.getContato());
			uptContato.setTipoContatos(findTipoContato);
			uptContato.setPessoa(findPessoa);

			return contatoRepository.save(uptContato);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}

	}

}
