var sellSharesDialog;
var sellSharesDialogDiv;

function sellShares(){
	getShares(displaySellDialog);
}

function displaySellDialog(stocks){
	var obj = JSON.parse(stocks.result);
	
	var html = "<select name='sellStock' id='sellStock' class='text ui-widget-content ui-corner-all'>";
	for(var index = 0;index < obj.length;index++){
		var value = obj[index];
		var stockName = value.stockName;
		var symbol = value.symbol;
		html += "<option value='"+symbol+"'>"+stockName+"</option>";
	}
	html += "</select>";
	
	sellSharesDialogDiv = getDialogDiv();
	var dropdownStock = sellSharesDialogDiv.find('#stocksDropdown');
	
	dropdownStock.html(html);
	
	sellSharesDialog = sellSharesDialogDiv.dialog({
	    autoOpen: true,
	    height: 380,
	    width: 450,
	    modal: true,
	
	    buttons: {
	      "Sell Shares": executeSell,
	      Cancel: function() {
	    	  sellSharesDialog.dialog( "close" );
	    	  sellSharesDialogDiv.empty();
	      }
	    },
	
	    close: function() {}
  });
}

function executeSell(){
	var stock = sellSharesDialogDiv.find('#sellStock').val();
	var quantity = sellSharesDialogDiv.find('#sellQuantity').val();
	
	var username = $.cookie('username');
	
	var order = {
			"amount" : quantity,
			"type" : "SELL",
			"username" : username,
			"stock" : {"symbol" : stock}
		}
	var orderString = JSON.stringify(order);
	
	var data = {
			serviceid:'com.sharegame.services.stock.SellStockService',
			className:'com.sharegame.model.order.Order',
			payload: orderString
		}
	
	makePostCall('microservice',data,
			function(result){
				sellSharesDialog.dialog( "close" );
				var div = $("<div title='Order executed'><p>Shares successfully sold.</p><div>");
				
				var confirmDialog = div.dialog({
					autoOpen: true,
					modal: true,
					buttons: {
					      "Dismiss": function(){
					    	  confirmDialog.dialog("close");
					    	  var portfolioDiv = $('#portfolio_div');
					    	  createPortfolioGrid(username,portfolioDiv);
					    	  sellSharesDialogDiv.empty();
					    	  }
					    }
					,
					close: function() {
						var portfolioDiv = $('#portfolio_div');
						createPortfolioGrid(username,portfolioDiv);
						sellSharesDialogDiv.empty();
					}
				});
			},function(error){
				sellSharesDialog.dialog( "close" );
				var message = error.responseText;
				var div = $("<div title='An Error Occurred'><p>"+message+"</p><div>");
				var confirmDialog = div.dialog({
					autoOpen: true,
					modal: true,
					buttons: {
					      "Dismiss": function(){
					    	  confirmDialog.dialog("close");
					    	  var portfolioDiv = $('#portfolio_div');
					    	  createPortfolioGrid(username,portfolioDiv);
					    	  sellSharesDialogDiv.empty();
					    	  }
					    }
					,
					close: function() {
						var portfolioDiv = $('#portfolio_div');
						createPortfolioGrid(username,portfolioDiv);
						sellSharesDialogDiv.empty();
					}
				});
			});
}

function getDialogDiv(){
	var div = $(
	'<div id="sell-dialog-form" title="Sell Shares">'
	+ '<p>Select a stock and enter an amount.</p>'
	+ '<form>'
	+ '<fieldset>'
	+ '<label for="name">Stock</label>'
	+ '<div id="stocksDropdown"></div>'
	+ '<label for="email">Quantity</label>'
	+ '<input type="text" name="sellQuantity" id="sellQuantity" value="1" class="text ui-widget-content ui-corner-all">'
	+ '</fieldset>'
	+ '</form>'
	+ '</div>');
	return div;
}