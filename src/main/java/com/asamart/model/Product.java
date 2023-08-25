package com.asamart.model;

import java.util.ArrayList;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "product")
public class Product {

	// productid,
	// productname,subcategoryid,productdescription,brand,tags,productcode,featured-boolean,

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int productid;
	private String productname;
	private String productdescription;
	private String brand;
	private String tags;
	private String productcode;
	private boolean featured;

	private boolean isDeleted;
//
//	@OneToMany(mappedBy = "productid")
//	private List<ProductImage> getAllImages;

	@OneToMany(mappedBy = "productid", cascade = CascadeType.ALL)
	private List<ProductPrice> productPrice;

	public List<ProductPrice> getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(List<ProductPrice> productPrice) {
		this.productPrice = productPrice;
	}

//	public List<ProductImage> getGetAllImages() {
//		return getAllImages;
//	}
//
//	public void setGetAllImages(List<ProductImage> getAllImages) {
//		this.getAllImages = getAllImages;
//	}

	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ProductImage> images = new ArrayList<>();

	public int getProductid() {
		return productid;
	}

	public void setProductid(int productid) {
		this.productid = productid;
	}

	public String getProductname() {
		return productname;
	}

	public void setProductname(String productname) {
		this.productname = productname;
	}

	public String getProductdescription() {
		return productdescription;
	}

	public void setProductdescription(String productdescription) {
		this.productdescription = productdescription;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public String getProductcode() {
		return productcode;
	}

	public void setProductcode(String productcode) {
		this.productcode = productcode;
	}

	public boolean isFeatured() {
		return featured;
	}

	
	public void setFeatured(boolean featured) {
		this.featured = featured;
	}

	public boolean isDeleted() {
		return isDeleted;
	}
	
	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
	
	@Override
	public String toString() {
		return "Product [productid=" + productid + ", productname=" + productname + ", productdescription="
				+ productdescription + ", brand=" + brand + ", tags=" + tags + ", productcode=" + productcode
				+ ", featured=" + featured + ", isDeleted=" + isDeleted + ", getAllImages=" + ", productPrice=" + productPrice + ", images=" + images + "]";
	}

	

}
