package com.example.shedule;

import android.app.Activity;
import android.content.ContentValues;
import android.content.DialogInterface.OnClickListener;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.dataBase.DBHelper;


public class Setting extends Activity {
	
	private static final String TAG = "myLogs";
	ImageView imLineTrue, imLineFalse;
	OnClickListener radioListener;
	String c;
	RadioGroup radiogroup;
	ImageView bt1, bt2;
	TextView tv1;
	
	@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.setting);
			imLineTrue = (ImageView) findViewById(R.id.imageView3);
			imLineFalse = (ImageView) findViewById(R.id.imageView4);
			
			tv1 = (TextView) findViewById(R.id.rectTimeStart);
			bt1 = (ImageView) findViewById(R.id.imageView1);
			bt2 = (ImageView) findViewById(R.id.imageView2);
				



	}
	
	protected void onStart(){
		super.onStart();
		Home home = new Home();
		String line = home.lineOfWeek;
		bt1.setImageResource(R.drawable.bt_yes_line_no);
//		bt1.setBackgroundColor(getResources().getColor(R.color.settingColorYes));
//		bt2.setBackgroundColor(getResources().getColor(R.color.settingColorNo));

		if(line.length() == 10){
			bt1.setImageResource(R.drawable.bt_yes_line_yes);
			bt2.setImageResource(R.drawable.bt_no_line_no);
			tv1.setVisibility(2);
			imLineTrue.setImageResource(R.drawable.rb_true_line_yes);
			imLineFalse.setImageResource(R.drawable.rb_false_line_no);
			imLineTrue.setVisibility(2);
			imLineFalse.setVisibility(2);
			}
		else if(line.length() == 12){
			bt1.setImageResource(R.drawable.bt_yes_line_yes);
			bt2.setImageResource(R.drawable.bt_no_line_no);
			tv1.setVisibility(2);
			imLineTrue.setImageResource(R.drawable.rb_true_line_no);
			imLineFalse.setImageResource(R.drawable.rb_false_line_yes);
			imLineTrue.setVisibility(2);
			imLineFalse.setVisibility(2);
			}
			
			else if(line.length() == 4){
			bt1.setImageResource(R.drawable.bt_yes_line_no);
			bt2.setImageResource(R.drawable.bt_no_line_yes);
			tv1.setVisibility(8);
			imLineTrue.setVisibility(8);
			imLineFalse.setVisibility(8);
			} 


	}

	public void readLine(String c){
				com.example.dataBase.DBHelper dbHelper = new DBHelper(this);
				SQLiteDatabase db = dbHelper.getWritableDatabase();
						ContentValues cv = new ContentValues();
						cv.put("lineOfWeek", c);
						db.update("Setting", cv, "id = 1", null);
						dbHelper.close();
						Log.d(TAG, " " + c);
		}
	
	public void lineYes(View v){
		bt1.setImageResource(R.drawable.bt_yes_line_yes);
		bt2.setImageResource(R.drawable.bt_no_line_no);
		tv1.setVisibility(2);
		imLineTrue.setVisibility(2);
		imLineFalse.setVisibility(2);
	}
	
	public void lineNo(View v){
		readLine("Null");
		bt1.setImageResource(R.drawable.bt_yes_line_no);
		bt2.setImageResource(R.drawable.bt_no_line_yes);
		imLineTrue.setImageResource(R.drawable.rb_true_line_no);
		imLineFalse.setImageResource(R.drawable.rb_false_line_no);
		tv1.setVisibility(8);
		imLineTrue.setVisibility(8);
		imLineFalse.setVisibility(8);
	}
	
	public void lineTrue(View v){
		readLine("Над чертой");
		imLineTrue.setImageResource(R.drawable.rb_true_line_yes);
		imLineFalse.setImageResource(R.drawable.rb_false_line_no);

	}
	
	public void lineFalse(View v){
		readLine(" Под чертой ");
		imLineTrue.setImageResource(R.drawable.rb_true_line_no);
		imLineFalse.setImageResource(R.drawable.rb_false_line_yes);
		
	}

	
	

}