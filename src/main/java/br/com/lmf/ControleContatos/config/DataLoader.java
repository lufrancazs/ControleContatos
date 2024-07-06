package br.com.lmf.ControleContatos.config;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import br.com.lmf.ControleContatos.entities.TipoContatos;
import br.com.lmf.ControleContatos.repositories.TipoContatoRepository;

@Configuration
public class DataLoader implements CommandLineRunner{
	
	private TipoContatoRepository tipoContatoRepository;
	

	public DataLoader(TipoContatoRepository tipoContatoRepository) {
		this.tipoContatoRepository = tipoContatoRepository;
	}


	@Override
	public void run(String... args) throws Exception {
		
		Arrays.stream(TipoContatos.Enum.values())
		.forEach( tipoConta -> tipoContatoRepository.save(tipoConta.get()));
		
	}

}
