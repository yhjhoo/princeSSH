package com.prince.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.PostLoad;
import javax.persistence.PreUpdate;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.DateBridge;
import org.hibernate.search.annotations.DynamicBoost;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;
import org.hibernate.search.annotations.Resolution;
import org.hibernate.search.annotations.SortableField;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.web.config.SpringDataWebConfigurationMixin;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.prince.model.enums.Gender;

@Entity
@Indexed
//@DynamicBoost(impl=DateBoostStrategy.class)
public class Person implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Field(name="personId")
	private Integer id;

	@Column
	@Length(max=10)
//	@Audited
	private String lastName;
	
	@Column
	@Length(max=10)
	@Field(index=Index.YES)
//	@Audited
	private String firstName;
	
	
	@Column
	@Length(max=100)
	@Field
	@Email
//	@Audited
	private String email;

	@IndexedEmbedded(depth=2)
	@ManyToMany
//	@JoinColumn(name = "departement_fk", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_Person_departement"))
	@JoinTable(
	        joinColumns = {@JoinColumn(name = "personId", foreignKey = @ForeignKey(name = "fk_personId"))},
	        inverseJoinColumns = {@JoinColumn(name = "departmentId", foreignKey = @ForeignKey(name = "fk_departmentId"))},
	        uniqueConstraints = {@UniqueConstraint(columnNames = {"personId", "departmentId"})}
	)
	@Cascade({ CascadeType.SAVE_UPDATE })
	// @ForeignKey(name = "fk_Person_departement")
	@JsonIgnore
	private Set<Department> departements;
	
	
	//@Field
	@IndexedEmbedded
	@ManyToOne
	@JoinColumn(name = "location_fk", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_Person_location"))
	@Cascade({ CascadeType.SAVE_UPDATE })
	// @ForeignKey(name = "fk_Person_departement")
	private WorldCity worldCity;
	
	
	@Column(name = "createDate")
	@Field(analyze=Analyze.NO)
	@SortableField
	@DateBridge(resolution = Resolution.DAY)
	private Date createDate; // CRE_ON DATE,
	
	
	/*@Column(name="gender", insertable=false, updatable=false, length=30)
	private String genderStr;*/
	
	@Column(name="gender", length = 10)
	@NotNull
	@Enumerated(EnumType.STRING)
	private Gender gender = Gender.M;

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

	@Override
	public String toString() {
		return "Person [id=" + id + ", lastName=" + lastName + ", firstName=" + firstName + "]";
	}

	public Set<Department> getDepartements() {
		return departements;
	}

	public void setDepartements(Set<Department> departements) {
		this.departements = departements;
	}
	
	@Field(name="hasFever")
	public boolean getHasFever(){
		if("fever".equalsIgnoreCase(firstName) ){
			return true;
		}
		
		return false;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((departements == null) ? 0 : departements.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((worldCity == null) ? 0 : worldCity.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		if (departements == null) {
			if (other.departements != null)
				return false;
		} else if (!departements.equals(other.departements))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (worldCity == null) {
			if (other.worldCity != null)
				return false;
		} else if (!worldCity.equals(other.worldCity))
			return false;
		return true;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	@PreUpdate
	public void preUpdate(){
		System.out.println("preUpdate");
	}
	
	@PostLoad
	public void postLoad(){
		System.out.println("postLoad");
	}
}
