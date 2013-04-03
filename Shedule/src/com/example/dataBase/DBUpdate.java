package com.example.dataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.shedule.CorrectAdd;
import com.example.shedule.Edit;


public class DBUpdate{
	

	
	public void update(int position,Context context,String a,String b,String c,String d){
		DBHelper dbHelper = new DBHelper(context);
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		com.example.shedule.Edit edit = new Edit();
		com.example.shedule.CorrectAdd correct = new CorrectAdd();
		ContentValues cv = new ContentValues();
		cv.put("lesson", a);
		cv.put("ticher", b);
		cv.put("timestart", c);
		cv.put("timefinish", d);
		db.update(edit.week, cv, "id = " + position, null);
		dbHelper.close();

	}




	

}
