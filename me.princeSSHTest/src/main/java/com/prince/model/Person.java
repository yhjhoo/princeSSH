package com.prince.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;
import org.hibernate.validator.constraints.Length;

@Entity
@Indexed
public class Person {
	@Id
	@GeneratedValue
	private Integer id;

	@Column
	@Length(max=10)
	private String lastName;
	
	@Column
	@Length(max=10)
	private String firstName;

	@IndexedEmbedded
	@ManyToOne
	@JoinColumn(name = "departement_fk", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_Person_departement"))
	@Cascade({ CascadeType.SAVE_UPDATE })
	// @ForeignKey(name = "fk_Person_departement")
	private Department departement;
	
	
	//@Field
	@IndexedEmbedded
	@ManyToOne
	@JoinColumn(name = "location_fk", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_Person_location"))
	@Cascade({ CascadeType.SAVE_UPDATE })
	// @ForeignKey(name = "fk_Person_departement")
	private WorldCity worldCity;


	public Department getDepartement() {
		return departement;
	}

	@Override
	public String toString() {
		return "Person [id=" + id + ", lastName=" + lastName + ", firstName="
				+ firstName 
				+", departement=" + departement 
				+", worldCity=" + worldCity 
				+ "]";
	}

	public void setDepartement(Department departement) {
		this.departement = departement;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public WorldCity getWorldCity() {
		return worldCity;
	}

	public void setWorldCity(WorldCity worldCity) {
		this.worldCity = worldCity;
	}
}
