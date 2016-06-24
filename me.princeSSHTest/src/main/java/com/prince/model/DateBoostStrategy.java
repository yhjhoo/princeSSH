package com.prince.model;

import java.util.Date;

import org.hibernate.search.engine.BoostStrategy;

public class DateBoostStrategy implements BoostStrategy{

	@Override
	public float defineBoost(Object value) {
//		Date person = ( Date ) value;
//		if(person.getDate() == 23){
//			return 20000f;
//		}
//		
//		return 0f;
//		return person.getDate();
//		return person.getTime();
		
		Person person = (Person) value;
		/*if(person.getId() == 6){
			return 2f;
		}
		return 1f;*/
		
		Date date = person.getCreateDate();
		
		return date.getYear() * 60 * 60 * 24 *  31 * 365 + 
		date.getMonth()  * 60 * 60 * 24 * 31 + 
		date.getDay() * 60 * 60 * 24 + 
		date.getHours() * 60 * 60 + 
		date.getMinutes() * 60 + 
		date.getSeconds();
		
//		return person.getCreateDate().getTime();
	}

}
