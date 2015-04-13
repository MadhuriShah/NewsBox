package com.example.newsbox;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;


public class MainActivity extends Activity {
	private ListView mylistview1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mylistview1=(ListView)findViewById(R.id.list1);  
        mylistview1.setOnItemClickListener(new OnItemClickListener() {
        
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				 Intent i=new Intent(getApplicationContext(),NewsList.class);// switch from mainactivity to newsList activity
				 String category=(String) parent.getItemAtPosition(position);//selected Item at particular position
		          i.putExtra("category",category);  //category variable containing the selected value and category to be used in next activity
		            startActivity(i);//switching to NewsList activity
				
			}                
        	
		});
        
        
    }
}


  