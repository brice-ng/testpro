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
@Table(name = "licence1")
public class Licence1 {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name ="firstname" )
	private String firstname;

	@Column(name ="lastname" )
	private String lastname;
	
	
	
	@Column(name ="fileType" )
	private String fileType;
	
	@Transient
	private MultipartFile file;
	
	public Licence1(){
		
	}
	
	
	public Licence1( String firstname, String lastname, String fileType) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
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
