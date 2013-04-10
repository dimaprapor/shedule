package com.example.shedule;

import java.util.Calendar;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.dataBase.DBHelper;
import com.example.shedule.R.drawable;


public class Add extends Activity {
	private static final String TAG = "myLogs";
	
	final int SAVE_MENU_ID = 1;
	final int CANCEL_MENU_ID = 2;
	final int DIALOG_TIME = 1;

	
	public EditText nameTicher, nameLesson;
	TextView tvStart, tvFinish;
	String timeStart, timeFinish, time, day;
	boolean start = false, finish = false, leftTime = false, rightTime = false;
	DBHelper dbHelper;

	
	
	
	
	
		//Метод создания Laout edit.xml
		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.add);
			
			nameLesson = (EditText) findViewById(R.id.editText1);
			nameTicher = (EditText) findViewById(R.id.editText2);
			tvStart = (TextView) findViewById(R.id.tvStartTime);
			tvFinish = (TextView) findViewById(R.id.tvFinishTime);

		} 
		
				
		protected void onStart(){
			super.onStart();
			Home home = new Home();
			RadioGroup radiogroup = (RadioGroup) findViewById(R.id.addRadioGroup);
			String line = home.lineOfWeek;
			if(line.length() == 10){
				radiogroup.setVisibility(2);}
			else if(line.length() == 12){
				radiogroup.setVisibility(2);}
			else if(line.length() == 4){
				radiogroup.setVisibility(8);}

		}
	
				
		
		// Создание пунктов меню
		public boolean onCreateOptionsMenu(Menu menu) {
			// TODO Auto-generated method stub
			menu.add(0, SAVE_MENU_ID, 0, "Добавить");
			menu.add(0, CANCEL_MENU_ID, 0, "Отменить");
			return super.onCreateOptionsMenu(menu);
			}

		// Обработки нажатия пунктов меню
		public boolean onOptionsItemSelected(MenuItem item) {
			
			// TODO Auto-generated method stub
			switch (item.getItemId()){
			//Обработка нажатия кнопки сохранить из меню
			case SAVE_MENU_ID:{
				if (TextUtils.isEmpty(nameLesson.getText().toString())
				|| TextUtils.isEmpty(nameTicher.getText().toString())){
					Toast.makeText(this, "Заполните все поля!", Toast.LENGTH_LONG).show();
					break;
				}
				
				else if(!leftTime || !rightTime){
					Toast.makeText(this, "Корректно заполните время!", Toast.LENGTH_LONG).show();
					break;
				}
				else{
					insert();
					closeAdd();
					

				}
			}
			
			case CANCEL_MENU_ID: {
				closeAdd();
			}
			}
			return super.onOptionsItemSelected(item);
		
		}
		
		public void closeAdd(){
			finish();
		}
		
		public void insert(){
						com.example.dataBase.DBHelper dbHelper = new DBHelper(this);
						Edit edit = new Edit();
						SQLiteDatabase db = dbHelper.getWritableDatabase();	
						ContentValues cv = new ContentValues();
						cv.put("lesson", nameLesson.getText().toString());
						cv.put("ticher", nameTicher.getText().toString());
						cv.put("timestart", tvStart.getText().toString());
						cv.put("timefinish", tvFinish.getText().toString());
						db.insert(edit.week, null, cv);
						dbHelper.close();
			} 

		protected Dialog onCreateDialog(int id) {
				if (id == DIALOG_TIME) {	
					Calendar c = Calendar.getInstance();
						TimePickerDialog tpd = new TimePickerDialog(this, myCallBack, c.get(c.HOUR), c.get(c.MINUTE), true);
					return tpd;
					}
			return super.onCreateDialog(id);
			}
		
		OnTimeSetListener myCallBack = new OnTimeSetListener() {
			public void onTimeSet(TimePicker view, int hour, int minute) {
				String h = String.valueOf(hour);
				String m = String.valueOf(minute);


				if(h.length() == 1){
					h = ("0"+h);}
				if(m.length() == 1){
					m = ("0"+m);}
				if(start == true)
					tvStart.setText(h+":"+m);
				else if(finish == true)
					tvFinish.setText(h+":"+m); 
}
			};
			
		public void startTimeClick(View v){
			start = true;
			finish = false;
			leftTime = true;
			showDialog(DIALOG_TIME);			
		}
		
		public void finishTimeClick(View v){
			finish = true;
			start = false;
			rightTime = true;
			showDialog(DIALOG_TIME);
		}
		

}







