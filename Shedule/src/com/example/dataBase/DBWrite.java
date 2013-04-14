package com.example.dataBase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.SimpleAdapter;

import com.example.shedule.Add;
import com.example.shedule.CorrectAdd;
import com.example.shedule.Edit;
import com.example.shedule.R;

public class DBWrite extends Add {
	
	String[] tableName = {"Monday", "Tuesday", "Wensday", "Thursday", "Friday", "Saturday", "Sunday"};
	public static int line = 0;
	final String ATTRIBUTE_NAME_TICHER = "NAME";
	final String ATTRIBUTE_NAME_LESSON = "TEXT";
	final String ATTRIBUTE_NAME_NUMBER = "NUMBER";
	final String ATTRIBUTE_TIME_START = "TIMES";
	final String ATTRIBUTE_TIME_FINISH = "TIMEF";
	final String ATTRIBUTE_CHECKED = "checked";
	int j;
	public SimpleAdapter sAdapter;
	public boolean flagNull = false;
	int id;
	public static String e1, e2, t1, t2;
	private static final String TAG = "myLogs";
	
	//Метод чтения базы данных, подключение к базе, чтение и запись в адаптер
	public SimpleAdapter write(String week, Context x){
		DBHelper dbHelper = new DBHelper(x);
		SQLiteDatabase db = dbHelper.getWritableDatabase();

		// получаем курсор
		Cursor cursor = db.query(week, null, null, null, null, null, null);


	      
					// массив имен атрибутов, из которых будут читаться данные
				String[] from = { ATTRIBUTE_NAME_LESSON, ATTRIBUTE_NAME_TICHER, ATTRIBUTE_NAME_NUMBER
						, ATTRIBUTE_TIME_START, ATTRIBUTE_TIME_FINISH};
				// массив ID View-компонентов, в которые будут вставлять данные
				int[] to = { R.id.tvLesson, R.id.tvName, R.id.tvNumber, R.id.tvStart, R.id.tvFinish};
				
				
					// массивы данных
					String[] texts = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10",
							          "11", "12", "13", "14", "15", "16", "17", "18", "19", "20",
							          "21", "22", "23", "24", "25", "26", "27", "28", "29", "30",
							          "31", "32", "33", "34", "35", "36", "37", "38", "39", "40",
							          "41", "42", "43", "44", "45", "46", "47", "48", "49", "50"};
					// создаем объект для данных
					// подключаемся к БД
					// упаковываем данные в понятную для адаптера структуру
					ArrayList<Map<String, Object>> data = new ArrayList<Map<String, Object>>(
					texts.length);		
					j = 0;
					
					if (cursor.moveToFirst()) {
							flagNull = true;
				    	      int lessonColIndex = cursor.getColumnIndex("lesson");
				    	      int ticherColIndex = cursor.getColumnIndex("ticher");
				    	      int startColIndex = cursor.getColumnIndex("timestart");
				    	      int finishColIndex = cursor.getColumnIndex("timefinish");
		
				    	      
				    	      
				        do {
				        			Map<String, Object> m;
				    				m = new HashMap<String, Object>();
				    				m.put(ATTRIBUTE_NAME_LESSON, (cursor.getString(lessonColIndex)));
				    				m.put(ATTRIBUTE_NAME_NUMBER, texts[j]);
				    				m.put(ATTRIBUTE_NAME_TICHER, (cursor.getString(ticherColIndex))); 
				    				//Записываем время начала и конца занятий
				    				m.put(ATTRIBUTE_TIME_START, (cursor.getString(startColIndex)));
				    				m.put(ATTRIBUTE_TIME_FINISH, (cursor.getString(finishColIndex)));
		
				    				data.add(m);
				    				j++;
				        }
				        while (cursor.moveToNext());}
					else 
						flagNull = false;
					
		
				      
				      db.close();
				      
				      
				  	// создаем адаптер
						sAdapter = new SimpleAdapter(x, data, R.layout.item_list_view,
						from, to);
			return sAdapter;	

	}

	//Метод чтения базы данных, подключение к базе, чтение и запись в адаптер
	public SimpleAdapter writeDay(String week, Context x){
		DBHelper dbHelper = new DBHelper(x);
		SQLiteDatabase db = dbHelper.getWritableDatabase();

		// получаем курсор
		Cursor cursor = db.query(week, null, null, null, null, null, null);


	      
					// массив имен атрибутов, из которых будут читаться данные
				String[] from = { ATTRIBUTE_NAME_LESSON, ATTRIBUTE_NAME_TICHER, ATTRIBUTE_NAME_NUMBER
						, ATTRIBUTE_TIME_START, ATTRIBUTE_TIME_FINISH};
				// массив ID View-компонентов, в которые будут вставлять данные
				int[] to = { R.id.tvDayLesson, R.id.tvDayTicher, R.id.tvDayNumber, R.id.tvDayStartTime, R.id.tvDayFinishTime};
				
				
					// массивы данных
					String[] texts = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10",
							          "11", "12", "13", "14", "15", "16", "17", "18", "19", "20",
							          "21", "22", "23", "24", "25", "26", "27", "28", "29", "30",
							          "31", "32", "33", "34", "35", "36", "37", "38", "39", "40",
							          "41", "42", "43", "44", "45", "46", "47", "48", "49", "50"};
					// создаем объект для данных
					// подключаемся к БД
					// упаковываем данные в понятную для адаптера структуру
					ArrayList<Map<String, Object>> data = new ArrayList<Map<String, Object>>(
					texts.length);		
					j = 0;
					
					if (cursor.moveToFirst()) {
							flagNull = true;
				    	      int lessonColIndex = cursor.getColumnIndex("lesson");
				    	      int ticherColIndex = cursor.getColumnIndex("ticher");
				    	      int startColIndex = cursor.getColumnIndex("timestart");
				    	      int finishColIndex = cursor.getColumnIndex("timefinish");
		
				    	      
				    	      
				        do {
				        			Map<String, Object> m;
				    				m = new HashMap<String, Object>();
				    				m.put(ATTRIBUTE_NAME_LESSON, (cursor.getString(lessonColIndex)));
				    				m.put(ATTRIBUTE_NAME_NUMBER, texts[j]);
				    				m.put(ATTRIBUTE_NAME_TICHER, (cursor.getString(ticherColIndex))); 
				    				//Записываем время начала и конца занятий
				    				m.put(ATTRIBUTE_TIME_START, (cursor.getString(startColIndex)));
				    				m.put(ATTRIBUTE_TIME_FINISH, (cursor.getString(finishColIndex)));
		
				    				data.add(m);
				    				j++;
				        }
				        while (cursor.moveToNext());}
					else 
						flagNull = false;
					
		
				      
				      db.close();
				      
				      
				  	// создаем адаптер
						sAdapter = new SimpleAdapter(x, data, R.layout.item_list_view_for_show_day,
						from, to);
			return sAdapter;	

	}
	
	
	public SimpleAdapter writeWeek(Context x){
		DBHelper dbHelper = new DBHelper(x);
		SQLiteDatabase db = dbHelper.getWritableDatabase();
				int max = 0;
				for(int m = 0; m < 7; m++){
					Cursor c = db.query(tableName[m], null, null, null, null, null, null);
					int b = c.getCount(); 
					if(max < b)
						max = b;
		
				}
				Log.d(TAG,""+ max);
				db.close();

				
				db = dbHelper.getWritableDatabase();
				String[] from = { ATTRIBUTE_NAME_LESSON, ATTRIBUTE_NAME_TICHER,
						ATTRIBUTE_TIME_START, ATTRIBUTE_TIME_FINISH};
				int[] to = { R.id.rectLesson, R.id.rectName, R.id.rectTimeStart, R.id.rectTimeFinish};
				ArrayList<Map<String, Object>> dataGrid = new ArrayList<Map<String, Object>>(10);		

				
	
			for(int z = 0; z < max; z++){
				for(int k = 0; k < 7; k++){
					Cursor cursor = db.query(tableName[k], null, null, null, null, null, null);
						if (cursor.moveToPosition(z)) {
								flagNull = true;
					    	      int lessonColIndex = cursor.getColumnIndex("lesson");
					    	      int ticherColIndex = cursor.getColumnIndex("ticher");
					    	      int startColIndex = cursor.getColumnIndex("timestart");
					    	      int finishColIndex = cursor.getColumnIndex("timefinish");
						        			Map<String, Object> m;
						    				m = new HashMap<String, Object>();
						    				m.put(ATTRIBUTE_NAME_LESSON, (cursor.getString(lessonColIndex)));
						    				m.put(ATTRIBUTE_NAME_TICHER, (cursor.getString(ticherColIndex))); 
						    				m.put(ATTRIBUTE_TIME_START, (cursor.getString(startColIndex)));
						    				m.put(ATTRIBUTE_TIME_FINISH, (cursor.getString(finishColIndex)));
						    				dataGrid.add(m);
						}
						else{ 
								Map<String, Object> n;
			    				n = new HashMap<String, Object>();
			    				n.put(ATTRIBUTE_NAME_LESSON, "");
			    				n.put(ATTRIBUTE_NAME_TICHER, ""); 
			    				n.put(ATTRIBUTE_TIME_START, "");
			    				n.put(ATTRIBUTE_TIME_FINISH, "");
			    				dataGrid.add(n);}
				}
			}
			   db.close();
			      SimpleAdapter adapter = new SimpleAdapter(x, dataGrid, R.layout.rect_grid_view,
						from, to); 
			return adapter;	

	}

	
	public ArrayAdapter<String> writeNumberInGrid(Context x){
		String[] number = {"Понедельник","Вторник","Среда","Четверг","Пятница","Суббота","Воскресенье"};
		
		ArrayAdapter<String> numberAdapter = new ArrayAdapter<String>(x, R.layout.rect_grid_view_number_and_week, R.id.rectGridTextView, number);
		return numberAdapter;
	}


	
	//Метод чтения базы данных, подключение к базе, чтение и запись в запись в элементы, которые на CorrectAdd
	public boolean writeCorrect(Context x, String week, int correctId){
		DBHelper dbHelper = new DBHelper(x);
		com.example.shedule.Edit edit = new Edit();
		com.example.shedule.CorrectAdd cor = new CorrectAdd();
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		// получаем курсор
		boolean flag = false;
		Cursor cursor = db.query(week, null, null, null, null, null, null);
		if(cursor.moveToPosition(correctId)){
	      int lessonColIndex = cursor.getColumnIndex("lesson");
	      int ticherColIndex = cursor.getColumnIndex("ticher");
	      int startColIndex = cursor.getColumnIndex("timestart");
	      int finishColIndex = cursor.getColumnIndex("timefinish");
	      int idColIndex = cursor.getColumnIndex("id");

	      id = cursor.getInt(idColIndex);
	      
	      e1 = cursor.getString(lessonColIndex);
	      e2 = cursor.getString(ticherColIndex);
	      t1 = cursor.getString(startColIndex);
	      t2 = cursor.getString(finishColIndex);
	      
	      flag = true;
		}
		else 
			flag=false;
	      
	      
	      
	      db.close();
	      return flag;
	}

	//проверка базы данных на наличие записей
	public boolean info(Context x){
		DBHelper dbHelper = new DBHelper(x);
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		int i = 0;
		boolean flagInfo = false;
			for(i = 0; i < 7; i++ ){
				Cursor cursor = db.query(tableName[i], null, null, null, null, null, null);
				if(cursor.moveToFirst()){
					flagInfo = true;
					db.close();
					break;
				}
									
			}
	db.close();		
	return flagInfo;
	}

	//*********************************************Сделать проверку черты*******************
	public void infoLine(Context x){
		DBHelper dbHelper = new DBHelper(x);
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		String lineOfWeek = "хрен";		
		Cursor cursor = db.query("Setting", null, null, null, null, null, null);
				if(cursor.moveToFirst()){
			    int idLineIndex = cursor.getColumnIndex("lineOfWeek");
			    lineOfWeek = cursor.getString(idLineIndex);}
				db.close();		
				switch(lineOfWeek.length()){
					case 4:	
						line = 0;
						break;
					case 10:	
						line = 1;
						break;	
					case 12:	
						line = 2;
						break;}
				Log.d(TAG, ""+lineOfWeek+" = "+ line);
	}
	

}
