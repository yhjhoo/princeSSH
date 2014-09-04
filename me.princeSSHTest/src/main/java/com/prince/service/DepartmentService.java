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
import com.prince.dao.WorldCityDao;
import com.prince.model.Department;
import com.prince.model.Person;
import com.prince.model.WorldCity;


//@Transactional
@Service
public class DepartmentService extends CommonService<Department>{
	@Resource
	private DepartmentDao departmentDao;
//	@Resource
//	public void setCommonDao(CommonDao<Person> commonDao) {
//		this.commonDao = personDao;
//	}
	
	@Resource
	@Override
	public void setCommonDao(CommonDao<Department> commonDao) {
		this.commonDao = departmentDao;
	}
}