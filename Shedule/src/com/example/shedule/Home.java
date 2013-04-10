package com.example.shedule;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;

import com.example.dataBase.DBHelper;
import com.example.dataBase.DBWrite;
import com.example.shedule.R.drawable;

public class Home extends Activity {
	
	public static String lineOfWeek;

	
	private static final String TAG = "myLogs";
	
	final int DIALOG_EXIT = 1;
	final int DIALOG_ABOUT = 2;
	int id;
	ImageView im;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home_menu);
		com.example.dataBase.DBWrite dbWrite = new DBWrite();
		im = (ImageView) findViewById(R.id.imHomeTextEdit);
		if(dbWrite.info(this))
			im.setImageResource(R.drawable.hometextclear);
			else
				im.setImageResource(R.drawable.hometextnew);
		

	}
	
	protected void onStart(){
		super.onStart();
		//Записываю в базу  Стандартные настройки, если она пустая
		//Суть в том, что настройки записываются один раз, а потом изменяются на вкладке Настройки
		//******************************************************************************************
		com.example.dataBase.DBHelper dbHelper = new DBHelper(this);
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		Cursor cursor = db.query("Setting", null, null, null, null, null, null);
		// Черта - Null (отсутствие)
		if (!cursor.moveToFirst()){
				ContentValues cv = new ContentValues();
				cv.put("lineOfWeek", "null");
				db.insert("Setting", null, cv);
				dbHelper.close();
				lineOfWeek = "null";
			}
		else{
			cursor.moveToFirst();
		    int line = cursor.getColumnIndex("lineOfWeek");
			lineOfWeek = cursor.getString(line);
			dbHelper.close();}
		//******************************************************************************************
	}
	


	
	protected Dialog onCreateDialog(int id) {
		if (id == DIALOG_EXIT) {
		AlertDialog.Builder adb = new AlertDialog.Builder(this);
		adb.setIcon(drawable.close_dialog);
		adb.setTitle("Выход");
		adb.setMessage("Вы уверены?");
		adb.setPositiveButton("Да", myClickListener);
		adb.setNegativeButton("Нет", myClickListener);
		return adb.create();}
		else if (id == DIALOG_ABOUT) {
			AlertDialog.Builder adb = new AlertDialog.Builder(this);
			adb.setIcon(drawable.about_dialog);
			adb.setTitle("Расписание занятий для ВУЗОВ и СУЗОВ");
			adb.setMessage("Данное приложение разработано в рамках курсового проекта по дисциплине" +
					" «Конструирование Программ и Языки Программирования»" +
					" учащимся группы ПК-31 \nТанковичем Дмитрием Николаевичем" +
					"\nГомель 2013г." +
					"\nemail: \ndmitry.tankovich@gmail.com");
			adb.setPositiveButton("Ok", myClickListener);
			return adb.create();}
		
		return super.onCreateDialog(id);
		}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.home, menu);
		return true;
	}
	
	public void showWeek(View v){
		Intent intent = new Intent(this, ShowWeek.class);
		startActivity(intent);
		Log.d(TAG, "Yes");
	}
	
	public void dayClick(View v){
		Intent intent = new Intent(this, ShowDay.class);
		startActivity(intent);
	}
	
	public void settingClick(View v){
		Intent intent = new Intent(this, Setting.class);
		startActivity(intent);
	}
	
	public void editClick(View v){
		Intent intent = new Intent(this, Edit.class);
		startActivity(intent);
	}
	
	public void aboutClick(View v){
		showDialog(2);	
		id = 2;
	}
	
	public void closeClick(View v){
		showDialog(1);
		id = 1;
	}

	OnClickListener myClickListener = new OnClickListener() {
	public void onClick(DialogInterface dialog, int which) {
	if(id == DIALOG_EXIT)
		
			switch (which) {
			// положительная кнопка
			case Dialog.BUTTON_POSITIVE:
				System.exit(2);
				break;
			// негаитвная кнопка
			case Dialog.BUTTON_NEGATIVE:
				dialog.cancel();
				break;}
			
	
	else if(id == DIALOG_ABOUT)
		
			switch (which) {
			// положительная кнопка
			case Dialog.BUTTON_POSITIVE:
				dialog.cancel();
			break;}

	
	}
	};


}
