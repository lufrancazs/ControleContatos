package br.com.lmf.ControleContatos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.lmf.ControleContatos.entities.Contatos;

@Repository
public interface ContatoRepository extends JpaRepository<Contatos, Long>{

}
