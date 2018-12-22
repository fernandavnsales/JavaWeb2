package webservice;

import java.io.Serializable;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import modelo.dominio.Endereco;

public class ServicoEndereco implements Serializable{

	private static final long serialVersionUID = 5035214272608851224L;
	
	public Endereco buscarEnderecoPor(String urlJson) {
		System.out.println("Chamou o serviço...");
		final GsonBuilder gsonBuilder = new GsonBuilder();
		final Gson gson = gsonBuilder.create();
		Gson g = new Gson();
		Endereco retornoEndereco = gson.fromJson(urlJson, Endereco.class);
		return retornoEndereco;
	}
}
