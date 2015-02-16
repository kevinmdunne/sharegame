package com.sharegame.pricing.provider;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.sharegame.model.stock.Stock;
import com.sharegame.pricing.http.HTTPHelper;



public class ISEQPriceProvider implements PriceProvider{

	private static final String url = "http://digitallook.sharewatch.com/irish_performance";
	
	public String getMarketSymbol(){
		return "ISEQ";
	}
	
	@Override
	public String getMarketName() {
		return "Irish Stock Exchange";
	}
	
	@Override
	public List<Stock> getStockPrices() {
		List<Stock> result = new ArrayList<Stock>();
		String rawHTML = HTTPHelper.get(url);
		
		Document doc = Jsoup.parse(rawHTML);

		Element bodyElement = doc.body();
		Element tableElement = bodyElement.getElementById("performanceTableTable");
		Elements elements = tableElement.child(0).children();
		
		Iterator<Element> iterator = elements.iterator();
		
		//dump the first row as it is just headers
		iterator.next();
		
		while(iterator.hasNext()){
			Element rowElement = iterator.next();
			Elements tableData = rowElement.children();
			Element nameElement = tableData.get(0);
			Element priceElement = tableData.get(1);

			String priceString = priceElement.text();
			priceString = priceString.trim();
			priceString = priceString.substring(2).trim();
			float d = Float.parseFloat(priceString);
			d = d * 100;
			int price = (int)d;
			Stock stock = new Stock();
			stock.setStockName(nameElement.text());
			stock.setSymbol(nameElement.text());
			stock.setPrice(price);
			
			result.add(stock);
		}
		
		return result;
	}

}
