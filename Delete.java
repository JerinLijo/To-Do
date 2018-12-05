package com.example.to_do;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

public class Delete extends Activity{
	
	SQLiteDatabase db;
	Database1 data;
	Cursor cd;
	String getdata, getdata1;
	ArrayList<String> arwrd;
	String wrd,word,worddel;
	JSONArray ja;
	JSONObject jo;
	TextView delete;
	
	
	Spinner delword;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.delete);
		
		delword=(Spinner) findViewById(R.id.worddel);
		delete=(TextView) findViewById(R.id.delete);
		
		arwrd = new ArrayList<String>();
		data = new Database1(Delete.this);

		getActionBar().setHomeButtonEnabled(true);
		getActionBar().setIcon(R.drawable.ic_indicator);

		arwrd.add("select");
		ja = new JSONArray();
		getdata = "select * from " + Database1.tb_wrds;
		db = data.getReadableDatabase();
		cd = db.rawQuery(getdata, null);
		while (cd.moveToNext()) {
			wrd = cd.getString(cd.getColumnIndex(Database1.wrdcol1));
			JSONObject jo = new JSONObject();
			try {
				jo.put("word", wrd);
				ja.put(jo);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		for (int i = 0; i <= ja.length(); i++) {
			try {
				JSONObject s = ja.getJSONObject(i);
				String arwd1 = s.getString("word");
				arwrd.add(arwd1);
				//Toast.makeText(Delete.this, "array" + arwrd, 1000).show();
				// arwrd.add(ja.getString(i));
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		// Toast.makeText(Update.this, "jaaaa"+""+ja, 1000).show();

		// Toast.makeText(Update.this, "" + arwrd, 1000).show();
		ArrayAdapter<String> a1 = new ArrayAdapter<String>(Delete.this,
				android.R.layout.simple_spinner_item, arwrd);
		a1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		delword.setAdapter(a1);

		delword.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				 word = (String) arg0.getItemAtPosition(arg2);
				//Toast.makeText(Update.this, word, 1000).show();
				

			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub

			}
		});
		
delete.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				 
				worddel="delete from "+Database1.tb_wrds+" where "+Database1.wrdcol1+"='"+word+"'";
				db=data.getWritableDatabase();
				db.execSQL(worddel);
				//Toast.makeText(Delete.this, "Deleted successfully", 1000).show();
				 Intent home=new Intent(Delete.this, Home.class);
				 startActivity(home);
			}
		});
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub

		switch (item.getItemId()) {
		case android.R.id.home:
			onBackPressed();
			break;
		}
		return super.onOptionsItemSelected(item);
	}

}
