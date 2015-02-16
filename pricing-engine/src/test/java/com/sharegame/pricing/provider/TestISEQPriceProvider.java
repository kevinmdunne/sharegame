package com.sharegame.pricing.provider;

import junit.framework.Assert;

import org.junit.Test;

public class TestISEQPriceProvider {

	@Test
	public void testGetMarketSymbol(){
		ISEQPriceProvider provider = new ISEQPriceProvider();
		String marketSymbol = provider.getMarketSymbol();
		
		Assert.assertEquals("ISEQ", marketSymbol);
	}
	
//	@Test
//	public void testGetStocks(){
//		ISEQPriceProvider provider = new ISEQPriceProvider();
//		provider.getStockPrices();
//	}
}
