package com.example.jsonparseing;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends Activity {
ListView lv;
ArrayList<String> as=new ArrayList<String>();	
@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	lv=(ListView)findViewById(R.id.listView1);
	new newadapter().execute();
	
	}

	


class newadapter extends AsyncTask<String, String, String>{
final String TAG="newadapter.java";
String url="http://api.androidhive.info/contacts/";
	JSONArray array=null;
	
	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		super.onPreExecute();
      ProgressDialog  pDialog = new ProgressDialog(MainActivity.this);
        pDialog.setMessage("Please wait...");
        pDialog.setCancelable(true);
        pDialog.show();
	}
	@Override
	protected String doInBackground(String... params) 
	{
		// TODO Auto-generated method stub
	
		try 
		{
			JSONParser jp=new JSONParser();
			JSONObject jo=jp.getJsonfromUrl(url);
			array=jo.getJSONArray("contacts");
			for (int i = 0; i < array.length(); i++) 
			{
				JSONObject c=array.getJSONObject(i);
				String book=c.getString("name");
				 Log.e(TAG, "bookname: " + book);
				as.add(book);
				
			}
		} 
		catch (Exception e) 
		{
			// TODO: handle exception
		}
		return null;
	}
	//@SuppressLint("InlinedApi")
	@Override
	protected void onProgressUpdate(String... values) {
		// TODO Auto-generated method stub
		super.onProgressUpdate(values);
	
	}
	@Override
	protected void onPostExecute(String result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
		ArrayAdapter<String> adp=new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_list_item_1, as);
		lv.setAdapter(adp);
	}
}}