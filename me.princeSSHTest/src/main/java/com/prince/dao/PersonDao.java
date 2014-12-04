package com.prince.dao;

import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.sql.JoinType;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.prince.model.Person;

@Repository
public class PersonDao extends CommonDao<Person>{

	//findByCreateria
	
	@Resource
	private HibernateTemplate hibernateTemplate;
	
	@SuppressWarnings("unchecked")
	public List<Person> findByCreateria(Set<Criterion> criterions, int pageSize, int pageNum){
		Criteria criteria = getCurrentSession().createCriteria(Person.class, "p");
		criteria.createAlias("p.departement", "d", JoinType.LEFT_OUTER_JOIN);
		criteria.createAlias("p.worldCity", "wc", JoinType.LEFT_OUTER_JOIN);
		
		setParams(criteria, criterions);
		
		if(pageSize > 0 && pageNum > 0){
			criteria.setFirstResult((pageNum-1)*pageSize);
			criteria.setMaxResults(pageSize);
		}
		return criteria.list();
	}

	public void save1(Person person) {
		//getCurrentSession().save(person);
		hibernateTemplate.save(person);
		
	}
}