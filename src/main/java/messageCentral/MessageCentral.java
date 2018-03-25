package main.java.messageCentral;

import java.util.ArrayList;
import java.util.List;

import main.java.model.BaseMessage;
import main.java.model.MessageTrade;
import main.java.util.MessageProcessor;
import main.java.util.ReportCentral;
import main.java.model.MessageModifier;

import java.util.logging.Level;
import java.util.logging.Logger;

public class MessageCentral {
	private final static Logger LOGGER = Logger.getLogger(MessageCentral.class.getName());

	private List<BaseMessage> archive;
	private int msgCounter;
	private ReportCentral reportCentral;
	private boolean active;
	
	public MessageCentral(){
		archive = new ArrayList<BaseMessage>();
		reportCentral = new ReportCentral();
		active = true;
	}
	
	public void messageIn(String msg) throws Exception{
		if(!active) {
			throw new Exception("Message central is not accepting messages.");
		}
		
		//Increase the message counter
		msgCounter++;
		
		try{
			//Try to process the incoming message
			BaseMessage baseMessage = MessageProcessor.process(msg);
			archive.add(baseMessage);
			
			if(baseMessage instanceof MessageTrade){
				MessageTrade trade = (MessageTrade)baseMessage;
				reportCentral.addSale(trade);
			} else if(baseMessage instanceof MessageModifier){
				MessageModifier messageModifier = (MessageModifier)baseMessage;
				reportCentral.addModifier(messageModifier);
			}
		} catch(Exception e){
			LOGGER.log(Level.INFO, "Invalid message: ".concat(msg));
		}
		
		//Make sales report after 10 message
		if(msgCounter%10 == 0){
			LOGGER.log(Level.INFO, reportCentral.getSalesReport());
		}
		//Make modifier report after 50 message and disable message processing
		if(msgCounter%50 == 0){
			LOGGER.log(Level.INFO, reportCentral.getModifierReport());
			active = false;
		}
	}
}
