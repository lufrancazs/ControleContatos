package br.com.lmf.ControleContatos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.lmf.ControleContatos.entities.TipoContatos;

public interface TipoContatoRepository extends JpaRepository<TipoContatos, Long>{

}
