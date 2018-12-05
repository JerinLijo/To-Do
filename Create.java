package com.example.to_do;

import java.util.Calendar;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Create extends Activity{
	
	EditText wrd,meaning;
	SQLiteDatabase db;
	Database1 data=new Database1(this);;
	TextView create;
	String words,meanings,datainsert="",yrmnday;
	TextView selectDate;
	int mYear,mMonth,mDay;
	String ymd;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.create);
		
		wrd=(EditText) findViewById(R.id.word);
		meaning=(EditText) findViewById(R.id.meang);
		
		create=(TextView) findViewById(R.id.create);
		selectDate=(TextView) findViewById(R.id.evdate);
		getActionBar().setHomeButtonEnabled(true);
		getActionBar().setIcon(R.drawable.ic_indicator);
		
		 final Calendar c = Calendar.getInstance();
         mYear = c.get(Calendar.YEAR);
         mMonth = c.get(Calendar.MONTH);
         mDay = c.get(Calendar.DAY_OF_MONTH);
		selectDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatePickerDialog  mdiDialog =new DatePickerDialog(Create.this,new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        //Toast.makeText(getApplicationContext(),year+ " "+monthOfYear+" "+dayOfMonth,Toast.LENGTH_LONG).show();
                         yrmnday=dayOfMonth+"-"+monthOfYear+"-"+year;
                        selectDate.setText(yrmnday);
                    }
                }, mYear, mMonth, mDay);
                mdiDialog.show();
                
            }

        });
		
		create.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub

				words=wrd.getText().toString();
				meanings=meaning.getText().toString();
				ymd=selectDate.getText().toString();
				//Toast.makeText(Create.this, words+" "+meanings, 1000).show();
				
				 
				 datainsert="insert into "+Database1.tb_wrds+"("+Database1.wrdcol1+","+Database1.wrdcol2+","+Database1.wrdcol3+")values('"+words+"','"+meanings+"','"+ymd+"');";
				 db=data.getWritableDatabase();
				 db.execSQL(datainsert);
				//Toast.makeText(Create.this, "successfully inserted", 1000).show();
				 Intent home=new Intent(Create.this, Home.class);
				 startActivity(home);
				
			}
		});
		
		
		
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
	        case android.R.id.home:
	            //finish();
	            onBackPressed();
	            break;
	    }
	    return true;
	}
	
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
	}

}
