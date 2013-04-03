package com.example.dataBase;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.shedule.Edit;

public class DBDelete extends Edit {
	
	public boolean moveToFirst = false;

	// Удаление одной записи из базы, на входе номер записи, имя базы и контекст
	public void deleteRecord(int numberItem, String week, Context x){
				// Создаем объект и подключаемся к базе
				DBHelper dbHelper = new DBHelper(x);
				SQLiteDatabase db = dbHelper.getWritableDatabase();
				//Получаем курсор
				Cursor cursor = db.query(week, null, null, null, null, null, null);
				//Если в базе нет записей вернется false
				if(cursor.moveToFirst()){
						//переходим на указанную позицию используя курсор
						cursor.moveToPosition(numberItem);
						int id = cursor.getInt(0);
						//Удаляем запись закрываем подключение
						db.delete(week, "id = " + id, null); 
						db.close();}
					else
						db.close();
	 

		}

	
	public void allDelete(String week, Context x){
		// Создаем объект и подключаемся к базе
		DBHelper dbHelper = new DBHelper(x);
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		//Получаем курсор
		Cursor cursor = db.query(week, null, null, null, null, null, null);
		//Если в базе нет записей вернется false
		if(cursor.moveToFirst()){
			db.delete(week, null, null);
			db.close();
			moveToFirst = true;

		}
		else
			moveToFirst = false;

		
	}


}
