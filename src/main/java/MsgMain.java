package main.java;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import main.java.util.MessageCentral;

public class MsgMain {

	public static void main(String[] args) {
	        // Initiate the sale object
//	        Sale sale = new Sale();
			Scanner s = new Scanner(System.in);
//	        while(true) {
//				System.out.println(s.next());
//			}
	        MessageCentral mc = new MessageCentral();
	        File file = new File("d:\\!D3\\words.txt");

	        try {
	            Scanner sc = new Scanner(file);

	            while (sc.hasNextLine()) {
	            	mc.messageIn(sc.nextLine());
	            }
	            sc.close();
	        } 
	        catch (FileNotFoundException e) {
	            e.printStackTrace();
	        }
	        
//	        s.close();
	}
}
