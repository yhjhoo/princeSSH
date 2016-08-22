package com.prince.dao;

import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.prince.model.ContractEmployee;
import com.prince.model.Person;

@Repository
public class ContractEmployeeDao extends CommonDao<ContractEmployee>{
}