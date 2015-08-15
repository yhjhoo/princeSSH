package com.prince.test.service;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Repeat;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.prince.model.Department;
import com.prince.model.Person;
import com.prince.model.WorldCity;
import com.prince.service.DepartmentService;
import com.prince.service.PersonService;
import com.prince.service.WorldCityService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={
	"classpath*:Spring/Spring_*.xml"
	})
//@TestExecutionListeners({
//	DependencyInjectionTestExecutionListener.class,
//	TransactionalTestExecutionListener.class})
@TransactionConfiguration(transactionManager="transactionManager", defaultRollback=false)
//@Transactional
public class PersonServiceTest {

	@Resource
	private PersonService personService;
	@Resource
	private DepartmentService departmentService;
	
	@Resource
	private WorldCityService worldCityService;
	
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
//	@Repeat(1000)
	public void testCreate(){
		List<Person> list = personService.findAll();
		System.out.println("list size: " + list.size() );
		
		Person person = new Person();
		person.setFirstName("first Name");
		person.setLastName("last Name");
		
		Person person1 = new Person();
		person1.setFirstName("first Name 123456");
		person1.setLastName("last Name");
		
		try {
			personService.save(person);
			//personService.save1(person);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		List<Person> list1 = personService.findAll();
		System.out.println("list1 size: " + list1.size() );
//		Assert.isTrue(list.size() == (list1.size()-1));
	}
	
	@Test
	public void testGetNames() throws IOException{
		Path file = Paths.get("names.txt");
		List<String> lines = Files.readAllLines(file, StandardCharsets.UTF_8);
		System.out.println("list size: " + lines.size() );
	}

	@Test
	@Repeat(112264)
	public void testCreateBatch() throws IOException{
		//List<Person> list = personService.findAll();
		//System.out.println("list size: " + list.size() );
		
		Department departement = new Department();
//		departement.setName("test Name1");
//		departement.setDescription("test Description");
		Random rand = new Random();
		departement = departmentService.getById(rand.nextInt(7));
		
		WorldCity wc = new WorldCity();
		//wc.setId(rand.nextInt(112264));
		wc = worldCityService.getById(rand.nextInt(112264) );
		
		
		Path file = Paths.get("names.txt");
		List<String> lines = Files.readAllLines(file, StandardCharsets.UTF_8);
		System.out.println("list size: " + lines.size() );
		//for(int i=0;i<1;i++){
		for(String line : lines){
			String[] names = line.split(" ");
			Person p = new Person();
			
			if(names.length > 1){
				p.setFirstName(names[0]);
				p.setLastName(names[1]);
			}else{
				p.setFirstName(names[0] + " " + lines.get(rand.nextInt(500)));
				p.setLastName(names[0] + " " + lines.get(rand.nextInt(500)));
			}
			
			p.setDepartement(departement);
			p.setWorldCity(wc);
			personService.save(p);	
			Assert.notNull(p);
		}
		
		//List<Person> list1 = personService.findAll();
		//System.out.println("list1 size: " + list1.size() );
		//Assert.isTrue(list.size() == (list1.size()-1));
	}
	
	@Test
	public void testCreateBatchRollback() throws SQLException{
		Department departement = new Department();
		departement.setName("test Name1");
		departement.setDescription("test Description");
		
		List<Person> persons = new ArrayList<Person>();
		
		for(int i=0;i<500;i++){
			Person p = new Person();
			p.setFirstName("first Name");
			p.setLastName("last Name");
			//p.setDepartement(departement);
			persons.add(p);
		}
		
		personService.saveBatch(persons);
		
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
	
	
	@Test
	public void testSqlRestriction(){
		List<Person> list = personService.findBySQLRestrication();
		System.out.println("list size: " + list.size() );
		
	}
	
	
}
