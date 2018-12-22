package modelo.dominio;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Embeddable
public class Endereco {
	
	@Column(name= "cep")
	@SerializedName("cep")
	@Expose
	private String cep;
	
	@Column(name= "logradouro")
	@SerializedName("logradouro")
	@Expose
	private String logradouro;
	
	@Column(name= "complemento")
	@SerializedName("complemento")
	@Expose
	private String complemento;
	
	@Column(name= "bairro")
	@SerializedName("bairro")
	@Expose
	private String bairro;
	
	@Column(name="localidade")
	@SerializedName("localidade")
	@Expose
	private String localidade;
	
	@Column(name= "uf")
	@SerializedName("uf")
	@Expose
	private String uf;
	
	@Column(name= "numero")
	private Integer numero;

	public Endereco() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Endereco(String cep, String logradouro, String complemento, String bairro, String localidade, String uf,
			Integer numero) {
		super();
		this.cep = cep;
		this.logradouro = logradouro;
		this.complemento = complemento;
		this.bairro = bairro;
		this.localidade = localidade;
		this.uf = uf;
		this.numero = numero;
	}



	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getLocalidade() {
		return localidade;
	}

	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}
	

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("cep", cep).append("logradouro", logradouro)
				.append("complemento", complemento).append("bairro", bairro).append("localidade", localidade)
				.append("uf", uf).toString();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(bairro).append(complemento).append(cep).append(uf).append(localidade)
				.append(logradouro).toHashCode();
	}

	@Override
	public boolean equals(Object other) {
		if (other == this) {
			return true;
		}
		if ((other instanceof Endereco) == false) {
			return false;
		}
		Endereco rhs = ((Endereco) other);
		return new EqualsBuilder().append(bairro, rhs.bairro).append(complemento, rhs.complemento).append(cep, rhs.cep)
				.append(uf, rhs.uf).append(localidade, rhs.localidade).append(logradouro, rhs.logradouro).isEquals();
	}

}