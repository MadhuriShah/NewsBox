package com.example.newsbox;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.util.Log;

public class Service {
	static String response = null;

	public String newsCall(String url) {
		return this.newsCall(url, null);
	}

	public String newsCall(String url, List<NameValuePair> parameter) {
		try {
			DefaultHttpClient httpClient = new DefaultHttpClient();
			HttpEntity httpEntity = null;
			HttpResponse httpResponse = null;
			if (parameter != null) {
				String myString = URLEncodedUtils.format(parameter, "utf-8"); // returns string suitable for url with parameters
				url += "?" + myString;
			}
			HttpGet httpGet = new HttpGet(url); // request to url
			httpResponse = httpClient.execute(httpGet); // executing http request
			httpEntity = httpResponse.getEntity(); // storing http response in entity
			response = EntityUtils.toString(httpEntity); // returns httpEntity content in string
			
		} 
	   catch (IOException e) {
			Log.d("error", "error"); //if error
		}

		return response;

	}
}
