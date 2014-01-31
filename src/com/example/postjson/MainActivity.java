package com.example.postjson;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	private ListView listView;
	private Context mContext;

	ArrayList<Tweet> tweetArray = new ArrayList<Tweet>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mContext = this;

		listView = (ListView) findViewById(R.id.listView1);


		/*buttonHasil.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub

			}
		}); */
		new DapatAsyncTask().execute();
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View view, int position,long id) {

				Toast.makeText(getBaseContext(), ""+position, Toast.LENGTH_SHORT).show();
				//selected item
				Intent in = new Intent(getApplicationContext(), TweetActivity.class);
				in.putExtra("tweet", tweetArray.get(position).getText());
				//in.putExtra("id", position);
				startActivity(in);
			}
		});
	}

	private class DapatAsyncTask extends AsyncTask<String, Void, String>{
		@Override
		protected String doInBackground(String... params) {

			String xxHasil = "";
			try {
				URL url = new URL("http://d.7langit.com/skilltest/timeline_7langit.json");
				HttpURLConnection connect = (HttpURLConnection) url.openConnection();
				//------------------------------------------------------------------------------------
				BufferedInputStream bis = new BufferedInputStream(connect.getInputStream());
				byte[] bb = new byte[1024];
				ByteArrayOutputStream baos = new ByteArrayOutputStream();

				int bytesRead =-1;

				while((bytesRead = bis.read(bb))>-1) {
					baos.write(bb, 0, bytesRead);
				}
				xxHasil = new String(baos.toByteArray());
				//------------------------------------------------------------------------------------
			} catch (MalformedURLException e) {
				Log.e("DOBG", e.getMessage());
			} catch (IOException e) {
				Log.e("DOBG", e.getMessage());
			}
			return xxHasil;
		}
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();

		}
		protected void onPostExecute(String tampungHasil)
		{
			//textViewHasil.setText("");

			try {
				JSONArray ja = new JSONArray(tampungHasil);
				for(int i=0; i<ja.length(); i++) {
					JSONObject jO = ja.getJSONObject(i);
					String time = jO.getString("created_at");
					String text = jO.getString("text");

					JSONObject jO2 = jO.getJSONObject("user");
					String id_user = jO2.getString("id");
					Tweet tweetPerson = new Tweet(time, id_user, text);

					tweetArray.add(tweetPerson);

				}
				Log.e("ISI array", "isi="+tweetArray.get(0).getText());
				TweetAdapter adapter = new TweetAdapter(MainActivity.this, android.R.layout.simple_list_item_1, tweetArray);
				listView.setAdapter(adapter);

			} catch (JSONException e) {
				Log.e("JSON", e.getMessage());
			}
		}
	}

	private class TweetAdapter extends ArrayAdapter<Tweet> {

		ArrayList<Tweet> tweetBaru; 

		public class ViewHolder {
			public TextView text_user;
			public TextView text_text;
			public TextView text_created;
		}


		public TweetAdapter(Context mContext, int textViewResourceId, ArrayList<Tweet> tweetBaru) {
			super(mContext, textViewResourceId, tweetBaru);

			this.tweetBaru = (ArrayList<Tweet>) tweetBaru;
		}
		@Override
		public boolean hasStableIds() {
			return true;
		}

		public View getView(int position, View convertView, ViewGroup parent) {
			View rowView = convertView;
			LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			rowView = inflater.inflate(R.layout.activity_list, parent, false);

			ViewHolder viewHolder = new ViewHolder();
			viewHolder.text_user = (TextView) rowView.findViewById(R.id.textView1);
			viewHolder.text_text = (TextView) rowView.findViewById(R.id.textView2);
			viewHolder.text_created = (TextView) rowView.findViewById(R.id.textView3);
			viewHolder.text_user.setText(tweetBaru.get(position).getUser());
			viewHolder.text_text.setText(tweetBaru.get(position).getText());
			viewHolder.text_created.setText(tweetBaru.get(position).getCreated_at());
			rowView.setTag(viewHolder);

			return rowView;
		}
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}