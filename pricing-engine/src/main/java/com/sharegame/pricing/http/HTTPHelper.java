package com.sharegame.pricing.http;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class HTTPHelper {

	private static String userAgent = "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.1; Trident/4.0; SLCC2; .NET CLR 2.0.50727; .NET CLR 3.5.30729; .NET CLR 3.0.30729; Media Center PC 6.0; .NET4.0C; InfoPath.3)";
	private static String acceptString = "image/jpeg, application/x-ms-application, image/gif, application/xaml+xml, image/pjpeg, application/x-ms-xbap, application/x-shockwave-flash, application/vnd.ms-excel, application/vnd.ms-powerpoint, application/msword, */*";
	
	public static String get(String url) {
		String data = "";
		try {
			CloseableHttpClient httpclient = HttpClients.createDefault();
			HttpGet httpGet = new HttpGet(url);
			
			httpGet.setHeader("Content-Type","application/x-www-form-urlencoded");
			httpGet.setHeader("User-Agent", userAgent);
			httpGet.setHeader("Accept-Language", "en-IE");
			httpGet.setHeader("Accept-Encoding", "gzip, deflate");
			httpGet.setHeader("Accept", acceptString);

			CloseableHttpResponse response = httpclient.execute(httpGet);

			try {
			    HttpEntity entity = response.getEntity();
			    data = EntityUtils.toString(entity);
			    EntityUtils.consume(entity);
			} finally {
			    response.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return data;
	}

}
