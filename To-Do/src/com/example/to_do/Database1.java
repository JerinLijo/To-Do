package com.example.to_do;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class Database1 extends SQLiteOpenHelper{
	
	
	public static final String DATABASE_NAME="dictionary.db";
	public static final String tb_name="Register";
	public static final String reg_id="ID";
	public static final String regcol1="name";
	public static final String regcol2="age";
	public static final String regcol3="address";
	public static final String regcol4="ph_num";
	public static final String regcol5="email_id";
	public static final String regcol6="password";
	public static final String tb_wrds="Words";
	public static final String wrd_id="ID";
	public static final String wrdcol1="word";
	public static final String wrdcol2="meaning";
	public static final String wrdcol3="dateevent";
	public static String datacreatewrd;

	public Database1(Context context) {
		super(context, DATABASE_NAME, null, 1);
		Log.e("datattconnn", "createddddddd");
		
	}

	@Override
	public void onCreate(SQLiteDatabase arg0) {
		// TODO Auto-generated method stub
		Log.e("datatt", "createddddddd");
		try
		{
		datacreatewrd="create table "+tb_wrds+"("+wrd_id+" integer primary key autoincrement,"+wrdcol1+" text,"+wrdcol2+" text,"+wrdcol3+" text);";
		arg0.execSQL(datacreatewrd);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub
		
	}

}
