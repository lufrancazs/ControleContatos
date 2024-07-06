package br.com.lmf.ControleContatos.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.lmf.ControleContatos.entities.Contatos;
import br.com.lmf.ControleContatos.entities.Pessoa;

@Repository
public interface ContatoRepository extends JpaRepository<Contatos, Long>{
	
	List<Contatos> findAllByPessoa(Pessoa pessoa);

}
