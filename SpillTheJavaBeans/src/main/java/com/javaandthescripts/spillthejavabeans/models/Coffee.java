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
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "coffees")
public class Coffee {
// ==========================
//        ATTRIBUTES
// ==========================
	// create unique id
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@Min(0)
	@Max(2)
	private Short roast; // 0 - Light, 1 - Medium, 2 - Dark

	@NotBlank
	private String region;

	@NotBlank
	private String flavors;

	// This will not allow the createdAt column to be updated after creation
	@Column(updatable = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date createdAt;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date updatedAt;

// ==========================
// 	      RELATIONSHIPS
// ========================== 
	// a single cafe
	// One-to-One Dominate Side
	// Coffee ---- cafe
//	@OneToOne(mappedBy="monthlyCoffee", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
//	private Cafe cafe;

	// list of drinks

// ==========================
//        CONSTRUCTOR
// ==========================
	public Coffee() {
	}

// ==========================
//     GETTERS / SETTERS
// ==========================
	@PrePersist
	protected void onCreate() {
		this.createdAt = new Date();
	}

	@PreUpdate
	protected void onUpdate() {
		this.updatedAt = new Date();
	}
	// add getters/setters for ALL attributes

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Short getRoast() {
		return roast;
	}

	public void setRoast(Short roast) {
		this.roast = roast;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getFlavors() {
		return flavors;
	}

	public void setFlavors(String flavors) {
		this.flavors = flavors;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	// TODO: Relationship getters and setters

	// ==========================
	// METHODS
	// ==========================

	public String roastType() {
		switch(this.roast) {
			case 0:
				return "Light Roast";
			case 1:
				return "Medium Roast";
			case 2:
				return "Dark Roast";
			default:
				return null;
		}
	}

} // end of coffee model