package com.prince.webmvc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.prince.model.Person;
import com.prince.service.PersonService;

@RestController
@RequestMapping("/rest")
public class WelcomeController {
	@Autowired
	private PersonService personService;
	
	@RequestMapping(value="/person", method=RequestMethod.GET)
	public List<Person> index(){
		return personService.findAll();
	}
	
	@RequestMapping(value="/test/person1", method=RequestMethod.GET)
	public List<Person> index1(){
		return personService.findAll();
	}
	
	@RequestMapping(value="/test/person2", method=RequestMethod.GET)
	public Person personId(@RequestParam Integer id){
		return personService.getById(id);
	}
}
