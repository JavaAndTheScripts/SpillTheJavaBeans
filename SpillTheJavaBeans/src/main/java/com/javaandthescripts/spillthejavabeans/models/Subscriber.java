package com.javaandthescripts.spillthejavabeans.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="subscribers")
public class Subscriber extends User{
// ==========================
//        ATTRIBUTES
// ==========================  
    @NotNull(message="Enter your Birthday for a free drink that month.")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthday;
    
    private Boolean usedPuzzle = false;
    private Boolean usedBday = false;

// ==========================
//        CONSTRUCTOR
// ==========================
    public Subscriber(){ super(); }

// ==========================
//     GETTERS / SETTERS
// ==========================

	public Date getBirthday() {	return birthday;	}
	public void setBirthday(Date birthday) {	this.birthday = birthday;	}

	public Boolean getUsedPuzzle() {	return usedPuzzle;	}
	public void setUsedPuzzle(Boolean usedPuzzle) {	this.usedPuzzle = usedPuzzle;	}

	public Boolean getUsedBday() {	return usedBday;	}
	public void setUsedBday(Boolean usedBday) {	this.usedBday = usedBday;	}    
}
