package main.java.util;

import main.java.enums.Operation;
import main.java.model.BaseMessage;
import main.java.model.MessageTrade;
import main.java.model.MessageModifier;

public class MessageProcessor {
	static private String NOT_NUMBERS = "\\D+";
	
	/**
	   * This method to process messages
	   * Can process 3 type of message:
	   * {{item}} at {{price}}
	   * {{operation}} {{price}} {{item}}s
	   * {{quantity}} sales of {{item}}s at {{price}} each 
	   * @param msg  This is the message to process
	   * @return BaseMessage This returns a message.
	   */
	public static BaseMessage process(String msg) throws Exception{
		BaseMessage trade = null;
		//Split string to words
		String[] parts = msg.split(" ");
		
		if(parts.length == 3){
			if(parts[1].equals("at")){
				//template: {{item}} at {{price}}
				
				//Extract numbers from word
				String nums = parts[2].replaceAll(NOT_NUMBERS,"");
				trade = new MessageTrade(parts[0], 1, Integer.parseInt(nums));
			} else {
				//template: {{operation}} {{price}} {{item}}s
				
				String operation = parts[0].toUpperCase();
				//Extract numbers from word
				String nums = parts[1].replaceAll(NOT_NUMBERS,"");
				//Remove last character
				String product = parts[2].substring(0, parts[2].length() - 1);
				trade = new MessageModifier(product, Operation.valueOf(operation), Integer.parseInt(nums));
			}
		} else if(parts.length == 7) {
			//template: {{quantity}} sales of {{item}}s at {{price}} each 
			
			//Extract numbers from word
			String nums = parts[5].replaceAll(NOT_NUMBERS,"");
			//Remove last character
			String product = parts[3].substring(0, parts[3].length() - 1);
			trade = new MessageTrade(product, Integer.parseInt(parts[0]), Integer.parseInt(nums));
		} else {
			throw new Exception();
		}
		return trade;
	}
}
