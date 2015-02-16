package com.sharegame.pricing.provider;

import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

public class TestProvidersRegistry {

	@Test
	public void testGetProivders(){
		ProvidersRegistry registry = ProvidersRegistry.getInstance();
		List<Class<? extends PriceProvider>> providers = registry.getProivders();
		Assert.assertNotNull(providers);
		Assert.assertEquals(1,providers.size());
	}
}
