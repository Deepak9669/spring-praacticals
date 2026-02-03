package com.rays.autowire.byconstructor;

public class Inventory {
	
	private int stock;
	
	
	public Inventory() {
	}


	public int getStock() {
		return stock;
	}


	public void setStock(int stock) {
		this.stock = stock;
	}


	public Inventory(int stock) {
		super();
		this.stock = stock;
	}
	
	public int sold(int qty) {
		stock -= qty; // stock = stock - qty;
		return stock;
	}

}
