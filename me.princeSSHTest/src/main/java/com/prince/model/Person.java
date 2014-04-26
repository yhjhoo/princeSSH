package com.prince.model;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
public class Person {
    @Id
    @GeneratedValue
    private Integer id;
    private String lastName;
    private String firstName;
    
    @ManyToOne
    @JoinColumn(name="departement_fk", referencedColumnName="id", 
    	foreignKey = @ForeignKey(name = "fk_Person_departement"))
    @Cascade({ CascadeType.SAVE_UPDATE })
//    @ForeignKey(name = "fk_Person_departement")
    private Department departement;

    public Department getDepartement() {
		return departement;
	}

	@Override
	public String toString() {
		return "Person [id=" + id + ", lastName=" + lastName + ", firstName="
				+ firstName + ", departement=" + departement + "]";
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
}
