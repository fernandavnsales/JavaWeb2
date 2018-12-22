package modelo.dominio;

public class Turma {
	private Integer capacidade;
	private String anoLetivo;
	
	
	public Turma() {
		super();
	}


	public Turma(Integer capacidade, String anoLetivo) {
		super();
		this.capacidade = capacidade;
		this.anoLetivo = anoLetivo;
	}


	public Integer getCapacidade() {
		return capacidade;
	}


	public void setCapacidade(Integer capacidade) {
		this.capacidade = capacidade;
	}


	public String getAnoLetivo() {
		return anoLetivo;
	}


	public void setAnoLetivo(String anoLetivo) {
		this.anoLetivo = anoLetivo;
	}
	
}
