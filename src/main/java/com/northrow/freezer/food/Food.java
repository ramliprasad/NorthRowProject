package com.northrow.freezer.food;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * <h1> Food </h1>
 * The Food program is the main Entity. It represents the way a Food is classified.
 * It has details such as 
 * <ul>
 * <li>id - Auto generated primary key field</li>
 * <li>name - Name of the food</li>
 * <li>type - type of food</li>
 * <li>quantity - quantity of food</li>
 * <li>createdDate - Creation date</li>
 * <li>updatedDate - Updation date, initially null on creation</li>
 * <ul>
 * @author Prasad Ramalingam
 * @version 1.0
 * @since 13-01-2019
 *
 */
@Entity
public class Food {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String id;
	private String name;
	private String type;
	private int quantity;
	@Temporal(TemporalType.DATE)
	private Date createdDate;

	@Temporal(TemporalType.DATE)
	private Date updatedDate;
	
	public Food() {
		super();
	}

	public Food(String id, String name, String type, int quantity) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
		this.quantity = quantity;
		this.createdDate = new Date();
	}
	
	public Food( String name, String type, int quantity) {
		super();
		this.name = name;
		this.type = type;
		this.quantity = quantity;
		this.createdDate = new Date();
	}
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	
}