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

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="cafes")
public class Cafe {
// ==========================
//        ATTRIBUTES
// ==========================
    // create unique id
    @Id		
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String puzzle;
    
    // This will not allow the createdAt column to be updated after creation
    @Column(updatable=false)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date createdAt;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date updatedAt;
    
// ==========================
// 	      RELATIONSHIPS
// ==========================
    // coffee - COFFEE OF THE MONTH
	 // One-to-One Attached Side
	 // Coffee ---- Cafe
//	 @OneToOne(fetch=FetchType.LAZY)
//	 @JoinColumn(name="coffee_id")
//	 private Coffee monthlyCoffee;
    
    // list of menu
    
    // list of subscribers
    
    // list of managers
    
    
// ==========================
//        CONSTRUCTOR
// ==========================
    public Cafe(){}

// ==========================
//     GETTERS / SETTERS
// ==========================
    @PrePersist
    protected void onCreate(){	this.createdAt = new Date(); }
	public Date getCreatedAt() {	return createdAt;	}
	public void setCreatedAt(Date createdAt) {	this.createdAt = createdAt;	}
	
    @PreUpdate
    protected void onUpdate(){	this.updatedAt = new Date(); }
	public Date getUpdatedAt() {	return updatedAt;	}
	public void setUpdatedAt(Date updatedAt) {	this.updatedAt = updatedAt;	}  
	
    // add getters/setters for ALL attributes 
	public Long getId() {	return id;	}
	public void setId(Long id) {	this.id = id;	}
	
	public String getPuzzle() {	return puzzle;	}
	public void setPuzzle(String puzzle) {	this.puzzle = puzzle;	}
	
	// TODO: Relationship getters and setters
}
