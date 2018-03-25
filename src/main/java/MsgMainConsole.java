package main.java;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import main.java.messageCentral.MessageCentral;

public class MsgMainConsole {
	private final static Logger LOGGER = Logger.getLogger(MsgMainConsole.class.getName());
	
	public static void main(String[] args) {
			Scanner sc = new Scanner(System.in);
	        MessageCentral mc = new MessageCentral();
	        while (sc.hasNextLine()) {
	         	try{
	         		String msg = sc.nextLine();
	         		if(msg.isEmpty()){
	         			break;
	         		}
	           		mc.messageIn(msg);
	           	} catch(Exception e){
	           		LOGGER.log(Level.INFO, e.getMessage());
	           	}
	        }
	        sc.close();
	}
}
