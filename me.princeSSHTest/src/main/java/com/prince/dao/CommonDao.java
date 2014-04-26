package com.prince.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.SimpleExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.GenericTypeResolver;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public class CommonDao<T>{
	@Autowired
	protected SessionFactory sessionFactory;
	
	private Class<T> persistantClass;

	private Logger log = Logger.getLogger(this.getClass());
	
	@SuppressWarnings("unchecked")
	public CommonDao() {
		this.persistantClass = (Class<T>) GenericTypeResolver.resolveTypeArgument(getClass(), CommonDao.class);
	}

	protected Session getCurrentSession(){
		return sessionFactory.getCurrentSession();
	}
	
    @SuppressWarnings("unchecked")
	public List<T> findAll() {
    	return getCurrentSession().createCriteria(persistantClass).list();
    }
    
	public Long findAllCount() {
    	Criteria criteria = getCurrentSession().createCriteria(persistantClass);
    	criteria.setProjection(Projections.rowCount());
    	return (Long) criteria.list().get(0);
    }
	
	@SuppressWarnings("unchecked")
	public T getByPK(Object id){
		return (T) getCurrentSession().get(persistantClass, (Serializable) id);
	}
	
	public T getById(Object id){
		return getByPK(id);
	}
	
	public void save(Object obj){
		getCurrentSession().save(obj);
	}
	
	public void saveOrUpdate(Object obj){
		getCurrentSession().saveOrUpdate(obj);
	}
	
	public void delete(Object obj){
		getCurrentSession().delete(obj);
	}
	
	public void deleteById(Object id){
		delete(getByPK(id));
	}
	
	@SuppressWarnings("unchecked")
	public List<T> findByCreateria(Set<Criterion> criterions, int pageSize, int pageNum){
		Criteria criteria = getCurrentSession().createCriteria(persistantClass);
		setParams(criteria, criterions);
		if(pageSize > 0 && pageNum > 0){
			criteria.setFirstResult((pageNum-1)*pageSize);
			criteria.setMaxResults(pageSize);
		}
		return criteria.list();
	}
	
	public Long findCountByCreateria(Set<Criterion> criterions){
		Criteria criteria = getCurrentSession().createCriteria(persistantClass);
		setParams(criteria, criterions);
		criteria.setProjection(Projections.rowCount());
    	return (Long) criteria.list().get(0);
	}

	private void setParams(Criteria criteria, Set<Criterion> criterions) {
		for(Criterion criterion : criterions){
			if(criterion instanceof SimpleExpression){
				criteria.add(criterion);
			}else if(criterion instanceof Order){
				criteria.addOrder((Order) criterion);
			}else{
				log.warn("Not implemented yet!");
			}
		}
	}
}
