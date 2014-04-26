package com.prince.test.dao;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.test.annotation.Repeat;

import com.opensymphony.xwork2.inject.Inject;
import com.prince.dao.DepartmentDao;
import com.prince.dao.PersonDao;
import com.prince.model.Department;
import com.prince.model.Person;
import com.prince.service.PersonService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={
		"classpath*:Spring/Spring_*.xml"
//		"classpath*:spring/applicationContext-command.xml",
//		"classpath*:spring/applicationContext-config.xml",
//		"classpath*:spring/applicationContext-dataAccess.xml",
//		"classpath*:spring/applicationContext-dataSource.xml",
//		"classpath*:spring/applicationContext-repository.xml",
//		"classpath*:spring/applicationContext-2-dataAccess.xml",
//		"classpath*:spring/applicationContext-2-dataSource.xml",
//		"classpath*:spring/applicationContext-email.xml"
		
		})
@TestExecutionListeners({
	DependencyInjectionTestExecutionListener.class,
	TransactionalTestExecutionListener.class})
//@TransactionConfiguration(transactionManager="transactionManager", defaultRollback=false)
@Transactional
public class PersonDaoTest {

	@Resource
	private PersonDao personDao;
	
	@Resource
	private DepartmentDao departmentDao;
	
	@Test
	public void testFindAll(){
		System.out.println("Hello Test");
		Assert.notNull(personDao);
		List<Person> list = personDao.findAll();
		System.out.println("list size: " + list.size() );
		for(Person p : list){
			System.out.println(p);
		}	
	}
	
	@Test
	public void testFindByPK(){
		List<Person> list = personDao.findAll();
		Assert.notNull(list);
		Assert.notEmpty(list);
		
		Person p = personDao.getByPK(list.get(0));
		Assert.notNull(p);
		Assert.notNull(p.getId());
		System.out.println(p);
	}
	
	@Test
//	@Repeat(value = 1000)
	public void testCreate() throws Exception{
		Long sum = personDao.findAllCount();
		System.out.println("list size: " + sum );
		
		Person p = new Person();
		p.setFirstName("first Name Test" + new Date());
		p.setLastName("last Name Test" + new Date());
		
		List<Department> listD = departmentDao.findAll();
		if(listD==null || listD.size()==0){
			Department departement = new Department();
			departmentDao.save(departement);
			p.setDepartement(departement);
		}else{
			p.setDepartement(listD.get(0));
		}
		
		personDao.save(p);
		
		if(true){
			throw new Exception("fds");
		}
		
		Long sum1 = personDao.findAllCount();
		System.out.println("list1 size: " + sum1 );
		Assert.isTrue(sum == (sum1 - 1));
	}
//
//	@Test
//	public void testCreateBatch(){
//		List<Person> list = personService.findAll();
//		System.out.println("list size: " + list.size() );
//		
//		Department departement = new Department();
//		departement.setName("test Name");
//		departement.setDescription("test Description");
//		
//		for(int i=0;i<2;i++){
//			Person p = new Person();
//			p.setFirstName("first Name Test " + i);
//			p.setLastName("last Name Test " + i);
//			p.setDepartement(departement);
//			personService.save(p);
//			
//		}
//		
//		List<Person> list1 = personService.findAll();
//		System.out.println("list1 size: " + list1.size() );
//		Assert.isTrue(list.size() == (list1.size()-2));
//	}
//	
//	@Test
//	public void testDelete(){
//		testCreate();
//		List<Person> list = personService.findAll();
//		System.out.println("list size: " + list.size() );
//		
//		personService.delete(list.get(0));
//		List<Person> list1 = personService.findAll();
//		System.out.println("list1 size: " + list1.size() );
//		Assert.isTrue(list.size() == (list1.size()+1));
//		
//	}
}
