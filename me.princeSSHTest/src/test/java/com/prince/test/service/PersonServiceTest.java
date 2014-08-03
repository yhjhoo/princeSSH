package com.prince.test.service;
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

import com.opensymphony.xwork2.inject.Inject;
import com.prince.model.Department;
import com.prince.model.Person;
import com.prince.service.PersonService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={
		"classpath*:Spring/Spring_*.xml"
		})
@TestExecutionListeners({
	DependencyInjectionTestExecutionListener.class,
	TransactionalTestExecutionListener.class})
@TransactionConfiguration(transactionManager="transactionManager", defaultRollback=false)
@Transactional
public class PersonServiceTest {

	@Resource
	private PersonService personService;
	
	@Test
	public void testFindAll(){
		System.out.println("Hello Test");
		Assert.notNull(personService);
		List<Person> list = personService.findAll();
		System.out.println("list size: " + list.size() );
		for(Person p : list){
			System.out.println(p);
		}	
	}
	
	@Test
	public void testCreateRollback(){
		List<Person> list = personService.findAll();
		System.out.println("list size: " + list.size() );
		
		Person person = new Person();
		person.setFirstName("first Name Test");
		person.setLastName("last Name Test");
		
		personService.saveRollback(person);
		
		List<Person> list1 = personService.findAll();
		System.out.println("list1 size: " + list1.size() );
		Assert.isTrue(list.size() == (list1.size()-1));
	}

	@Test
	public void testCreateBatch(){
		List<Person> list = personService.findAll();
		System.out.println("list size: " + list.size() );
		
		Department departement = new Department();
		departement.setName("test Name");
		departement.setDescription("test Description");
		
		for(int i=0;i<2;i++){
			Person p = new Person();
			p.setFirstName("first Name Test " + i);
			p.setLastName("last Name Test " + i);
			p.setDepartement(departement);
			personService.save(p);
			
		}
		
		List<Person> list1 = personService.findAll();
		System.out.println("list1 size: " + list1.size() );
		Assert.isTrue(list.size() == (list1.size()-2));
	}
	
	@Test
	public void testDelete(){
		//testCreate();
		List<Person> list = personService.findAll();
		System.out.println("list size: " + list.size() );
		
		personService.delete(list.get(0));
		List<Person> list1 = personService.findAll();
		System.out.println("list1 size: " + list1.size() );
		Assert.isTrue(list.size() == (list1.size()+1));
		
	}
}
