package main.java.model;

public class Sale {
	private int quantity;
	private double price;
	
	public Sale(){
		
	}
	
	public Sale(int quantity, int price){
		this.quantity = quantity;
		this.price = price;
	}
	
	/**
	   * This method is used to add quantity and price.
	   * @param quantity This is the quantity to add
	   * @param price This is the price to add
	   */
	public void addSale(int quantity, double price){
		this.quantity += quantity;
		this.price += price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("quantity: ").append(quantity).append("\n");
		sb.append("amount: ").append(getPrice()).append("\n");
		return sb.toString();
	}
}
