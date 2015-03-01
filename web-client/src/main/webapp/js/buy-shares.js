function buyShares(div){
	getShares();
}

function executeBuy(){
	var stock = $("#stock").val();
	var quantity = $("#quantity").val();
	
	var username = $.cookie('username');
	
	var order = {
			"amount" : quantity,
			"type" : "BUY",
			"username" : username,
			"stock" : {"symbol" : stock}
		}
	var orderString = JSON.stringify(order);
	
	var data = {
			serviceid:'com.sharegame.services.stock.BuyStockService',
			className:'com.sharegame.model.order.Order',
			payload: orderString
		}
	
	makePostCall('microservice',data,
			function(result){
				dialog.dialog( "close" );
			},function(){
				alert('Service call failed');
			});
}

function displayDialog(stocks){
	var obj = JSON.parse(stocks.result);
	
	var html = "<select name='stock' id='stock' class='text ui-widget-content ui-corner-all'>";
	for(var index = 0;index < obj.length;index++){
		var value = obj[index];
		var stockName = value.stockName;
		var symbol = value.symbol;
		html += "<option value='"+symbol+"'>"+stockName+"</option>";
	}
	html += "</select>";
	
	var dropdownStock = $('#stocksDropdown');
	dropdownStock.html(html);
	
	var dialogDiv = $('#dialog-form');
	
		var dialog = dialogDiv.dialog({
	    autoOpen: true,
	    height: 380,
	    width: 450,
	    modal: true,
	
	    buttons: {
	      "Buy Shares": executeBuy,
	      Cancel: function() {
	      	dialog.dialog( "close" );
	      }
	    },
	
	    close: function() {

	    }
  });
}

function getShares(){
	var market = "{symbol: ISEQ }";
	
	var data = {
			serviceid:'com.sharegame.services.stock.FetchStocksService',
			className:'com.sharegame.model.market.Market',
			payload:market
		}
		makePostCall('microservice',data,
				displayDialog,
		function(blah,blah2){
			alert('Failed to load stocks');
		});
}