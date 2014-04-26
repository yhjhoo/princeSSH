package com.prince.service;


import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.prince.dao.CommonDao;
import com.prince.dao.PersonDao;
import com.prince.model.Person;


@Transactional
@Service
public class PersonService extends CommonService<Person>{
//	@Resource
	private PersonDao personDao;
	
	
	@Resource
	public void setPersonDao(PersonDao personDao) {
		this.personDao = personDao;
		this.commonDao = personDao;
	}
//
//	public List<Person> findAll() {
//    	return personDao.findAll();
//    }
}