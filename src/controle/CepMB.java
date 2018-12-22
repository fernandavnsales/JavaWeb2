
package controle;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import modelo.dominio.Endereco;


@ManagedBean(name = "cepMB")
@RequestScoped
public class CepMB implements Serializable{
	private static final long serialVersionUID = -7312656320297107777L;
	
	private List<Endereco> lista = new ArrayList<Endereco>();
	private Endereco endereco;
	private String cep;
	
	
	public List<Endereco> getLista(){
		return lista;
	}
	
	public void setLista(List<Endereco> lista) {
		this.lista = lista;
	}
	
	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	
	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}
	
	public void limpar() {
		this.endereco = new Endereco();
	}
	
	
}
