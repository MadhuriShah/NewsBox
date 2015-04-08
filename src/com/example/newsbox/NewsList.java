package com.example.newsbox;


import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class NewsList extends Activity {
	private TextView tv;
	private String url="http://timesofindia.indiatimes.com/feeds/newsdefaultfeeds.cms?feedtype=sjson";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_news_list);
		String category = getIntent().getStringExtra("category");//Listview item selected in previous activity
		 tv=(TextView)findViewById(R.id.sample);
		 tv.setText(category);//Listview selected item
		 Service sh = new Service();
		 String jsonStr = sh.newsCall(url);
		}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.news_list, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
