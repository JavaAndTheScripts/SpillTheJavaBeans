package com.javaandthescripts.spillthejavabeans.models;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="puzzles")
public class Puzzle {
// ==========================
//        ATTRIBUTES
// ==========================
    // create unique id
    @Id		
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message="A title for the puzzle is required.")
    private String title;
    
    @NotBlank(message="Contents for the puzzle are reguired.")
    private String contents;
    
    @NotBlank(message="A solution for the puzzle is required.")
    private String solution;
    
    @NotBlank(message="A reward for winning the puzzle is required.")
    private String reward;
    
    @Min(value = 0, message="The percentage value must be between 0 and 100")
    @Max(value = 100, message="The percentage value must be between 0 and 100")
    @NotNull(message="A percentage for the puzzle's reward is required.")
    private Float percent;

    // This will not allow the createdAt column to be updated after creation
    @Column(updatable=false)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date createdAt;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date updatedAt;

// ==========================
// 	      RELATIONSHIPS
// ==========================
    // cafe
    // One-to-One Attached Side
 	// Puzzle ---- Cafe
 	@OneToOne(fetch=FetchType.LAZY)
 	@JoinColumn(name="cafe_id")
	private Cafe cafe;
 
	// subscriber
	// One-to-Many
	// Puzzle ---< Subscriber
	@OneToMany(mappedBy="puzzle", cascade=CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Subscriber> subs;
    
// ==========================
//        CONSTRUCTOR
// ==========================
    public Puzzle(){}

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

	public String getTitle() {	return title;	}
	public void setTitle(String title) {	this.title = title;	}

	public String getContents() {	return contents;	}
	public void setContents(String contents) {	this.contents = contents;	}

	public String getSolution() {	return solution;	}
	public void setSolution(String solution) {	this.solution = solution;	}

	public String getReward() {	return reward;	}
	public void setReward(String reward) {	this.reward = reward;	}

	public Float getPercent() {	return percent;	}
	public void setPercent(Float percent) {	this.percent = percent;	}

	// Relationship getters and setters
	public Cafe getCafe() {	return cafe;	}
	public void setCafe(Cafe cafe) {	this.cafe = cafe;	}

	public List<Subscriber> getSubs() {	return subs;	}
	public void setSubs(List<Subscriber> subs) {	this.subs = subs;	}
	
}//Puzzle
