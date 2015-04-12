package com.example.newsbox;
import java.util.ArrayList;
import java.util.HashMap;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.app.ListActivity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;


public class NewsList1 extends ListActivity {
	//private TextView tv;
	private ListView mylistView2;
	private String url="http://timesofindia.indiatimes.com/feeds/newsdefaultfeeds.cms?feedtype=sjson";
	private static final String newsArray = "NewsItem";
	private static final String headline= "HeadLine";
	//private static final String date = "DateLine";
	private static final String story = "Story";
	private static final String byline = "ByLine";
	JSONArray news;
	ArrayList<HashMap<String, String>> catNews;
		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_news_list);
			catNews = new ArrayList<HashMap<String, String>>();
			 //String UserId = getIntent().getStringExtra("UserId");
			//tv=(TextView)findViewById(R.id.textView2);
			//tv.setText(UserId);
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
							String story2 = c.getString(story);
							String headline2= c.getString(headline);
							String byline2=c.getString(byline);
							// hashmap for singlen news
							Log.d("hash", "map1");
							HashMap<String, String> singleNews = new HashMap<String, String>();
							Log.d("hash", "map");
							singleNews.put(headline, headline2);
							Log.d("hash", "map2");
							singleNews.put(story, story2);
							singleNews.put(byline, byline2);
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
		R.layout.listitem, new String[] {headline,byline, story}, new int[] { R.id.headlineTextView,R.id.dateTextView,R.id.storyTextView });
		setListAdapter(adapter);
				
			}

		}

	}


		
