package main.java;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import main.java.messageCentral.MessageCentral;

public class MsgMainFromFile {
	private final static Logger LOGGER = Logger.getLogger(MsgMainFromFile.class.getName());
	
	public static void main(String[] args) {
			MessageCentral mc = new MessageCentral();
	        File file = new File(".\\test\\words.txt");

	        try {
	            Scanner sc = new Scanner(file);

	            while (sc.hasNextLine()) {
	            	try{
	            		mc.messageIn(sc.nextLine());
	            	} catch(Exception e){
	            		LOGGER.log(Level.INFO, e.getMessage());
	            	}
	            }
	            sc.close();
	        } 
	        catch (FileNotFoundException e) {
	            e.printStackTrace();
	        }
	}
}
