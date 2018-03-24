package main.java.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	
	public void addTrade(MessageTrade trade){
		salesMap.putIfAbsent(trade.getProduct(), new Sale(0,0));
		Sale sale = salesMap.get(trade.getProduct());
		int quantity = sale.getQuantity();
		int price = sale.getPrice();
		quantity += trade.getSale().getQuantity();
		price += trade.getSale().getQuantity() * trade.getSale().getPrice();
		sale.setQuantity(quantity);
		sale.setPrice(price);
	}
	
	public void addModifier(MessageModifier modifier){
		modifierMap.putIfAbsent(modifier.getProduct(), new ArrayList<>());
		List<MessageModifier> list = modifierMap.get(modifier.getProduct());
		list.add(modifier);
		applyModifier(modifier);
	}
	
	private void applyModifier(MessageModifier modifier) {
		Sale sale = salesMap.get(modifier.getProduct());
		if(sale != null){
		int price = sale.getPrice();
		switch(modifier.getOperation()){
			case ADD: {
				price += sale.getQuantity() * modifier.getAmount();
				break;
			}
			case SUBTRACT: {
				price -= sale.getQuantity() * modifier.getAmount();
				break;
			}
			case MULTIPLY: {
				price = price * modifier.getAmount();
				break;
			}
		}
		
		sale.setPrice(price);
		}
	}
	
	public String getSalesReport(){
		StringBuilder sb = new StringBuilder();
		for(Map.Entry<String, Sale> item : salesMap.entrySet()){
			sb.append(item.getValue().getQuantity());
			sb.append(" ");
			sb.append(item.getKey());
			sb.append(": ");
			sb.append(item.getValue().getPrice()).append("p");
			sb.append("\n");
		}
		return sb.toString();
	}
	
	public String getAdjustReport(){
		StringBuilder sb = new StringBuilder();
		for(Map.Entry<String, List<MessageModifier>> item : modifierMap.entrySet()){
			sb.append(item.getKey()).append(": \n");
			for(MessageModifier mm : item.getValue()){
				sb.append("\t").append(mm.getOperation()).append(": ").append(mm.getAmount()).append("\n");	
			}
			sb.append("\n");
		}
		return sb.toString();
	}
	
	
}
