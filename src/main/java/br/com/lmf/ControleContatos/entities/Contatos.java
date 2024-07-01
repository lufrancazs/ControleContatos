package br.com.lmf.ControleContatos.entities;

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
	private String contato;
	
	@ManyToOne
	@JoinColumn(name = "tipo_contatos_id")
	private TipoContatos tipoContatos;
	
	
	public Contatos() {
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
	
	

}
