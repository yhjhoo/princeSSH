package com.prince.test.dao;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.lucene.document.DateTools;
import org.apache.lucene.document.DateTools.Resolution;
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
import org.springframework.test.annotation.Commit;
import org.springframework.test.annotation.Repeat;
import org.springframework.test.annotation.Rollback;

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
//@Rollback
@Commit
@Transactional
public class DepartmentDaoTest {
	@Resource
	private DepartmentDao departmentDao;
	
	@Test
	public void testCreate() {//throws Exception{
		Department dept = departmentDao.getById(9);
		System.out.println(dept);
		
		dept.setName("IT13");
		departmentDao.update(dept);
	}
	
}
