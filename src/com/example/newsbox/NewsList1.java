package com.example.newsbox;
import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ListActivity;
import android.app.ProgressDialog;
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
	private ProgressDialog dialog;
	private String url="http://timesofindia.indiatimes.com/feeds/newsdefaultfeeds.cms?feedtype=sjson";
	private static final String newsArray = "NewsItem";
	private static final String headline= "HeadLine";
	//private static final String date = "DateLine";
	private static final String story = "Story";
	private static final String caption = "Caption";
	JSONArray news;
	ArrayList<HashMap<String, String>> catNews;
		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_news_list);
			catNews = new ArrayList<HashMap<String, String>>();
			 String category = getIntent().getStringExtra("category");
			 if(category.equalsIgnoreCase("Tech")){
				 url="http://timesofindia.indiatimes.com/feeds/newsfeed/31926882.cms?feedtype=sjson";
			 }
			 else if (category.equalsIgnoreCase("Business")) {
				 url="http://timesofindia.indiatimes.com/feeds/newsfeeddf/-2128680634.cms?feedtype=sjson";
			 }
			 else if (category.equalsIgnoreCase("Sports")) {
				 url="http://timesofindia.indiatimes.com/feeds/newsfeed/4719148.cms?feedtype=sjson";
			 }
			 else if (category.equalsIgnoreCase("Science")) {
				 url="http://timesofindia.indiatimes.com/feeds/newsfeeddf/-2128672765.cms?feedtype=sjson";
			 }
			 else if (category.equalsIgnoreCase("World")) {
				 url="http://timesofindia.indiatimes.com/feeds/newsfeed/296589292.cms?feedtype=sjson";
			 }
			 else if (category.equalsIgnoreCase("Education")) {
				 url="http://timesofindia.indiatimes.com/feeds/newsfeed/913168846.cms?feedtype=sjson";
			 }
			 else if (category.equalsIgnoreCase("Life and Style")) {
				 url="http://timesofindia.indiatimes.com/feeds/newsfeed/2886714.cms?feedtype=sjson";
			 }
			 else if (category.equalsIgnoreCase("beauty")) {
				 url="http://timesofindia.indiatimes.com/feeds/newsfeeddf/2886724.cms?feedtype=sjson";
			 }
			//tv=(TextView)findViewById(R.id.textView2);
			//tv.setText(UserId);
			 mylistView2=getListView();	
			 mylistView2.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					// TODO Auto-generated method stub
					Intent in = new Intent(getApplicationContext(),SingleNews.class);
					//in.putExtra("hello", "hello");
					String headline1 = ((TextView) view.findViewById(R.id.headlineTextView)).getText().toString();
					String story1 = ((TextView) view.findViewById(R.id.storyTextView)).getText().toString();	
					in.putExtra(headline, headline1);
					in.putExtra(story, story1);
					startActivity(in);
					
				}
				 
			 });
			 new GetNews().execute();	 
					
	
		}
		
	private class GetNews extends AsyncTask<Void, Void, Void> {
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			//displaying progress dialog
			dialog = new ProgressDialog(NewsList1.this);
			dialog.setMessage("Please wait...");
			dialog.setCancelable(false);
			dialog.show();

		}
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
							String byline2=c.getString(caption);
							Log.d("hash", "map1");
							HashMap<String, String> singleNews = new HashMap<String, String>();
							Log.d("hash", "map");
							singleNews.put(headline, headline2);
							Log.d("hash", "map2");
							singleNews.put(story, story2);
							singleNews.put(caption, byline2);
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
		if (dialog.isShowing())
			dialog.dismiss();
		// populating listview with json data
		ListAdapter adapter = new SimpleAdapter(NewsList1.this, catNews,
		R.layout.listitem, new String[] {headline,caption, story}, 
		new int[] { R.id.headlineTextView,R.id.dateTextView,R.id.storyTextView }); // uses listitem textview and populates data in it
		setListAdapter(adapter);
				
			}

		}

	}


		
