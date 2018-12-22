package modelo.dominio;

public class Disciplina {
	private String nome;
	private Integer cargaHoraria;
	
	public Disciplina() {
		super();
	}

	public Disciplina(String nome, Integer cargaHoraria) {
		super();
		this.nome = nome;
		this.cargaHoraria = cargaHoraria;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getCargaHoraria() {
		return cargaHoraria;
	}

	public void setCargaHoraria(Integer cargaHoraria) {
		this.cargaHoraria = cargaHoraria;
	}
	
}
