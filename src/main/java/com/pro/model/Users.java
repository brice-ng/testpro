package com.pro.model;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

@Entity
@Table(name = "users")
public class Users {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(name ="firstname" )
	private String firstname;

	@Column(name ="lastname" )
	private String lastname;
	
	@Column(name ="email" )
	private String email;
	
	@Column(name ="phoneNumber" )
	private String phoneNumber;
	
	@Column(name ="fileType" )
	private String fileType;
	
	@Transient
	private MultipartFile file;
	
	public Users(){
		
	}
	
	
	public Users( String firstname, String lastname, String email, String phoneNumber, String fileType) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.fileType = fileType;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getFileType() {
		return fileType;
	}
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}


	public MultipartFile getFile() {
		return file;
	}


	public void setFile(MultipartFile file) {
		this.file = file;
	}

	
	
	
}
