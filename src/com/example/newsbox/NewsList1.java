package com.example.newsbox;
import java.util.ArrayList;
import java.util.HashMap;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.app.ListActivity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;


public class NewsList1 extends ListActivity {
	private TextView tv;
	private ListView mylistView2;
	private String url="http://timesofindia.indiatimes.com/feeds/newsdefaultfeeds.cms?feedtype=sjson";
	private static final String newsArray = "NewsItem";
	private static final String headline= "HeadLine";
	private static final String date = "DateLine";
	JSONArray news;
	ArrayList<HashMap<String, String>> catNews;
		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_news_list);
			catNews = new ArrayList<HashMap<String, String>>();
			 String UserId = getIntent().getStringExtra("UserId");
			tv=(TextView)findViewById(R.id.textView2);
			tv.setText(UserId);
			 mylistView2=getListView();		
			 new GetNews().execute();	 
		}
		
	private class GetNews extends AsyncTask<Void, Void, Void> {
			@Override
	protected Void doInBackground(Void... arg0) {
		Service sh = new Service();
		String jsonStr = sh.newsCall(url);
		Log.d("Response", ": " + jsonStr);
		if (jsonStr != null) {
			try {
					JSONObject jsonObj = new JSONObject(jsonStr);
						// Getting JSON Array node
						news = jsonObj.getJSONArray(newsArray);
						// looping through All news
						for (int i = 0; i < news.length(); i++) {
							JSONObject c = news.getJSONObject(i);
							String date2 = c.getString(date);
							String headline2= c.getString(headline);
							// hashmap for singlen news
							Log.d("hash", "map1");
							HashMap<String, String> singleNews = new HashMap<String, String>();
							Log.d("hash", "map");
							singleNews.put(headline, headline2);
							Log.d("hash", "map2");
							singleNews.put(date, date2);
							catNews.add(singleNews);
							Log.d("hash", "map3");
				}
				} 
			catch (JSONException e) {
						e.printStackTrace();
				}
				}
			else {
					Log.e("webservice", "Couldn't get any data from the url");
				}

				return null;
			}
	protected void onPostExecute(Void result) {
		super.onPostExecute(result);
		// populating listview with json data
		ListAdapter adapter = new SimpleAdapter(NewsList1.this, catNews,
		R.layout.listitem, new String[] {headline, date}, new int[] { R.id.headlineTextView,R.id.dateTextView });
		setListAdapter(adapter);
				
			}

		}

	}


		
