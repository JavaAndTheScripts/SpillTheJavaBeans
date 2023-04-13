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
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="drinks")
public class Drink {
// ==========================
//        ATTRIBUTES
// ==========================
    // create unique id
    @Id		
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank
    private String name;
    
    @NotBlank
    private String description;
    
    @NotBlank
    private String ingredients;
    
    @NotNull
    private Boolean isHot;
    
    @NotNull
    private Double price;

    // This will not allow the createdAt column to be updated after creation
    @Column(updatable=false)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date createdAt;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date updatedAt;

// ==========================
//       RELATIONSHIPS
// ==========================  
    // single coffee
    // list of DrinkOnMenu
    
// ==========================
//        CONSTRUCTOR
// ==========================
    public Drink(){}

// ==========================
//     GETTERS / SETTERS
// ==========================
    @PrePersist
    protected void onCreate(){	this.createdAt = new Date();}
	public Date getCreatedAt() {	return createdAt;	}
	public void setCreatedAt(Date createdAt) {	this.createdAt = createdAt;	}
	
    @PreUpdate
    protected void onUpdate(){	this.updatedAt = new Date();}
	public Date getUpdatedAt() {	return updatedAt;	}
	public void setUpdatedAt(Date updatedAt) {	this.updatedAt = updatedAt;	}
    // add getters/setters for ALL attributes 
   

	public Long getId() {	return id;	}
	public void setId(Long id) {	this.id = id;	}

	public String getName() {	return name;	}
	public void setName(String name) {	this.name = name;	}

	public String getDescription() {	return description;	}
	public void setDescription(String description) {	this.description = description;	}

	public String getIngredients() {	return ingredients;	}
	public void setIngredients(String ingredients) {	this.ingredients = ingredients;	}

	public Boolean getIsHot() {	return isHot;	}
	public void setIsHot(Boolean isHot) {	this.isHot = isHot;	}

	public Double getPrice() {	return price;	}
	public void setPrice(Double price) {	this.price = price;	}
    
    // TODO: Relationship getters and setters
}
