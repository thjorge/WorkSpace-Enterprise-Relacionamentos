package br.com.fiap.dao.impl;


import java.lang.reflect.ParameterizedType;

import javax.persistence.EntityManager;
import br.com.fiap.dao.GenericDAO;
import br.com.fiap.exception.DBException;
import br.com.fiap.exception.IdNotFoundException;

public class GenericDAOImpl<T,K> implements GenericDAO<T,K>{

	protected EntityManager em;
	protected Class<T> classe;
	
	@SuppressWarnings("all")
	public GenericDAOImpl(EntityManager em){
		this.em = em;
		classe = (Class<T>) ((ParameterizedType)getClass().getGenericSuperclass())
				.getActualTypeArguments()[0];
	}
	
	@Override
	public void cadastrar(T entity) {
		em.persist(entity);
	}

	@Override
	public void salvar() throws DBException {
		try {			
			em.getTransaction().begin(); //Inicia a Transação			
			em.getTransaction().commit(); //Finaliza a Transação
		}catch (Exception e){
			if(em.getTransaction().isActive())
				em.getTransaction().rollback();
			e.printStackTrace();
			throw new DBException("Erro no commit!");
		}
	}

	@Override
	public T pesquisar(K codigo) {
		return em.find(classe, codigo);
	}

	@Override
	public void alterar(T entity) {
		em.merge(entity);
	}

	@Override
	public void remover(K codigo) throws IdNotFoundException {
		T entity = pesquisar(codigo);
		if(entity == null)
			throw new IdNotFoundException("Entidade não encontrada");
		em.remove(entity);
	}


}
