package modelo.dao;

import java.util.List;

import javax.persistence.EntityManager;

public interface GenericDAO<P>
{
	public EntityManager getEntityManager();

	public P lerPorId(Object id);

	public List<P> lerTodos();

	public P salvar(P objeto);

	public void excluir(P objeto);

	public void abrirTransacao();

	public void gravarTransacao();

	public void desfazerTransacao();

}