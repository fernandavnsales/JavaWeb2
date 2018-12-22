package modelo.dao;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

public abstract class JpaDAO<P> implements GenericDAO<P> {
	protected Class<P> persistentClass;
	private EntityManager manager;

	/**
	 * Método construtor
	 */
	@SuppressWarnings("unchecked")
	public JpaDAO() {
		super();

		// pegar a classe persistente por reflexão
		Type tipo = ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
		this.persistentClass = (Class<P>) tipo;

		// pega o EntityManager padrão da ThreadAtual
		this.manager = JPAUtil.getEntityManager();
	}

	public JpaDAO(EntityManager manager) {
		this();
		this.manager = manager;
	}

	@Override
	public EntityManager getEntityManager() {
		// caso o EntityManager tenha sido fechado, deve criar um novo
		if ((this.manager != null) && (!this.manager.isOpen()))
			this.manager = JPAUtil.getEntityManager();

		return this.manager;
	}

	@Override
	public P lerPorId(Object id) {
		return (P) this.getEntityManager().find(this.persistentClass, id);
	}

	@Override
	public List<P> lerTodos() {
		CriteriaBuilder cb = this.getEntityManager().getCriteriaBuilder();
		CriteriaQuery<P> c = cb.createQuery(this.persistentClass);
		c.select(c.from(this.persistentClass));

		List<P> resultado = this.getEntityManager().createQuery(c).getResultList();
		return resultado;
	}

	@Override
	public P salvar(P objeto) {
		boolean transacaoAtiva = this.getEntityManager().getTransaction().isActive();

		if (!transacaoAtiva)
			this.abrirTransacao();

		objeto = this.getEntityManager().merge(objeto);

		if (!transacaoAtiva)
			this.gravarTransacao();

		return objeto;
	}

	@Override
	public void excluir(P objeto) {
		boolean transacaoAtiva = this.getEntityManager().getTransaction().isActive();

		if (!transacaoAtiva)
			this.abrirTransacao();

		this.getEntityManager().remove(objeto);

		if (!transacaoAtiva)
			this.gravarTransacao();

	}

	@Override
	public void abrirTransacao() {
		this.getEntityManager().getTransaction().begin();
	}

	@Override
	public void gravarTransacao() {
		this.getEntityManager().flush();
		this.getEntityManager().getTransaction().commit();
	}

	@Override
	public void desfazerTransacao() {
		this.getEntityManager().getTransaction().rollback();
	}

}