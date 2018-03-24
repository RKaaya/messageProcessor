package main.java.model;

import main.java.enums.Operation;

public class MessageModifier extends BaseMessage {
	private Operation operation;
	private int amount;
	
	public MessageModifier(String product, Operation operation, int amount){
		this.setProduct(product);
		this.operation = operation;
		this.amount = amount;
	}
	
	public Operation getOperation() {
		return operation;
	}

	public void setOperation(Operation operation) {
		this.operation = operation;
	}

	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("product: ").append(getProduct()).append("\n");
		sb.append("amount: ").append(amount).append("\n");
		sb.append("operation: ").append(operation).append("\n");
		return sb.toString();
	}
}
