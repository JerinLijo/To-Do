package com.example.to_do;

import java.nio.channels.GatheringByteChannel;
import java.util.ArrayList;
import java.util.Calendar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.R.anim;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Update extends Activity {

	SQLiteDatabase db;
	Database1 data;
	Spinner updatewrd;
	Cursor cd;
	String getdata, getdata1;
	ArrayList<String> arwrd;
	String wrd,word,updatedatamng,upmng;
	JSONArray ja;
	JSONObject jo;
	EditText updatemng;
	TextView update;
	TextView evdateupd;
	int mYear,mMonth,mDay;
	String ymd,yrmnday,dateup;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.update);

		updatewrd = (Spinner) findViewById(R.id.wordup);
		updatemng = (EditText) findViewById(R.id.updatemng);
		update=(TextView) findViewById(R.id.update);
		evdateupd=(TextView) findViewById(R.id.evdateup);
		arwrd = new ArrayList<String>();
		data = new Database1(Update.this);
		
		final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);
        evdateupd.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               DatePickerDialog  mdiDialog =new DatePickerDialog(Update.this,new DatePickerDialog.OnDateSetListener() {
                   @Override
                   public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                      // Toast.makeText(getApplicationContext(),year+ " "+monthOfYear+" "+dayOfMonth,Toast.LENGTH_LONG).show();
                        yrmnday=dayOfMonth+"-"+monthOfYear+"-"+year;
                       //evdateupd.setText(yrmnday);
                   }
               }, mYear, mMonth, mDay);
               mdiDialog.show();
               
           }

       });

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
				//Toast.makeText(Update.this, "array" + arwrd, 1000).show();
				// arwrd.add(ja.getString(i));
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		// Toast.makeText(Update.this, "jaaaa"+""+ja, 1000).show();

		// Toast.makeText(Update.this, "" + arwrd, 1000).show();
		ArrayAdapter<String> a1 = new ArrayAdapter<String>(Update.this,
				android.R.layout.simple_spinner_item, arwrd);
		a1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		updatewrd.setAdapter(a1);

		updatewrd.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				 word = (String) arg0.getItemAtPosition(arg2);
				//Toast.makeText(Update.this, word, 1000).show();
				if (word.equals("select")) {
					updatemng.setText("");

				} else {
					getdata1 = "select * from " + Database1.tb_wrds + " where "
							+ Database1.wrdcol1 + "='" + word + "'";
					db = data.getReadableDatabase();
					cd = db.rawQuery(getdata1, null);
					if (cd.moveToNext()) {
						String meaning = cd.getString(cd
								.getColumnIndex(Database1.wrdcol2));
						String dateupdate = cd.getString(cd
								.getColumnIndex(Database1.wrdcol3));
						/*
						 * Toast.makeText(Update.this, "MEANING" + meaning,
						 * 1000) .show();
						 */
						updatemng.setText(meaning);
						evdateupd.setText(dateupdate);
					}
				}

			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub

			}
		});
		update.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				 upmng=updatemng.getText().toString();
				 dateup=evdateupd.getText().toString();
				 updatedatamng="update "+Database1.tb_wrds+" set "+Database1.wrdcol2+"='"+upmng+"'&&"+Database1.wrdcol3+"='"+dateup+"' where "+Database1.wrdcol1+"='"+word+"'";
				db=data.getWritableDatabase();
				db.execSQL(updatedatamng);
				//Toast.makeText(Update.this, "updated successfully", 1000).show();
				 Intent home=new Intent(Update.this, Home.class);
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
