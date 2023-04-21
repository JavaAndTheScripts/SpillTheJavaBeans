package com.javaandthescripts.spillthejavabeans.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
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
    
    @NotBlank(message="The Drink must have a name to display on the menu.")
    private String name;
    
    @NotBlank(message="A description for the drink is required.")
    private String description;
    
    @NotBlank(message="Ingredients for making the drink is required.")
    private String ingredients;
    
    @NotNull(message="Select if the drink can be served hot.")
    private Boolean isHot;
    
    @NotNull(message="Select if the drink can be served cold.")
    private Boolean isCold;
    
    @NotNull(message="A price must be entered for a small sized cup.")
    private Double bytePrice;
    
    @NotNull(message="A price must be entered for a medium sized cup.")
    private Double intPrice;
    
    @NotNull(message="A price must be entered for a large sized cup.")
    private Double longPrice;

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
    // Many-to-One
    // <Drink> >--- <coffee>
    @NotNull(message="A coffee must be selected for the drink.") // this one cannot be empty
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="coffee_id")
    private Coffee coffee;
    
    // single cafe
    // Many-to-One
    // <Drink> >--- <cafe>
    // this one can be empty
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="cafe_id")
    private Cafe cafe;
    
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
	
	public Boolean getIsCold() {	return isCold;	}
	public void setIsCold(Boolean isCold) {	this.isCold = isCold;	}

    public Double getBytePrice() {	return bytePrice;	}
	public void setBytePrice(Double bytePrice) {	this.bytePrice = bytePrice;	}

	public Double getIntPrice() {	return intPrice;	}
	public void setIntPrice(Double intPrice) {	this.intPrice = intPrice;	}

	public Double getLongPrice() {	return longPrice;	}
	public void setLongPrice(Double longPrice) {	this.longPrice = longPrice;	}

	// Relationship getters and setters
	public Coffee getCoffee() {	return coffee;	}
	public void setCoffee(Coffee coffee) {	this.coffee = coffee;	}

	public Cafe getCafe() {	return cafe;	}
	public void setCafe(Cafe cafe) {	this.cafe = cafe;	}		
}
