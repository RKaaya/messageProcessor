package main.java.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import main.java.enums.Operation;
import main.java.model.BaseMessage;
import main.java.model.MessageTrade;
import main.java.model.MessageModifier;

public class MessageProcessor {
	static final private String NOT_NUMBERS = "\\D+";
	static final private Pattern MESSAGE_PATTERN_1 = Pattern.compile("(?i)(\\S+)\\s*at\\s*(\\S+)p");
	static final private Pattern MESSAGE_PATTERN_2 = Pattern.compile("(?i)^(\\d+) sales of (\\S+)s\\s*at\\s*(\\S+)p each");
	static final private Pattern MESSAGE_PATTERN_3 = Pattern.compile("(?i)^(add|subtract|multiply).(\\S+)p.(\\S+)s");
	
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
		
		Matcher matcher=MESSAGE_PATTERN_1.matcher(msg);
		if(matcher.matches())
		{
			trade = parseMessage1(matcher.group(1),matcher.group(2));
		}
		
		if(trade == null){
			matcher=MESSAGE_PATTERN_2.matcher(msg);
			if(matcher.matches())
			{
				trade = parseMessage2(matcher.group(1),matcher.group(2),matcher.group(3));
			}
		}
		
		if(trade == null){
			matcher=MESSAGE_PATTERN_3.matcher(msg);
			if(matcher.matches())
			{
				trade = parseMessage3(matcher.group(1),matcher.group(2),matcher.group(3));
			}
		}

		if(trade == null){
			throw new Exception();	
		}
		
		return trade;
	}
	
	private static BaseMessage parseMessage1(final String pProduct, final String pPrice){
		Integer price = Integer.parseInt(pPrice.replaceAll(NOT_NUMBERS, ""));
		BaseMessage trade = new MessageTrade(pProduct, 1, price);
		return trade;
	}
	
	private static BaseMessage parseMessage2(final String pQuantity, final String pProduct, final String pPrice){
		Integer price = Integer.parseInt(pPrice.replaceAll(NOT_NUMBERS, ""));
		Integer quantity = Integer.parseInt(pQuantity);
		BaseMessage trade = new MessageTrade(pProduct, quantity, price);
		return trade;
	}
	
	private static BaseMessage parseMessage3(final String pOperation, final String pPrice, final String pProduct){
		Operation operation = Operation.valueOf(pOperation.toUpperCase());
		Integer price = Integer.parseInt(pPrice.replaceAll(NOT_NUMBERS, ""));
		BaseMessage trade = new MessageModifier(pProduct, operation, price);
		return trade;
	}
}
