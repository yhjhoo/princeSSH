package com.prince.service;


import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.prince.dao.CommonDao;
import com.prince.dao.DepartmentDao;
import com.prince.dao.PersonDao;
import com.prince.model.Department;
import com.prince.model.Person;


//@Transactional
@Service
public class PersonService extends CommonService<Person>{
	@Resource
	private PersonDao personDao;
	
	@Autowired
	protected SessionFactory sessionFactory;
	
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
	

	//@Transactional(isolation=Isolation.READ_UNCOMMITTED)
	public List<Person> findAll1() {
    	//return personDao.findAll();
		return commonDao.findAll();
    }


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


	public void saveBatch(List<Person> persons) throws SQLException {
		int i = 0;
		for(Person p : persons){
			personDao.save(p);
			i++;
			if(i == 100){
				throw new SQLException();
			}
		}
	}

}