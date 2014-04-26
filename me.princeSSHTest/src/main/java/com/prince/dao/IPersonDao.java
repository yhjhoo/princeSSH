package com.prince.dao;

import org.springframework.data.repository.CrudRepository;

import com.prince.model.Person;


public interface IPersonDao extends CrudRepository<Person, Long> {

}
