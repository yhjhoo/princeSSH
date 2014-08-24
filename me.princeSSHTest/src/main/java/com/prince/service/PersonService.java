package com.prince.service;


import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.prince.dao.CommonDao;
import com.prince.dao.DepartmentDao;
import com.prince.dao.PersonDao;
import com.prince.model.Department;
import com.prince.model.Person;


@Transactional
@Service
public class PersonService extends CommonService<Person>{
	@Resource
	private PersonDao personDao;
	
	@Resource
	private DepartmentDao departmentDao;
	
	
//	@Resource
//	public void setCommonDao(CommonDao<Person> commonDao) {
//		this.commonDao = personDao;
//	}
	
	@Resource
	@Override
	public void setCommonDao(CommonDao<Person> commonDao) {
		this.commonDao = personDao;
	}
	
//
//	public List<Person> findAll() {
//    	return personDao.findAll();
//    }


	/**
	 * transaction roll-back, department is mandatory
	 * @param person
	 */
	public void saveRollback(Person person){
		personDao.save(person);
//		if(true){
//			throw new SQLException();
//		}
		
		Department department = new Department();
		//department.setName("test name 1");
		department.setDescription("test name 1");
		departmentDao.save(department);
		
	}


	
}