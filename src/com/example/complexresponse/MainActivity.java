package com.example.complexresponse;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.net.ParseException;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class MainActivity extends Activity {
	
ArrayList<Worldpopulation> list;
	
	PopulationAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		list = new ArrayList<Worldpopulation>();
		new JSONAsyncTask().execute("http://www.androidbegin.com/tutorial/jsonparsetutorial.txt");
		
		ListView lv=(ListView)findViewById(R.id.listView1);
		adapter = new PopulationAdapter(getApplicationContext(), R.layout.single_row, list);
		
		lv.setAdapter(adapter);
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,long arg3) {	
				
				Toast.makeText(getApplicationContext(), list.get(arg2).getCountry(), Toast.LENGTH_LONG).show();				
			}
			
		});
	}
	
	class JSONAsyncTask extends AsyncTask<String, Void, Boolean>
	{
		ProgressDialog dialog;

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			dialog = new ProgressDialog(MainActivity.this);
			dialog.setMessage("Loading, please wait");
			dialog.setTitle("Connecting server");
			dialog.show();
			dialog.setCancelable(false);
		}
		
		
		@Override
		protected Boolean doInBackground(String... urls) {
			try{
				
				//------------------>>
				HttpGet httppost = new HttpGet(urls[0]);
				HttpClient httpclient = new DefaultHttpClient();
				HttpResponse response = httpclient.execute(httppost);

				// StatusLine stat = response.getStatusLine();
				int status = response.getStatusLine().getStatusCode();

				if (status == 200) {
					HttpEntity entity = response.getEntity();
					String data = EntityUtils.toString(entity);
					
					JSONObject jsono = new JSONObject(data);
					JSONArray jarray = jsono.getJSONArray("worldpopulation");
					
					for (int i = 0; i < jarray.length(); i++) {
						JSONObject object = jarray.getJSONObject(i);
					
						Worldpopulation pop = new Worldpopulation();
						
						pop.setRank(object.getString("rank"));
						pop.setCountry(object.getString("country"));
						pop.setPopulation(object.getString("population"));
						pop.setImage(object.getString("flag"));
						
						list.add(pop);
			}
			return true;
}
				//------------------>>
				
			} catch (ParseException e1) {
				e1.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (JSONException e) {
				e.printStackTrace();
			}
			return false;	
		}
		
		@Override
		protected void onPostExecute(Boolean result) {
			
			super.onPostExecute(result);
			dialog.cancel();
			adapter.notifyDataSetChanged();
			if(result == false)
				Toast.makeText(getApplicationContext(), "Unable to fetch data from server", Toast.LENGTH_LONG).show();
			
		}
		
	}

}
