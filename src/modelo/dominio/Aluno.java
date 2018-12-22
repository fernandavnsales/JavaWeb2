package modelo.dominio;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

@Entity
@Table(name = "tab_alunos")
@SequenceGenerator(name = "ALUNO_PK", sequenceName = "SEQ_ALUNO_PK", allocationSize = 1)
public class Aluno implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ALUNO_PK")
	private Integer matricula;

	@Size(min = 10, max = 60, message = "Nome entre 10 e 60 letras")
	@Column(length = 120, nullable = false)
	private String nome;

	@Column
	private String cpf;

	@Past(message = "Data de Nascimento inválida")
	@Column(name = "data_nascimento")
	@Temporal(TemporalType.DATE)
	private Date dataNascimento;

	@Lob
	@Basic(fetch = FetchType.LAZY)
	private byte[] foto;

	@DecimalMax(value = "9", message = "Série no máximo até 9º ano")
	@Column
	private Integer serie;

	@ManyToOne
	@JoinColumn(name = "id_turno_fk")
	private Turno turno;

	@Embedded
	private Endereco endereco = new Endereco();

	public Aluno(Integer matricula, String nome, String cpf, Date dataNascimento, byte[] foto, Integer serie,
			Turno turno, Endereco endereco) {
		super();
		this.matricula = matricula;
		this.nome = nome;
		this.cpf = cpf;
		this.dataNascimento = dataNascimento;
		this.foto = foto;
		this.serie = serie;
		this.turno = turno;
		this.endereco = endereco;
	}

	public Aluno() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getMatricula() {
		return matricula;
	}

	public void setMatricula(Integer matricula) {
		this.matricula = matricula;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public byte[] getFoto() {
		return foto;
	}

	public void setFoto(byte[] foto) {
		this.foto = foto;
	}

	public Integer getSerie() {
		return serie;
	}

	public void setSerie(Integer serie) {
		this.serie = serie;
	}

	public Turno getTurno() {
		return turno;
	}

	public void setTurno(Turno turno) {
		this.turno = turno;
	}

	public Endereco getEndereco() {

		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((matricula == null) ? 0 : matricula.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Aluno other = (Aluno) obj;
		if (matricula == null) {
			if (other.matricula != null)
				return false;
		} else if (!matricula.equals(other.matricula))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return matricula + "";
	}

}
