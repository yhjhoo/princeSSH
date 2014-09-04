package com.prince.dao;

import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.sql.JoinType;
import org.springframework.stereotype.Repository;

import com.prince.model.Person;

@Repository
public class PersonDao extends CommonDao<Person>{

	//findByCreateria
	
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
}