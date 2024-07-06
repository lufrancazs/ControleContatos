package br.com.lmf.ControleContatos.dto;

public class ContatoSimplesDto {
	
	private String contato;
	private Long tipoContatosId;
	private Long pessoaId;
	
	public ContatoSimplesDto() {
		
	}

	public ContatoSimplesDto(String contato, Long tipoContatosId, Long pessoaId) {
		this.contato = contato;
		this.tipoContatosId = tipoContatosId;
		this.pessoaId = pessoaId;
	}

	public String getContato() {
		return contato;
	}

	public void setContato(String contato) {
		this.contato = contato;
	}

	public Long getTipoContatosId() {
		return tipoContatosId;
	}

	public void setTipoContatosId(Long tipoContatosId) {
		this.tipoContatosId = tipoContatosId;
	}

	public Long getPessoaId() {
		return pessoaId;
	}

	public void setPessoaId(Long pessoaId) {
		this.pessoaId = pessoaId;
	}

}
