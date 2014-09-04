package com.prince.test.service;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
@Transactional
public class WorldCityServiceTest {

	@Resource
	private WorldCityService worldCityService;
	
	@Test
	
	public void testFindAll(){
		System.out.println("Hello Test");
		Assert.notNull(worldCityService);
		List<WorldCity> list = worldCityService.findAll();
		System.out.println("list size: " + list.size() );
		for(WorldCity p : list){
			System.out.println(p);
		}	
	}
	
		
}
