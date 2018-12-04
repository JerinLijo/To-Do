package com.example.to_do;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.ActionBar;
import android.app.Activity;
import android.app.ActionBar.LayoutParams;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.opengl.Visibility;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Home extends Activity {

	ListView navlist;
	ArrayAdapter<String> ar;
	DrawerLayout drawer_layout1;
	ActionBar ab;
	Context con;
	ActionBarDrawerToggle mDrawerToggle;
	String mTitle = "", accountName;
	TextView gmailname;
	ListView listdet;
	Cursor cd;
	String getdata, wrd, wrdmng,dateevt;
	SQLiteDatabase db;
	Database1 data;
	JSONArray ja;
	JSONObject jo;
	ArrayList<String> arwrd;
	TextView todow, errandsw;
	ListAdapter listdet1;

	protected void onCreate(Bundle savedInstanceState) {

		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home);
		navlist = (ListView) findViewById(R.id.navList);
		drawer_layout1 = (DrawerLayout) findViewById(R.id.drawer_layout);
		gmailname = (TextView) findViewById(R.id.googlename);

		mTitle = (String) getTitle();

		addDrawerItems();
		getActionBar().setIcon(R.drawable.option22);
		listdet = (ListView) findViewById(R.id.listdetails);

		loaddata();
		// setupDrawer();

		
		  Account account = getAccount(AccountManager.get(Home.this));
		  accountName = account.name; 
		  //String fullName =accountName.substring(0,accountName.lastIndexOf("@"));
		 
		// / Toast.makeText(Home.this, "name----"+fullName, 1000).show();
		// Log.e("nammmmmmeeee", accountName);
		/*
		 * emailEditText.setText(accountName);
		 * fullNameEditText.setText(fullName);
		 */
		 gmailname.setText(accountName);

		mDrawerToggle = new ActionBarDrawerToggle(Home.this, drawer_layout1,
				R.drawable.option22, R.string.drawer_open,
				R.string.drawer_close) {

			/** Called when a drawer has settled in a completely open state. */
			public void onDrawerOpened(View drawerView) {
				// getActionBar().setTitle("Select a river");
				getActionBar().setIcon(R.drawable.ic_indicator);
				invalidateOptionsMenu();
				super.onDrawerOpened(drawerView);

				// creates call to onPrepareOptionsMenu()
			}

			/** Called when a drawer has settled in a completely closed state. */
			public void onDrawerClosed(View view) {
				getActionBar().setTitle(mTitle);
				getActionBar().setIcon(R.drawable.option22);
				invalidateOptionsMenu();
			}

		};

		mDrawerToggle.setDrawerIndicatorEnabled(true);
		drawer_layout1.setDrawerListener(mDrawerToggle);

		navlist.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				drawer_layout1.closeDrawers();
				switch (arg2) {
				case 0:
					// Toast.makeText(Home.this, "Create", 1000).show();
					Intent ob = new Intent(Home.this, Create.class);
					startActivity(ob);
					break;
				case 1:
					// Toast.makeText(Home.this, "Update", 1000).show();
					Intent ob1 = new Intent(Home.this, Update.class);
					startActivity(ob1);
					break;
				case 2:
					// Toast.makeText(Home.this, "Delete", 1000).show();
					Intent ob2 = new Intent(Home.this, Delete.class);
					startActivity(ob2);
					break;
				default:
					break;
				}

			}
		});

	}

	public static Account getAccount(AccountManager accountManager) {
		Account[] accounts = accountManager.getAccountsByType("com.google");
		Account account;
		if (accounts.length > 0) {
			account = accounts[0];
		} else {
			account = null;
		}
		return account;
	}

	private void addDrawerItems() {
		String[] osArray = { "Create", "Update", "Delete" };
		ar = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, osArray);
		navlist.setAdapter(ar);
		getActionBar().setHomeButtonEnabled(true);

		// Enabling Up navigation
		// getActionBar().setDisplayHomeAsUpEnabled(true);

	}

	public void loaddata() {
		arwrd = new ArrayList<String>();
		data = new Database1(Home.this);
		ja = new JSONArray();
		try {
			getdata = "select * from " + Database1.tb_wrds;

			db = data.getReadableDatabase();
			cd = db.rawQuery(getdata, null);
			//Toast.makeText(Home.this, "" + cd.getCount(), 1000).show();
			if (cd.getCount() > 0) {

				while (cd.moveToNext()) {
					wrd = cd.getString(cd.getColumnIndex(Database1.wrdcol1));
					wrdmng = cd.getString(cd.getColumnIndex(Database1.wrdcol2));
					dateevt = cd.getString(cd.getColumnIndex(Database1.wrdcol3));
					jo = new JSONObject();

					jo.put("word", wrd);
					jo.put("wrdmng", wrdmng);
					jo.put("dateevt", dateevt);
					ja.put(jo);

					//Toast.makeText(Home.this, "" + ja, 1000).show();

				}
				listdet1 = new ListAdapter(Home.this, ja);
				listdet.setAdapter(listdet1);
				listdet1.notifyDataSetChanged();

			} else {

			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		loaddata();
		//Toast.makeText(Home.this, "resume", 1000).show();

	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		if (mDrawerToggle.onOptionsItemSelected(item)) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
