package com.prince.model.enums;
/**
 * 
 * @author SGSPYHJX
 *
 */
public enum Gender {
	M("Male"), F("Female");
	
	private final String value;
	private Gender(String value) {
		this.value = value;
	}
	
	public String value() {
		return value;
	}
	
	@Override
	public String toString() {
		return value;
	}
	
}
