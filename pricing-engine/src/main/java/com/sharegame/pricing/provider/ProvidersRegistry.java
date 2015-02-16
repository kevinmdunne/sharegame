package com.sharegame.pricing.provider;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ProvidersRegistry {

	private static ProvidersRegistry INSTANCE;
	
	public static ProvidersRegistry getInstance(){
		if(INSTANCE == null){
			INSTANCE = new ProvidersRegistry();
		}
		return INSTANCE;
	}
	
	private List<Class<?extends PriceProvider>> providers;
	
	private ProvidersRegistry(){
		providers = new ArrayList<Class<? extends PriceProvider>>();
		this.loadProviders();
	}
	
	private void loadProviders(){
		try{
			InputStream stream = getClass().getResourceAsStream("/ProvidersRegistry.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document document = dBuilder.parse(stream);
			NodeList nodeList = document.getElementsByTagName("classname");
			
			for (int index = 0; index < nodeList.getLength(); index++) {
				Node node = nodeList.item(index);
				String className = node.getTextContent();
				Class<? extends PriceProvider> clazz = (Class<? extends PriceProvider>)Class.forName(className);
				this.providers.add(clazz);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public List<Class<? extends PriceProvider>> getProivders(){
		return this.providers;
	}
}
