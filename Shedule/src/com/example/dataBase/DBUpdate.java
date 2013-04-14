package com.example.dataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.shedule.CorrectAdd;
import com.example.shedule.Edit;


public class DBUpdate{
private static final String TAG = "myLogs";	

	
	public void update(int position, String week,Context context,String a,String b,String c,String d){
		DBHelper dbHelper = new DBHelper(context);
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		com.example.shedule.Edit edit = new Edit();
		Cursor cursor = db.query(week, null, null, null, null, null, null);
		int id;
		if(cursor.moveToPosition(position)){
			int idColIndex = cursor.getColumnIndex("id");
			id = cursor.getInt(idColIndex);
			ContentValues cv = new ContentValues();
			cv.put("lesson", a);
			cv.put("ticher", b);
			cv.put("timestart", c);
			cv.put("timefinish", d);
			db.update(week, cv, "id = " + id, null);
			dbHelper.close();}

	}




	

}
