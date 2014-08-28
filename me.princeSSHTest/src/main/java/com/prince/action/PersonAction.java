package com.prince.action;

import java.util.List;









import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;
import com.opensymphony.xwork2.inject.Inject;
import com.prince.model.Person;
import com.prince.service.PersonService;

public class PersonAction extends ActionSupport implements Preparable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 2156655858735306357L;

	@Resource
	private PersonService personService;
    
    private List<Person> persons;
    private Person person;
    private Integer id;
    
    private final Log log = LogFactory.getLog(this.getClass());

    /*public PersonAction(PersonService service) {
        this.service = service;
    }*/

    public String listAll() {
    	//personService.findAll1();
        this.persons = personService.findAll1();
        return SUCCESS;
    }

    public String save() {
//        this.service.save(person);
        this.person = new Person();
//        return execute();
        return SUCCESS;
    }

    public String remove() {
//        service.remove(id);
        return SUCCESS;
    }

    public List<Person> getPersons() {
        return persons;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void prepare() throws Exception {
//        if (id != null)
//            person = service.getById(id);
    	log.info("prepare");
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
