package com.asamart.model;

import java.util.Date;
import java.util.List;
<<<<<<< HEAD
=======

import javax.persistence.CascadeType;
>>>>>>> f2b2c802a1868aba27a93564715c3c1e7d1943c1
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
<<<<<<< HEAD
=======
import javax.persistence.PrePersist;
>>>>>>> f2b2c802a1868aba27a93564715c3c1e7d1943c1
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "category")
public class Category {

//id, categoryname, description, createddate, createdBy image

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String categoryname;
	private String description;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date createddate;
	
	private String createdBy;
	@Lob
	private byte[] image;

	@OneToMany(mappedBy = "categoryid")
<<<<<<< HEAD
	private List<SubCategory> subCategory;

=======
    private List<SubCategory> subcategories;
	
	
>>>>>>> f2b2c802a1868aba27a93564715c3c1e7d1943c1
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
<<<<<<< HEAD

	public List<SubCategory> getSubCategory() {
		return subCategory;
	}

	public void setSubCategory(List<SubCategory> subCategory) {
		this.subCategory = subCategory;
	}

=======
	
	@PrePersist
	protected void onCreate() {
		createddate = new Date();
	}
>>>>>>> f2b2c802a1868aba27a93564715c3c1e7d1943c1
}
