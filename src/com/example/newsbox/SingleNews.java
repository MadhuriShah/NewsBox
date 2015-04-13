package com.example.newsbox;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class SingleNews extends Activity {
	private TextView tv,tv2;
	private static final String headlines = "HeadLine";
	private static final String Story = "Story";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_single_news);
		Intent in = getIntent();
		tv=(TextView)findViewById(R.id.headlineText);
		tv.setText(in.getStringExtra(headlines)); //headlines from newslist activity
		tv2=(TextView)findViewById(R.id.storyText);
		tv2.setText(in.getStringExtra(Story));//story from newslist activity
		tv2.setMovementMethod(new ScrollingMovementMethod());// makes textview scrollable
		
	}

}
