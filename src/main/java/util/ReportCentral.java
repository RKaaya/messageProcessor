package main.java.util;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import main.java.enums.Operation;
import main.java.model.MessageModifier;
import main.java.model.MessageTrade;
import main.java.model.Sale;

public class ReportCentral {
	private HashMap<String, Sale> salesMap;
	private HashMap<String, List<MessageModifier>> modifierMap;
	
	public ReportCentral(){
		salesMap = new HashMap<String, Sale>();
		modifierMap = new HashMap<String, List<MessageModifier>>();
	}
	
	/**
	   * This method add a sale.
	   * @param trade This is the trade message to add
	   */
	public void addSale(MessageTrade trade){
		salesMap.putIfAbsent(trade.getProduct(), new Sale());
		Sale sale = salesMap.get(trade.getProduct());
		sale.addSale(trade.getQuantity(), trade.getPrice());
	}

	/**
	   * This method add a sale modifier.
	   * @param modifier This is the modifier message to add
	   */
	public void addModifier(MessageModifier modifier){
		modifierMap.putIfAbsent(modifier.getProduct(), new LinkedList<>());
		List<MessageModifier> list = modifierMap.get(modifier.getProduct());
		list.add(modifier);
		applyModifier(modifier);
	}
	
	/**
	   * This method apply a sale modifier.
	   * @param modifier This is the modifier to apply to all sales
	   */
	private void applyModifier(MessageModifier modifier) {
		Sale sale = salesMap.get(modifier.getProduct());
		if(sale != null){
		double price = sale.getPrice();
		switch(modifier.getOperation()){
			case ADD: {
				price += sale.getQuantity() * modifier.getPrice();
				break;
			}
			case SUBTRACT: {
				price -= sale.getQuantity() * modifier.getPrice();
				break;
			}
			case MULTIPLY: {
				price = price * modifier.getPrice();
				break;
			}
		}
		
		sale.setPrice(price);
		}
	}
	
	/**
	   * This method make a report from sales.
	   * @return String This returns the sales report.
	   */
	public String getSalesReport(){
		StringBuilder sb = new StringBuilder();
		sb.append("Sales report:").append("\n");
		for(Map.Entry<String, Sale> item : salesMap.entrySet()){
			sb.append(item.getValue().getQuantity()).append(" ");
			sb.append(item.getKey()).append(": ");
			sb.append(item.getValue().getPrice()/100).append("p");
			sb.append("\n");
		}
		return sb.toString();
	}
	
	/**
	   * This method make a report from modifiers.
	   * @return String This returns the modifier report.
	   */
	public String getModifierReport(){
		StringBuilder sb = new StringBuilder();
		sb.append("Modifier report:").append("\n");
		for(Map.Entry<String, List<MessageModifier>> item : modifierMap.entrySet()){
			sb.append(item.getKey()).append(": \n");
			for(MessageModifier mm : item.getValue()){
				if(mm.getOperation().equals(Operation.MULTIPLY)){
					sb.append("\t").append(mm.getOperation()).append(": ").append(mm.getPrice()).append("\n");
				} else {
					sb.append("\t").append(mm.getOperation()).append(": ").append(mm.getPrice()/100).append("p\n");
				}
			}
			sb.append("\n");
		}
		return sb.toString();
	}
	
}
