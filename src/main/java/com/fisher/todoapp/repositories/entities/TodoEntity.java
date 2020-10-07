package com.fisher.todoapp.repositories.entities;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Todo")
public class TodoEntity implements Serializable {
	private static final long serialVersionUID = -1867171395723996174L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String activity;
	private String category;
	private LocalDate dateAdded;
	private LocalDate dateToComplete;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getActivity() {
		return activity;
	}
	public void setActivity(String activity) {
		this.activity = activity;
	}
	
	public String getCategory() {
		return category;
	}
	public void setCategory(String activityCategory) {
		this.category = activityCategory;
	}
	
	public LocalDate getDateAdded() {
		return dateAdded;
	}
	public void setDateAdded(LocalDate dateAdded) {
		this.dateAdded = dateAdded;
	}
	
	public LocalDate getDateToComplete() {
		return dateToComplete;
	}
	public void setDateToComplete(LocalDate dateToComplete) {
		this.dateToComplete = dateToComplete;
	}
}