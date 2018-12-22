package modelo.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import modelo.dominio.Aluno;
import modelo.dominio.Turno;

/**
 * Classe de persistência Aluno
 * 
 * @author Fernanda Viana
 * @version 4.0.0 - 13/11/2018
 */
public class AlunoDAO extends JpaDAO<Aluno>{
	public List<Aluno> filtrar(Turno turno, String nome)
	{
		String jpql = "from Aluno a ";
		String conector = " where ";
		
		if (turno != null){
			jpql = jpql + conector + " a.turno = :paramTurno";
			conector = " and ";
		}
		
		if (nome != null){
			jpql = jpql + conector + " a.nome like :paramNome";
			conector = " and ";
		}
		
		jpql = jpql + " order by a.nome";
		
		TypedQuery<Aluno> consulta = this.getEntityManager().createQuery(jpql, Aluno.class);
		
		if (turno != null)
			consulta.setParameter("paramTurno", turno);
		
		if (nome != null)
			consulta.setParameter("paramNome", "%" + nome + "%");
		
		List<Aluno> retorno = consulta.getResultList();		
		return retorno;
	}
}

