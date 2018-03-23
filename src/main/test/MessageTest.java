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
    	BaseMessage baseTrade = MessageProcessor.process(msg);

    	assertTrue(baseTrade instanceof MessageTrade);
    	if(baseTrade instanceof MessageTrade){
    		MessageTrade mt = (MessageTrade)baseTrade;
    		assertEquals(mt.getProduct(), "apple");
    		assertEquals(mt.getSale().getQuantity(), 1);
    		assertEquals(mt.getSale().getPrice(), 10);
    	}
    }
    
    @Test
    public void testMessage2() {
    	String msg = "20 sales of apples at 10p each";
    	BaseMessage baseTrade = MessageProcessor.process(msg);
    	
    	assertTrue(baseTrade instanceof MessageTrade);
    	if(baseTrade instanceof MessageTrade){
    		MessageTrade mt = (MessageTrade)baseTrade;
    		assertEquals(mt.getProduct(), "apple");
    		assertEquals(mt.getSale().getQuantity(), 20);
    		assertEquals(mt.getSale().getPrice(), 10);
    	}
    }
    
    @Test
    public void testMessage3Add() {
    	String msg = "Add 20p apples";
    	BaseMessage baseTrade = MessageProcessor.process(msg);

    	assertTrue(baseTrade instanceof MessageModifier);
    	if(baseTrade instanceof MessageModifier){
    		MessageModifier mt = (MessageModifier)baseTrade;
    		assertEquals(mt.getProduct(), "apple");
    		assertEquals(mt.getAmount(), 20);
    		assertEquals(mt.getOperation(), Operation.ADD);
    	}
    }
    
    @Test
    public void testMessage3Subtract() {
    	String msg = "Subtract 20p apples";
    	BaseMessage baseTrade = MessageProcessor.process(msg);

    	assertTrue(baseTrade instanceof MessageModifier);
    	if(baseTrade instanceof MessageModifier){
    		MessageModifier mt = (MessageModifier)baseTrade;
    		assertEquals(mt.getProduct(), "apple");
    		assertEquals(mt.getAmount(), 20);
    		assertEquals(mt.getOperation(), Operation.SUBTRACT);
    	}
    }
    
    @Test
    public void testMessage3Multiply() {
    	String msg = "Multiply 20p apples";
    	BaseMessage baseTrade = MessageProcessor.process(msg);

    	assertTrue(baseTrade instanceof MessageModifier);
    	if(baseTrade instanceof MessageModifier){
    		MessageModifier mt = (MessageModifier)baseTrade;
    		assertEquals(mt.getProduct(), "apple");
    		assertEquals(mt.getAmount(), 20);
    		assertEquals(mt.getOperation(), Operation.MULTIPLY);
    	}
    }
}
