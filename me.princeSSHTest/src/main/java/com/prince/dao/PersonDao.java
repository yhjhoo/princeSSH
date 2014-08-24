package com.prince.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.prince.model.Person;

@Repository
public class PersonDao extends CommonDao<Person>{

}