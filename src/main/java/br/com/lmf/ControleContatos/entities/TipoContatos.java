package br.com.lmf.ControleContatos.entities;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_tipo_contatos")
public class TipoContatos {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String descricao;
	
	public TipoContatos() {
	}

	public TipoContatos(Long id, String descricao) {
		this.id = id;
		this.descricao = descricao;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TipoContatos other = (TipoContatos) obj;
		return Objects.equals(id, other.id);
	}
	
	public enum Enum {
		
		TELEFONE(1L, "telefone"),
		CELULAR(2L, "celular"),
		EMAIL(3L, "email");
		
		private Enum(Long id, String descricao) {
			this.id = id;
			this.descricao = descricao;
		}
		
		private Long id;
		private String descricao;
		
		public TipoContatos get() {
			return new TipoContatos(id, descricao);
		}
	}

}
