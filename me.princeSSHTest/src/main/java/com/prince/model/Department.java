package com.prince.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

@Entity
@Indexed
public class Department {

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
    
	@Field
	@Column(name="name", length=25, nullable=false)
	private String name;
	
	@Column(name="description", length=600, nullable=true)
	private String description;

	public Department(Integer id, String name, String description) {
		this.id = id;
		this.name = name;
		this.description = description;
	}
	
	
	public Department(Integer id) {
		super();
		this.id = id;
	}
	
	public Department() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


	@Override
	public String toString() {
		return "Department [id=" + id + ", name=" + name + ", description="
				+ description + "]";
	}
	
}
