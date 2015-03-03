var buySharesDialog;

function buyShares(div){
	getShares();
}

function executeBuy(dialog){
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
				buySharesDialog.dialog( "close" );
				var div = $("<div title='Order executed'><p>Shares successfully purchased.</p><div>");
				
				var confirmDialog = div.dialog({
					autoOpen: true,
					modal: true,
					buttons: {
					      "Dismiss": function(){
					    	  confirmDialog.dialog("close");
					    	  var portfolioDiv = $('#portfolio_div');
					    	  createPortfolioGrid(username,portfolioDiv)
					    	  }
					    }
					,
					close: function() {
						var portfolioDiv = $('#portfolio_div');
						createPortfolioGrid(username,portfolioDiv)
					}
				}
				);
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
	dialogDiv.removeClass('not-to-be-shown');
	
	buySharesDialog = dialogDiv.dialog({
	    autoOpen: true,
	    height: 380,
	    width: 450,
	    modal: true,
	
	    buttons: {
	      "Buy Shares": executeBuy,
	      Cancel: function() {
	    	  buySharesDialog.dialog( "close" );
	      }
	    },
	
	    close: function() {}
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