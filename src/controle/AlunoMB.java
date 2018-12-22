package controle;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.primefaces.model.UploadedFile;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;

import modelo.dominio.Aluno;
import modelo.dominio.Turno;
import webservice.ServicoEndereco;
import modelo.dao.AlunoDAO;
import modelo.dao.TurnoDAO;

/**
 * Classe Managed Bean Aluno
 * 
 * @author Fernanda Viana
 * @version 4.0.0 - 13/11/2018
 *
 */

@ManagedBean(name = "alunoMB")
@RequestScoped
public class AlunoMB implements Serializable{

	private static final long serialVersionUID = 1L;

	@ManagedProperty(value = "#{loginMB}")
	private LoginMB loginMB;

	private List<Aluno> alunos = null;
	private List<Turno> turnos = null;
	private Aluno aluno = new Aluno();
	private AlunoDAO dao = new AlunoDAO();

	private Turno filtroTurno;
	private String filtroNome;

	private UploadedFile uploadedFile;
	
	private ServicoEndereco servico = new ServicoEndereco();
	
	public void carregarEndereco() {
		Client cli = Client.create();
		WebResource wr = cli.resource("https://viacep.com.br/ws/" + this.aluno.getEndereco().getCep() + "/json/");
		System.out.println("Chamando WebService...");
		this.aluno.setEndereco( servico.buscarEnderecoPor(wr.get(String.class)) );
		String JSON = wr.get(String.class);
		System.out.println(JSON);
	}

	public Turno getFiltroTurno() {
		return filtroTurno;
	}

	public void setFiltroTurno(Turno filtroTurno) {
		this.filtroTurno = filtroTurno;
	}

	public String getFiltroNome() {
		return filtroNome;
	}

	public void setFiltroNome(String filtroNome) {
		this.filtroNome = filtroNome;
	}

	public LoginMB getLoginMB() {
		return loginMB;
	}

	public void setLoginMB(LoginMB loginMB) {
		this.loginMB = loginMB;
	}
	

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public List<Turno> getTurnos() {

		if (this.turnos == null) {
			TurnoDAO turnoDao = new TurnoDAO();
			this.turnos = turnoDao.lerTodos();
		}

		return turnos;
	}

	public void setTurnos(List<Turno> turnos) {
		this.turnos = turnos;
	}

	public UploadedFile getUploadedFile() {
		return uploadedFile;
	}

	public void setUploadedFile(UploadedFile uploadedFile) {
		this.uploadedFile = uploadedFile;
	}

	public List<Aluno> getAlunos() {

		if (this.alunos == null)
			this.alunos = this.dao.filtrar(filtroTurno, filtroNome);

		return alunos;
	}

	public void setAlunos(List<Aluno> alunos) {
		this.alunos = alunos;
	}

	// Métodos

	public void acaoPesquisar() {
		this.alunos = null;
	}

	// Listar todos
	public String acaoListar() {
		return "alunoListar.jsf?faces-redirect=true";
	}

	// Fazer cadastro
	public String acaoAbrirInclusao() {
		return "alunoEditar.jsf";
	}

	// Alterar pela matricula
	public String acaoAbrirAlteracao(Integer matricula) {

		this.aluno = this.dao.lerPorId(matricula);

		return "alunoEditar.jsf";
	}

	// Salvar imagem no banco
	public String acaoSalvar() {
		byte[] foto = null;
		
		if (this.uploadedFile != null)
			foto = this.uploadedFile.getContents();

		if (foto != null)
			this.aluno.setFoto(foto);

		this.dao.salvar(this.aluno);

		return acaoListar();
	}

	// Excluir do banco
	public String acaoExcluir(Integer matricula) {

		this.aluno = this.dao.lerPorId(matricula);
		
		this.dao.excluir(this.aluno);
		
		this.alunos = null;
		
		return acaoListar();
	}

	public void download() throws IOException {
		FacesContext contexto = FacesContext.getCurrentInstance();
		ExternalContext external = contexto.getExternalContext();
		OutputStream saida = external.getResponseOutputStream();

		// ler o código passado como parâmetro
		String matricula = external.getRequestParameterMap().get("matricula");
		Integer id = Integer.parseInt(matricula);
		
		external.responseReset();
		external.setResponseContentType("image/jpeg");
		external.setResponseHeader("Content-Disposition", "attachment; filename=foto-" + matricula + ".jpg");

		external.setResponseHeader("Cache-Control", "no-cache, no-store, must-revalidate");
		external.setResponseHeader("Pragma", "no-cache"); // HTTP 1.0
		external.setResponseHeader("Expires", "0"); // Proxies.

	
		Aluno aluno = dao.lerPorId(id);
		byte[] foto = aluno.getFoto();
		if (foto != null) {
			
			external.setResponseContentLength(foto.length);
			saida.write(foto);
		}

		contexto.responseComplete();
	}
}
