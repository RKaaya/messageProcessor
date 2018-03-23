package main.java.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import main.java.model.BaseMessage;
import main.java.model.MessageTrade;
import main.java.model.MessageModifier;
import main.java.model.Sale;

import java.util.logging.Level;
import java.util.logging.Logger;

public class MessageCentral {
	private final static Logger LOGGER = Logger.getLogger(MessageCentral.class.getName());

	private HashMap<String, List<Sale>> hmap;
	private List<MessageModifier> adjusterList;
	private int msgCounter;
	private ReportCentral reportCentral;
	
	public MessageCentral(){
		hmap = new HashMap<String, List<Sale>>();
		adjusterList = new ArrayList<>();
		msgCounter = 0;
		reportCentral = new ReportCentral();
	}
	
	public void messageIn(String msg){
		BaseMessage baseTrade = MessageProcessor.process(msg);
		msgCounter++;
		if(baseTrade instanceof MessageTrade){
			MessageTrade trade = (MessageTrade)baseTrade;
			hmap.putIfAbsent(trade.getProduct(), new ArrayList<>());
			List<Sale> list = hmap.get(trade.getProduct());
			list.add(trade.getSale());
			reportCentral.addTrade(trade);
		} else if(baseTrade instanceof MessageModifier){
			MessageModifier messageModifier = (MessageModifier)baseTrade;
			adjusterList.add(messageModifier);
			reportCentral.addModifier(messageModifier);
		}
		if(msgCounter%10 == 0){
			LOGGER.log(Level.INFO, reportCentral.getReport());
		}
	}
}
