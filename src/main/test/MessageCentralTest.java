package main.test;

import org.junit.Test;

import main.java.messageCentral.MessageCentral;

public class MessageCentralTest {

	@Test(expected = Exception.class)
	public void msgCentralStopAt50() throws Exception {
		MessageCentral mc = new MessageCentral();
		for(int i = 0; i<51; i++){
			mc.messageIn("apple at 10p");
		}
	}

}
