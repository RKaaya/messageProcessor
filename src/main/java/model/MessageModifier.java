package main.java.model;

import main.java.enums.Operation;

public class MessageModifier extends BaseMessage {
	private Operation operation;
	
	public MessageModifier(String product, Operation operation, int price){
		this.setProduct(product);
		this.operation = operation;
		setPrice(price);
	}
	
	public Operation getOperation() {
		return operation;
	}

	public void setOperation(Operation operation) {
		this.operation = operation;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("product: ").append(getProduct()).append("\n");
		sb.append("price: ").append(getPrice()).append("\n");
		sb.append("operation: ").append(operation).append("\n");
		return sb.toString();
	}
}
