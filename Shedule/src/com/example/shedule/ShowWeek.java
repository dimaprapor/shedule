package com.example.shedule;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.example.dataBase.DBWrite;

public class ShowWeek extends Activity {
	private static final String TAG = "myLogs";
	SimpleAdapter adapter;
	String[] tableName = {"Monday", "Tuesday", "Wensday", "Thursday", "Friday", "Saturday", "Sunday"};
	public static int positionHorisontal, positionVertical;
	public static String week = "Monday";
	public int correctId = 0;
	
/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.show_week);
		com.example.dataBase.DBWrite dbWrite = new com.example.dataBase.DBWrite();
		GridView gvNumber = (GridView) findViewById(R.id.gridView2);
		gvNumber.setAdapter(dbWrite.writeNumberInGrid(this));
		
		adapter = dbWrite.writeWeek(this);
		GridView gvMain = (GridView) findViewById(R.id.gridView1);
		gvMain.setAdapter(adapter);
		gvMain.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView parent, View v, int position, long id) {
            	int positionItem = (int) adapter.getItemId(position);
            	Log.d(TAG, "ClickGritItem - "+positionItem);

            	
            	positionHorisontal = positionItem % 7; 
//            	Log.d(TAG, "Week - "+tableName[positionHorisontal]);
            	positionVertical = positionItem / 7; 
//            	Log.d(TAG, "Week - "+positionVertical);
            	Log.d(TAG, "["+positionHorisontal+";"+positionVertical+"]");
            	week = tableName[positionHorisontal];
            	dialogItem();
            	
            	
            }
        });
	}
	
	public void onStart(){
		super.onStart();
		com.example.dataBase.DBWrite dbWrite = new DBWrite();
		TextView tv1 = (TextView)findViewById(R.id.showWeekNoLesson);
		if(dbWrite.info(this))
			tv1.setVisibility(8);
			else
				tv1.setVisibility(2);


	}
	
	private void dialogItem(){
			Intent intent = new Intent(this, ShowDialogInfoItem.class);
			startActivity(intent);

			
		}
		
			
	
	
	


	



}