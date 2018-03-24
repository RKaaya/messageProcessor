package main.java.util;

import java.util.ArrayList;
import java.util.List;

import main.java.model.BaseMessage;
import main.java.model.MessageTrade;
import main.java.model.MessageModifier;

import java.util.logging.Level;
import java.util.logging.Logger;

public class MessageCentral {
	private final static Logger LOGGER = Logger.getLogger(MessageCentral.class.getName());

	private List<BaseMessage> archive;
	private int msgCounter;
	private ReportCentral reportCentral;
	
	public MessageCentral(){
		archive = new ArrayList<BaseMessage>();
		msgCounter = 0;
		reportCentral = new ReportCentral();
	}
	
	public void messageIn(String msg){
		BaseMessage baseMessage = MessageProcessor.process(msg);
		archive.add(baseMessage);
		msgCounter++;
		if(baseMessage instanceof MessageTrade){
			MessageTrade trade = (MessageTrade)baseMessage;
			reportCentral.addTrade(trade);
		} else if(baseMessage instanceof MessageModifier){
			MessageModifier messageModifier = (MessageModifier)baseMessage;
			reportCentral.addModifier(messageModifier);
		}
		if(msgCounter%10 == 0){
			LOGGER.log(Level.INFO, reportCentral.getSalesReport());
		}
		if(msgCounter%50 == 0){
			LOGGER.log(Level.INFO, "EEZZ: "+msgCounter);
			LOGGER.log(Level.INFO, reportCentral.getAdjustReport());
		}
		
	}
}
