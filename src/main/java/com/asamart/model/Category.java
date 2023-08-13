package com.asamart.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "category")
public class Category {

//id, categoryname, description, createddate, createdBy image

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String categoryname;
	private String description;
	private Date createddate;
	private String createdBy;

	@Lob
	@Column(name = "image", columnDefinition = "LONGBLOB")
	private byte[] image;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCategoryname() {
		return categoryname;
	}

	public void setCategoryname(String categoryname) {
		this.categoryname = categoryname;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreateddate() {
		return createddate;
	}

	public void setCreateddate(Date createddate) {
		this.createddate = createddate;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public byte[] getImage() {
		return image;
	}

}
