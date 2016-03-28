package com.example.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.stereotype.Component;
@Entity

public class Produit implements Serializable {

	@Id
	@GeneratedValue
	private Long id;
	private String designation ;
	
 @NotNull
 
	private double prix;
	@Lob
	private byte[] photo;
	
	
	public byte[] getPhoto() {
		return photo;
	}
	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public double getPrix() {
		return prix;
	}
	public void setPrix(double prix) {
		this.prix = prix;
	}
	public Produit() {
		super();
	}
	public Produit( String designation, double prix) {
		super();
	
		this.designation = designation;
		this.prix = prix;
	}

}
