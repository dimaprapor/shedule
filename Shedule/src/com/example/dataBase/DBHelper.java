package com.example.dataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
			public DBHelper(Context context) {
			// конструктор суперкласса
			super(context, "da445219s145d127sfw451", null, 1);
			}
			@Override
			public void onCreate(SQLiteDatabase db) {
			// создаем таблицу с полями
			db.execSQL("create table Monday ("
					+ "id integer primary key autoincrement,"
					+ "lesson text,"
					+ "ticher text,"
					+ "timestart text,"
					+ "timefinish text,"
					+ "lessonline text" + ");");
			db.execSQL("create table Tuesday ("
					+ "id integer primary key autoincrement,"
					+ "lesson text,"
					+ "ticher text,"
					+ "timestart text,"
					+ "timefinish text,"
					+ "lessonline text" + ");");
			db.execSQL("create table Wensday ("
					+ "id integer primary key autoincrement,"
					+ "lesson text,"
					+ "ticher text,"
					+ "timestart text,"
					+ "timefinish text,"
					+ "lessonline text" + ");");
			db.execSQL("create table Thursday ("
					+ "id integer primary key autoincrement,"
					+ "lesson text,"
					+ "ticher text,"
					+ "timestart text,"
					+ "timefinish text,"
					+ "lessonline text" + ");");
			db.execSQL("create table Friday ("
					+ "id integer primary key autoincrement,"
					+ "lesson text,"
					+ "ticher text,"
					+ "timestart text,"
					+ "timefinish text,"
					+ "lessonline text" + ");");
			db.execSQL("create table Saturday ("
					+ "id integer primary key autoincrement,"
					+ "lesson text,"
					+ "ticher text,"
					+ "timestart text,"
					+ "timefinish text,"
					+ "lessonline text" + ");");
			db.execSQL("create table Sunday ("
					+ "id integer primary key autoincrement,"
					+ "lesson text,"
					+ "ticher text,"
					+ "timestart text,"
					+ "timefinish text,"
					+ "lessonline text" + ");");
			db.execSQL("create table Setting ("
					+ "id integer primary key autoincrement,"
					+ "lineOfWeek text,"
					+ "volume text,"
					+ "vibrator text" + ");");
			}
			@Override
			public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			}
			} 