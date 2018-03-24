package main.java.util;

import main.java.enums.Operation;
import main.java.model.BaseMessage;
import main.java.model.MessageTrade;
import main.java.model.MessageModifier;

public class MessageProcessor {
	static private String NOT_NUMBERS = "\\D+";
	public static BaseMessage process(String msg){
		BaseMessage trade;
		String[] parts = msg.split(" ");
		if(parts.length == 3){
			if(parts[1].equals("at")){
				String nums = parts[2].replaceAll(NOT_NUMBERS,"");
				trade = new MessageTrade(parts[0], 1, Integer.parseInt(nums));
			} else {
				String operation = parts[0].toUpperCase();
				String nums = parts[1].replaceAll(NOT_NUMBERS,"");
				String product = parts[2].substring(0, parts[2].length() - 1);
				trade = new MessageModifier(product, Operation.valueOf(operation), Integer.parseInt(nums));
			}
		} else {
			String nums = parts[5].replaceAll(NOT_NUMBERS,"");
			String product = parts[3].substring(0, parts[3].length() - 1);
			trade = new MessageTrade(product, Integer.parseInt(parts[0]), Integer.parseInt(nums));
		}
		return trade;
	}
}
