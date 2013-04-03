package com.example.dataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import com.example.shedule.Edit;

public class DBInsert {
	
	public void insert(Context x, String a, String b, String c, String d){
		DBHelper dbHelper = new DBHelper(x);
		com.example.shedule.Edit edit = new Edit();
		SQLiteDatabase db = dbHelper.getWritableDatabase();	
		ContentValues cv = new ContentValues();
		cv.put("lesson", a);
		cv.put("ticher", b);
		cv.put("timestart", c);
		cv.put("timefinish", d);
		db.insert(edit.week, null, cv);
		dbHelper.close();
} 
	
	/*					com.example.dataBase.DBInsert dbInsert = new DBInsert();
					dbInsert.insert(this, nameLesson.getText().toString(), nameTicher.getText().toString(),
							tvStart.getText().toString(), tvFinish.getText().toString());
					Intent intent = new Intent(this, Edit.class);
					startActivity(intent);
					finish();
*/


}
