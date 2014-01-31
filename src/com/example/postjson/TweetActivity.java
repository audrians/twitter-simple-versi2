package com.example.postjson;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.widget.TextView;

public class TweetActivity extends Activity {

	private TextView text_tweet,
						text_user,
						text_time;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list);
		
		text_user = (TextView) findViewById(R.id.textView3);
		text_time = (TextView) findViewById(R.id.textView2);
		text_tweet = (TextView) findViewById(R.id.textView1);
		
		Intent i = getIntent();
		String tweet = i.getExtras().getString("tweet");
		text_tweet.setText(tweet);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.list, menu);
		return true;
	}

}
