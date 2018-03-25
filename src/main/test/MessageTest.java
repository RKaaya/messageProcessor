package main.test;

import org.junit.Test;

import main.java.enums.Operation;
import main.java.model.BaseMessage;
import main.java.model.MessageModifier;
import main.java.model.MessageTrade;
import main.java.util.MessageProcessor;

import static org.junit.Assert.*;

public class MessageTest {

	@Test
	public void testMessage1() {
		String msg = "apple at 10p";
		try {
			BaseMessage baseTrade = MessageProcessor.process(msg);

			assertTrue(baseTrade instanceof MessageTrade);
			if (baseTrade instanceof MessageTrade) {
				MessageTrade mt = (MessageTrade) baseTrade;
				assertEquals(mt.getProduct(), "apple");
				assertEquals(mt.getQuantity(), 1);
				assertEquals(mt.getPrice(), 10, 0);
			}
		} catch (Exception e) {

		}
	}

	@Test
	public void testMessage2() {
		String msg = "20 sales of apples at 10p each";
		try {
			BaseMessage baseTrade = MessageProcessor.process(msg);

			assertTrue(baseTrade instanceof MessageTrade);
			if (baseTrade instanceof MessageTrade) {
				MessageTrade mt = (MessageTrade) baseTrade;
				assertEquals(mt.getProduct(), "apple");
				assertEquals(mt.getQuantity(), 20);
				assertEquals(mt.getPrice(), 10, 0);
			}
		} catch (Exception e) {

		}
	}

	@Test
	public void testMessage3Add() {
		String msg = "Add 20p apples";
		try {
			BaseMessage baseTrade = MessageProcessor.process(msg);

			assertTrue(baseTrade instanceof MessageModifier);
			if (baseTrade instanceof MessageModifier) {
				MessageModifier mt = (MessageModifier) baseTrade;
				assertEquals(mt.getProduct(), "apple");
				assertEquals(mt.getPrice(), 20, 0);
				assertEquals(mt.getOperation(), Operation.ADD);
			}
		} catch (Exception e) {

		}
	}

	@Test
	public void testMessage3Subtract() {
		String msg = "Subtract 20p apples";
		try {
			BaseMessage baseTrade = MessageProcessor.process(msg);

			assertTrue(baseTrade instanceof MessageModifier);
			if (baseTrade instanceof MessageModifier) {
				MessageModifier mt = (MessageModifier) baseTrade;
				assertEquals(mt.getProduct(), "apple");
				assertEquals(mt.getPrice(), 20, 0);
				assertEquals(mt.getOperation(), Operation.SUBTRACT);
			}
		} catch (Exception e) {

		}
	}

	@Test
	public void testMessage3Multiply() {
		String msg = "Multiply 20p apples";
		try {
			BaseMessage baseTrade = MessageProcessor.process(msg);

			assertTrue(baseTrade instanceof MessageModifier);
			if (baseTrade instanceof MessageModifier) {
				MessageModifier mt = (MessageModifier) baseTrade;
				assertEquals(mt.getProduct(), "apple");
				assertEquals(mt.getPrice(), 20, 0);
				assertEquals(mt.getOperation(), Operation.MULTIPLY);
			}
		} catch (Exception e) {

		}
	}
	
	@Test(expected = Exception.class)
	public void testMessageWrongOperation() throws Exception {
		String msg = "Divide 20p apples";
		MessageProcessor.process(msg);
	}
	
	@Test(expected = Exception.class)
	public void testMessageWithoutPrice() throws Exception {
		String msg = "apple at p";
		MessageProcessor.process(msg);
	}
	
	@Test(expected = Exception.class)
	public void testMessageInvalidMessage() throws Exception {
		String msg = "at 10p";
		MessageProcessor.process(msg);
	}
	
	@Test(expected = Exception.class)
	public void testMessageEmpty() throws Exception {
		String msg = "";
		MessageProcessor.process(msg);
	}
	
	@Test(expected = Exception.class)
	public void testMessageNull() throws Exception {
		MessageProcessor.process(null);
	}

}
