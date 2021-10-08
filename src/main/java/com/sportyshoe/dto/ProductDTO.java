package com.sportyshoe.dto;

public class ProductDTO {


	private long id;
	private String name;
	private int categoryId;
	private double price;
	private String description;
	private String imageName;
	public ProductDTO(long id, String name, int categoryId, double price, String description, String imageName) {
		super();
		this.id = id;
		this.name = name;
		this.categoryId = categoryId;
		this.price = price;
		this.description = description;
		this.imageName = imageName;
	}
	public ProductDTO(String name, int categoryId, double price, String description, String imageName) {
		super();
		this.name = name;
		this.categoryId = categoryId;
		this.price = price;
		this.description = description;
		this.imageName = imageName;
	}
	public ProductDTO() {
		super();
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getImageName() {
		return imageName;
	}
	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
	
	
	
	

}
