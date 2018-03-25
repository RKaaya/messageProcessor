package main.test;

import org.junit.Test;

import main.java.model.Sale;

import static org.junit.Assert.*;

public class SaleTest {

	@Test
	public void testSale() {
		Sale sale = new Sale();
		
		assertEquals(sale.getPrice(), 0, 0);
		assertEquals(sale.getQuantity(), 0);
		
		sale.addSale(5, 0);
		
		assertEquals(sale.getPrice(), 0, 0);
		assertEquals(sale.getQuantity(), 5);
		
		sale.addSale(0, 5);
		
		assertEquals(sale.getPrice(), 5, 0);
		assertEquals(sale.getQuantity(), 5);
		
		sale.addSale(5, 5);
		
		assertEquals(sale.getPrice(), 10, 0);
		assertEquals(sale.getQuantity(), 10);
	}

}
