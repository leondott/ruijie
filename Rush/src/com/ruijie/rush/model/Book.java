package com.ruijie.rush.model;

public class Book {

	private String author = "";
	private float price = (float) 0.0;
	private int pages = 0;
	private String name = "";
	private int categoryId = 0;

	public Book(String author, String name, float price, int pages,
			int categoryId) {
		this.author = author;
		this.name = name;
		this.price = price;
		this.pages = pages;
		this.categoryId = categoryId;
	}
	
	public Book() {
		// TODO Auto-generated constructor stub
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public int getPages() {
		return pages;
	}

	public void setPages(int pages) {
		this.pages = pages;
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

}
