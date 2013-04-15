package com.example.shedule;

import java.util.Calendar;

import android.app.Activity;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.dataBase.DBUpdate;
import com.example.dataBase.DBWrite;

public class CorrectAdd extends Activity{
	
	public EditText ed1, ed2;
	public TextView tv1, tv2;
	int idCorrect;
	boolean start = false, finish = false;
	private static final String TAG = "myLogs";
	String a,b,c,d;
	


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.correct);
		ed1 = (EditText) findViewById(R.id.etCorrectLesson);
		ed2 = (EditText) findViewById(R.id.etCorrectTicher);
		tv1 = (TextView) findViewById(R.id.tvCorrectLeft);
		tv2 = (TextView) findViewById(R.id.tvCorrectRight);
		com.example.dataBase.DBWrite dbWrite = new DBWrite();
		Edit edit = new Edit();
		if(dbWrite.writeCorrect(this, edit.week, edit.correctId)){
			ed1.setText(dbWrite.e1);
			ed2.setText(dbWrite.e2);
			tv1.setText(dbWrite.t1);
			tv2.setText(dbWrite.t2);
			idCorrect = edit.correctId;
		}
		else 
			finish();
	      

	}
	
	protected void onStart(){
		super.onStart();
//********************Видимость группы для изменения черты*******
/*		Home home = new Home();
		RadioGroup radiogroup = (RadioGroup) findViewById(R.id.correctRadioGroup);
		String line = home.lineOfWeek;
		if(line.length() == 10){
			radiogroup.setVisibility(2);}
		else if(line.length() == 12){
			radiogroup.setVisibility(2);}
		else if(line.length() == 4){
			radiogroup.setVisibility(8);} */

	}

	
	public void editClick(View v){
		a = ed1.getText().toString();
		b = ed2.getText().toString();
		c = tv1.getText().toString();
		d = tv2.getText().toString();
		com.example.dataBase.DBUpdate dbUpdate = new DBUpdate();
		Edit edit = new Edit();
		dbUpdate.update(idCorrect, edit.week, this, a, b, c, d);
		finish();

	}
	
	public void cancelClick(View v){
		finish();
	}
	
	
	
	protected Dialog onCreateDialog(int id) {
		if (id == 1) {					
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
			tv1.setText(h+":"+m);
		else if(finish == true)
			tv2.setText(h+":"+m); 
}
	};
	
	public void leftClick(View v){
	start = true;
	finish = false;
	showDialog(1);			
}

	public void rightClick(View v){
	finish = true;
	start = false;
	showDialog(1);
}


}
