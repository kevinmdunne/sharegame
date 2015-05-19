package com.sharegame.services.user;

import java.util.Comparator;
import java.util.List;

import com.sharegame.model.portfolio.Holding;
import com.sharegame.model.portfolio.Portfolio;
import com.sharegame.model.stock.Stock;
import com.sharegame.model.user.User;

public class UserComparison implements Comparator<User>{

	@Override
	public int compare(User o1, User o2) {
		int netWorth1 = calculateNetWorth(o1.getPortfolio());
		int netWorth2 = calculateNetWorth(o2.getPortfolio());
		return netWorth2 - netWorth1;
	}

	private static int calculateNetWorth(Portfolio portfolio){
		int result = portfolio.getCashBalance();
		List<Holding> holdings = portfolio.getHoldings();
		
		for(Holding holding : holdings){
			Stock stock = holding.getStock();
			int amount = holding.getAmount();
			result = result + (stock.getPrice() * amount);
		}
		
		return result;
	}

}
