package br.com.lmf.ControleContatos.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.lmf.ControleContatos.entities.Pessoa;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
	
	Optional<Pessoa> findByNomeAndCep(String nome, String cep);

}
