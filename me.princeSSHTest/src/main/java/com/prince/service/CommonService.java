package com.prince.service;

import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.hibernate.criterion.Criterion;

import com.prince.dao.CommonDao;
import com.prince.model.Person;

public abstract class CommonService<T> {
	protected CommonDao<T> commonDao;
	
	public abstract void setCommonDao(CommonDao<T> commonDao);

	public List<T> findAll() {
    	return commonDao.findAll();
    }
    
	public Long findAllCount() {
    	return commonDao.findAllCount();
    }
	
	public T getByPK(Object id){
		return commonDao.getByPK(id);
	}
	
	public T getById(Object id){
		return getByPK(id);
	}
	
	public void save(Object obj){
		commonDao.save(obj);
	}
	
	public void saveAndFlush(Object obj){
		commonDao.saveAndFlush(obj);
	}
	
	public void saveOrUpdate(Object obj){
		commonDao.saveOrUpdate(obj);
	}
	
	public void delete(Object obj){
		commonDao.delete(obj);
	}
	
	public void deleteById(Object id){
		commonDao.deleteById(id);
	}
	
	public List<T> findByCreateria(Set<Criterion> criterions, int pageSize, int pageNum){
		return commonDao.findByCreateria(criterions, pageSize, pageNum);
	}
	
	public Long findCountByCreateria(Set<Criterion> criterions){
    	return commonDao.findCountByCreateria(criterions);
	}
}
