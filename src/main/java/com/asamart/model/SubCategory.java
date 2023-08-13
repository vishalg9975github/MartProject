package com.asamart.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
<<<<<<< HEAD
=======
import javax.persistence.ManyToOne;
>>>>>>> f2b2c802a1868aba27a93564715c3c1e7d1943c1
import javax.persistence.Table;

@Entity
@Table(name = "subcategory")
public class SubCategory {
//subcategoryid, subcategoryname, categoryid, description, createddate, createdby

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int subcategoryid;
	private String subcategoryname;
	private int categoryid;
	private String description;
	private Date createddate;
	private String createdBy;
	
	
	

	public int getSubcategoryid() {
		return subcategoryid;
	}

	public void setSubcategoryid(int subcategoryid) {
		this.subcategoryid = subcategoryid;
	}

	public String getSubcategoryname() {
		return subcategoryname;
	}

	public void setSubcategoryname(String subcategoryname) {
		this.subcategoryname = subcategoryname;
	}

	public int getCategoryid() {
		return categoryid;
	}

	public void setCategoryid(int categoryid) {
		this.categoryid = categoryid;
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

}
