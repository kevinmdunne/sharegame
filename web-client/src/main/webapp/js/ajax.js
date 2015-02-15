function makePostCall(post_url,data,success_func,failure_func){
	$.ajax(
		{
			url: post_url, 
			success: success_func,
			type: "POST",
			data: data,
			error: failure_func
		}
	);	
}