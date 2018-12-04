package com.example.to_do;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class ListAdapter extends BaseAdapter{
	Context con;
	JSONArray ja;

	public ListAdapter(Context con, JSONArray ja) {
		super();
		
		this.con = con;
		this.ja = ja;
		Log.e("constructor", "consss");
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return ja.length();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int arg0, View arg1, ViewGroup arg2) {
		// TODO Auto-generated method stub
		
		arg1=LayoutInflater.from(con).inflate(R.layout.list_details, null);
		TextView textheading=(TextView) arg1.findViewById(R.id.heading);
		TextView textmng=(TextView) arg1.findViewById(R.id.submng);
		TextView textdt=(TextView) arg1.findViewById(R.id.dateevnt);
		try {
			Log.e("kiituuunel",""+ja.length());
			JSONObject jo=ja.getJSONObject(arg0);
			String headingw=jo.getString("word");
			String meaningw=jo.getString("wrdmng");
			String dateevent=jo.getString("dateevt");
			Log.e("hhhhhhhhhhhhhhhhhhmmmmmmmmmmmmmmm", headingw+" "+meaningw);
			textheading.setText(headingw);
			textmng.setText(meaningw);
			textdt.setText(dateevent);
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return arg1;
	}

}
