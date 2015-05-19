function getLeadershipBoard(leaderboardDiv){
	var data = {
			serviceid:'com.sharegame.services.user.LeaderBoardService',
			className:'java.lang.String',
			payload:''
		}
		makePostCall('microservice',data,
		function(result){
			var leaderboard = JSON.parse(result.result);
			drawBoard(leaderboard,leaderboardDiv);
		},function(blah,blah2){
			alert('Service call failed');
		});
}

function drawBoard(users,leaderboardDiv){
	var html = "<table border='1' width='90%'>";

	for(var i = 0;i < users.length;i++){
		var user = users[i];
		var name = user.firstname + " " + user.surname;
		var position = i + 1;
		
		html += "<tr><td>"+position+"</td><td>"+name+"</td></tr>"
	}
	
	html += "</table>";
	leaderboardDiv.html(html);
}