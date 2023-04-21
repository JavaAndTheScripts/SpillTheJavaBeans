package com.javaandthescripts.spillthejavabeans.models;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
    
    private Boolean solvedPuzzle = false;
    private Boolean usedBday = false;

// ==========================
// 	      RELATIONSHIPS
// ==========================  
    // puzzle
	// Many-to-One
	// Subscriber >--- Puzzle
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="puzzle_id")
	private Puzzle puzzle;
    
// ==========================
//        CONSTRUCTOR
// ==========================
    public Subscriber(){ super(); }

// ==========================
//     GETTERS / SETTERS
// ==========================
	public Date getBirthday() {	return birthday;	}
	public void setBirthday(Date birthday) {	this.birthday = birthday;	}

	public Boolean getSolvedPuzzle() {	return solvedPuzzle;	}
	public void setSolvedPuzzle(Boolean solvedPuzzle) {	this.solvedPuzzle = solvedPuzzle;	}

	public Boolean getUsedBday() {	return usedBday;	}
	public void setUsedBday(Boolean usedBday) {	this.usedBday = usedBday;	}  
	
	public Puzzle getPuzzle() {	return puzzle;	}
	public void setPuzzle(Puzzle puzzle) {		this.puzzle = puzzle;	}

// ==========================
//  		METHODS
// ==========================	
	// this method will be used to determine if the subscriber has access to the birthday coupon
	// returns: True (if they can have access), False (if they should not have access)
	public boolean bdayCheck() {
		// convert birthday to LocalDate
		LocalDate bday = birthday.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		// get the now LocalDate
		LocalDate today = LocalDate.now();
		
		// find the month for both LocalDates
		int bdayM = bday.getMonthValue();
		int todayM = today.getMonthValue();
		
		// if their birthday is this month AND they have not used the coupon
		if(bdayM == todayM && this.usedBday == false) {
			return true; // give access to coupon
		}// if
		// else if their birthday is not this month AND their coupon is marked as used
		else if(bdayM != todayM && this.usedBday == true) {
			this.usedBday = false; 	// make sure usedBday is false
		}// else if		
		
		return false;
	}
}
