package com.pro.model;


import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="currency_exacgange")
public class Choice {
	
	@Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
	
	@Column(name="id")
	private Integer id;
	
	@Column(name="name")

	private String productName;
	private double price;
	private String catgorie;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	

	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getCatgorie() {
		return catgorie;
	}
	public void setCatgorie(String catgorie) {
		this.catgorie = catgorie;
	}

	
	
	
	

	
	

	
}