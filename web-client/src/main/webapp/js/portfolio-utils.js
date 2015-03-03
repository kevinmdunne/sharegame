function createPortfolioGrid(username,containerDiv){
	var data = {
			serviceid:'com.sharegame.services.portfolio.FetchPortfolioService',
			className:'java.lang.String',
			payload:username
		}
		makePostCall('microservice',data,
		function(result){
			var portfolio = JSON.parse(result.result);
			_drawGrid(portfolio,containerDiv);
		},function(blah,blah2){
			alert('Service call failed');
		});
}

function _drawGrid(portfolio,containerDiv){
	console.log(portfolio);
	var html = "<table border='1' width='90%'>";
	var holdings = portfolio.holdings;
	for(var i = 0;i < holdings.length;i++){
		var holding = holdings[i];
		var stock = holding.stock;
		var amount = holding.amount;
		
		html += "<tr><td>"+stock.stockName+"</td><td>"+amount+"</td></tr>"
	}
	
	html += "</table>";
	containerDiv.html(html);
}