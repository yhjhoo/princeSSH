package com.prince.test.dao;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.Sort;
import org.apache.lucene.search.SortField;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.hibernate.search.FullTextQuery;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.hibernate.search.query.dsl.QueryContextBuilder;
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
import com.prince.dao.WorldCityDao;
import com.prince.model.Department;
import com.prince.model.Person;
import com.prince.model.WorldCity;
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
public class PersonDaoTest {

	@Resource
	private PersonDao personDao;
	
	@Resource
	private WorldCityDao worldCityDao;
	
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
	public void testIndexSearch() throws InterruptedException{
		testIndexPerson();
		testSearchPerson1();
	}
	

	@SuppressWarnings("unchecked")
	@Test
	public void testIndexPerson() throws InterruptedException{
		/*Session session = personDao.getSessionFactory().getCurrentSession();
		FullTextSession fullTextSession = Search.getFullTextSession(session);
		
		Person person = personDao.getById(1);
		
		fullTextSession.index(person);*/
		
		
		Session session = personDao.getSessionFactory().getCurrentSession();
		FullTextSession fullTextSession = Search.getFullTextSession(session);
		fullTextSession.createIndexer().startAndWait();
	}
	
	@Test
	public void testSearchPerson1(){
		Session session = personDao.getSessionFactory().getCurrentSession();
		FullTextSession fullTextSession = Search.getFullTextSession(session);
		QueryBuilder qb = fullTextSession.getSearchFactory().buildQueryBuilder().forEntity(Person.class).get();
		
		Query luceneQuery = 
				qb.keyword()
//				qb.phrase()
//				.onFields( "firstName", "departements.name")
				.onFields("firstName").matching("first")
//				.onField("firstName").sentence("first 12")
//				.onFields("hasFever").matching(false)
				.createQuery();
		
		FullTextQuery fq = fullTextSession.createFullTextQuery(luceneQuery, Person.class);
//		Sort sort = new Sort(new SortField("departements.name", SortField.STRING, true));
//		fq.setSort(sort);
		List<Person> list = fq.list();
		
		System.out.println("list size: " + fq.getResultSize() );
		
		System.out.println("list size: " + list.size() );
		for(Person wc : list){
			System.out.println(wc);
//			for(Department d : wc.getDepartements()){
//				System.out.println("============" + d);
//			}
//			wc.setLocal_name(wc.getLocal_name() + " China");
//			worldCityDao.saveOrUpdate(wc);
			
//			System.out.println(wc );
		}
	}
	
	
	@Test
//	@Repeat(1000)
	public void testCreate() {//throws Exception{
		Long sum = personDao.findAllCount();
		System.out.println("list size: " + sum );
		
		int i = 16;
		
		Person p = new Person();
		p.setFirstName("first " + i);
		p.setLastName("last " + i);
		p.setCreateDate(new Date() );
		
		/*List<Department> listD = departmentDao.findAll();
		if(listD==null || listD.size()==0){
			Department departement = new Department();
			departmentDao.save(departement);
			p.setDepartement(departement);
		}else{
			p.setDepartement(listD.get(0));
		}*/
		
		
		Department departement1 = new Department();
		departement1.setName("dept" + i);
		departement1.setDescription("dept" + i + " desc");
		
		Department departement2 = new Department();
		departement2.setName("dept 2 " + i);
		departement2.setDescription("dept 2 desc" + i);
		
		Set<Department> departements = new HashSet<>();
		departements.add(departement1);
		departements.add(departement2);
		
		p.setDepartements(departements );
		
		try {
			personDao.save(p);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/*if(true){
			throw new Exception("fds");
		}*/
		
//		Long sum1 = personDao.findAllCount();
//		System.out.println("list1 size: " + sum1 );
//		Assert.isTrue(sum == (sum1 - 1));
	}
	
	
	@Test
	public void testFindByCreatiaWorldCity(){
		Set<Criterion> criterions = new HashSet<Criterion>();
		criterions.add(Restrictions.like("local_name", "%Singapore refactor China%") );
		
		List<WorldCity> list = worldCityDao.findByCreateria(criterions, 10, 1);
		
		System.out.println("list size: " + list.size() );
		for(WorldCity wc : list){
			wc.setLocal_name(wc.getLocal_name() + " China");
			worldCityDao.update(wc);
			System.out.println(wc);
		}
	}
	
	@Test
	public void testFindByCreatia(){
		Set<Criterion> criterions = new HashSet<Criterion>();
		//criterions.add(Restrictions.like("firstName", "%yang ning%") );
		
		//criterions.add(Restrictions.like("d.name", "%network%") );
		//criterions.add(Restrictions.like("wc.local_name", "%singapore%") );
		criterions.add(Restrictions.isNull("p.wc.id") );
		
		List<Person> list = personDao.findByCreateria(criterions, 10, 1);
		
		System.out.println("list size: " + list.size() );
		for(Person wc : list){
			System.out.println(wc);
		}
	}
	
	@Test
	public void testIndex() throws InterruptedException{
		Session session = personDao.getSessionFactory().getCurrentSession();
		FullTextSession fullTextSession = Search.getFullTextSession(session);
		fullTextSession.createIndexer().startAndWait();
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testSearch(){
		Session session = personDao.getSessionFactory().getCurrentSession();
		FullTextSession fullTextSession = Search.getFullTextSession(session);
		QueryBuilder qb = fullTextSession.getSearchFactory().buildQueryBuilder().forEntity(WorldCity.class).get();
		
		Query luceneQuery = qb.keyword().onFields("local_name")
				.matching(null).createQuery();
		List<WorldCity> list = fullTextSession.createFullTextQuery(luceneQuery, WorldCity.class).list();
		
		System.out.println("list size: " + list.size() );
		for(WorldCity wc : list){
			System.out.println(wc);
//			wc.setLocal_name(wc.getLocal_name() + " China");
//			worldCityDao.saveOrUpdate(wc);
			
//			System.out.println(wc );
		}
	}
	
	
	@SuppressWarnings("unchecked")
	@Test
	public void testSearchPerson(){
		Session session = personDao.getSessionFactory().getCurrentSession();
		FullTextSession fullTextSession = Search.getFullTextSession(session);
		QueryBuilder qb = fullTextSession.getSearchFactory().buildQueryBuilder().forEntity(Person.class).get();
		
//		Query luceneQuery = qb.keyword().onFields("local_name", "iso")
//				.matching("Singapore refactor").createQuery();
		
		
//		Query luceneQuery = qb.keyword().onFields("firstName", "departement.name")
//				.matching("network").createQuery();
		Query luceneQuery = qb
					.bool()
					.must(qb.keyword().onField("firstName").matching("first").createQuery())
					//.must(qb.keyword().onField("departement.name").matching("network").createQuery())
					//.must(qb.keyword().onField("worldCity.local_name").createQuery())
					.createQuery();
		
					
		List<Person> list = fullTextSession.createFullTextQuery(luceneQuery, Person.class).setMaxResults(10).list();
		
		System.out.println("list size: " + list.size() );
		for(Person wc : list){
			System.out.println(wc);
//			wc.setLocal_name(wc.getLocal_name() + " China");
//			worldCityDao.saveOrUpdate(wc);
			
//			System.out.println(wc );
		}
	}
	
}
