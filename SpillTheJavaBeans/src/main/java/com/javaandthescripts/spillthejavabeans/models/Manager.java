package com.javaandthescripts.spillthejavabeans.models;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name="managers")
public class Manager extends User {
// ==========================
//        ATTRIBUTES
// ==========================    
    @NotBlank
    private String phoneNum;
    
    @NotBlank
    private String address;
    
    @NotBlank
    private String city;
    
    @NotBlank
    private String state;
    
    @NotBlank
    private String zipcode;
    
// ==========================
//  	 RELATIONSHIPS
// ==========================   

// ==========================
//        CONSTRUCTOR
// ==========================
    public Manager(){ super(); }

// ==========================
//     GETTERS / SETTERS
// ==========================
	public String getPhoneNum() {	return phoneNum;	}
	public void setPhoneNum(String phoneNum) {	this.phoneNum = phoneNum;	}

	public String getAddress() {	return address;	}
	public void setAddress(String address) {	this.address = address;	}

	public String getCity() {	return city;	}
	public void setCity(String city) {	this.city = city;	}

	public String getState() {	return state;	}
	public void setState(String state) {	this.state = state;	}

	public String getZipcode() {	return zipcode;	}
	public void setZipcode(String zipcode) {	this.zipcode = zipcode;	}
}
