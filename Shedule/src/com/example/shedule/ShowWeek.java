package com.example.shedule;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.SimpleAdapter;

public class ShowWeek extends Activity {
	private static final String TAG = "myLogs";
	SimpleAdapter sa;
/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.show_week);
		com.example.dataBase.DBWrite dbWrite = new com.example.dataBase.DBWrite();
		GridView gvNumber = (GridView) findViewById(R.id.gridView2);
		gvNumber.setAdapter(dbWrite.writeNumberInGrid(this));

		GridView gvMain = (GridView) findViewById(R.id.gridView1);
		
		sa = dbWrite.writeWeek(this);
		gvMain.setAdapter(sa);
		gvMain.setOnItemClickListener((OnItemClickListener) this);

	}
	

	public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {

	// TODO Auto-generated method stub

	Log.d(TAG, ""+sa.getItem(position));

	}
	



}