package com.asamart.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "productimage")
public class ProductImage {
//imageid, imagepath,productid,defaultimage-flag

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int imageId;
	private String imagePath;
	private boolean defaultImage;
	private String imageHash;
	// @Column(unique = true)
	private String imageName;
	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;

	public int getImageId() {
		return imageId;
	}

	public void setImageId(int imageId) {
		this.imageId = imageId;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public boolean isDefaultImage() {
		return defaultImage;
	}

	public void setDefaultImage(boolean defaultImage) {
		this.defaultImage = defaultImage;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public String getImageHash() {
		return imageHash;
	}

	public void setImageHash(String imageHash) {
		this.imageHash = imageHash;
	}
}
