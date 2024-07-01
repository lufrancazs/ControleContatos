package br.com.lmf.ControleContatos.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_contatos")
public class Contatos {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String contato;

	@ManyToOne
	@JoinColumn(name = "tipo_contatos_id", nullable = false)
	private TipoContatos tipoContatos;

	@ManyToOne(optional = false)
	private Pessoa pessoa;

	public Contatos() {
	}

	public Contatos(Long id, String contato, TipoContatos tipoContatos, Pessoa pessoa) {
		this.id = id;
		this.contato = contato;
		this.tipoContatos = tipoContatos;
		this.pessoa = pessoa;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getContato() {
		return contato;
	}

	public void setContato(String contato) {
		this.contato = contato;
	}

	public TipoContatos getTipoContatos() {
		return tipoContatos;
	}

	public void setTipoContatos(TipoContatos tipoContatos) {
		this.tipoContatos = tipoContatos;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

}
