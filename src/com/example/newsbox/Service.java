package com.example.newsbox;
import java.io.IOException;
import java.util.List;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

public class Service {
	static String response;
	
	public String newsCall(String url) {
		return this.newsCall(url);
	}

	public String newsCall(String url,
			List<NameValuePair> params) {
		try {		
			DefaultHttpClient httpClient = new DefaultHttpClient();
			HttpEntity httpEntity = null;
			HttpResponse httpResponse = null;
				if (params != null) {
					String paramString = URLEncodedUtils
							.format(params, "utf-8");
					url += "?" + paramString;
				}
				HttpGet httpGet = new HttpGet(url);
				httpResponse = httpClient.execute(httpGet);
				httpEntity = httpResponse.getEntity();
				response = EntityUtils.toString(httpEntity);
			}catch (IOException e) {
				
			}
			return response;
	}
}
