package main.java.model;

public class MessageTrade extends BaseMessage {
	private Sale sale;

	public MessageTrade(String product, int quantity, int price){
		setProduct(product);
		this.sale = new Sale(quantity, price);
	}

	public Sale getSale() {
		return sale;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("product: ").append(getProduct()).append("\n");
		sb.append("quantity: ").append(sale.getQuantity()).append("\n");
		sb.append("amount: ").append(sale.getPrice()).append("\n");
		return sb.toString();
	}
}
