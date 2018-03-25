package main.java.model;

public class MessageTrade extends BaseMessage {
	private int quantity;

	public MessageTrade(String product, int quantity, int price){
		setProduct(product);
		setPrice(price);
		this.quantity = quantity;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("product: ").append(getProduct()).append("\n");
		sb.append("quantity: ").append(quantity).append("\n");
		sb.append("amount: ").append(getPrice()).append("\n");
		return sb.toString();
	}
}
