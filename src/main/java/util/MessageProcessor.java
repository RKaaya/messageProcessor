package main.java.util;

import java.util.regex.Pattern;

import main.java.enums.Operation;
import main.java.model.BaseMessage;
import main.java.model.MessageTrade;
import main.java.model.MessageModifier;

public class MessageProcessor {
	static final private String NOT_NUMBERS = "\\D+";
	static final private String MESSAGE_PATTERN_1 = "(?i)(\\S+)\\s*at\\s*(\\S+)";
	static final private String MESSAGE_PATTERN_2 = "(?i)^(\\d). sales of (\\S+)\\s*at\\s*(\\S+) each";
	static final private String MESSAGE_PATTERN_3 = "(?i)^(add|subtract|multiply).((?:\\S+\\s+){1}\\S+)";
	
	/**
	   * This method to process messages
	   * Can process 3 type of message:
	   * {{item}} at {{price}}
	   * {{operation}} {{price}} {{item}}s
	   * {{quantity}} sales of {{item}}s at {{price}} each 
	   * @param msg  This is the message to process
	   * @return BaseMessage This returns a message.
	   */
	public static BaseMessage process(final String msg) throws Exception{
		BaseMessage trade = null;
		if(Pattern.matches(MESSAGE_PATTERN_2, msg)){
			trade = parseMessage2(msg);
		} else if(Pattern.matches(MESSAGE_PATTERN_1, msg)){
			trade = parseMessage1(msg);
		} else if(Pattern.matches(MESSAGE_PATTERN_3, msg)){
			trade = parseMessage3(msg);
		} else {
			throw new Exception();
		}
		return trade;
	}
	
	private static BaseMessage parseMessage1(final String msg){
		String[] parts = msg.split(" ");
		//Extract numbers from word
		String nums = parts[2].replaceAll(NOT_NUMBERS,"");
		BaseMessage trade = new MessageTrade(parts[0], 1, Integer.parseInt(nums));
		return trade;
	}
	
	private static BaseMessage parseMessage2(final String msg){
		String[] parts = msg.split(" ");
		//Extract numbers from word
		String nums = parts[5].replaceAll(NOT_NUMBERS,"");
		//Remove last character
		String product = parts[3].substring(0, parts[3].length() - 1);
		BaseMessage trade = new MessageTrade(product, Integer.parseInt(parts[0]), Integer.parseInt(nums));
		return trade;
	}
	
	private static BaseMessage parseMessage3(final String msg){
		String[] parts = msg.split(" ");
		String operation = parts[0].toUpperCase();
		//Extract numbers from word
		String nums = parts[1].replaceAll(NOT_NUMBERS,"");
		//Remove last character
		String product = parts[2].substring(0, parts[2].length() - 1);
		BaseMessage trade = new MessageModifier(product, Operation.valueOf(operation), Integer.parseInt(nums));
		return trade;
	}
}
