package com.example.shedule;

import java.util.Calendar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class ShowDay extends Activity{

	private static final String TAG = "myLogs";

		
	
		TextView noLesson;
		ImageView imageWeek;
		public static String week = "Monday";
		ListView listview;
		String statusWeek;

		boolean flagUpdate = false;
		static int i = 0;
		int j = 0;
		

	
	
	
	//Метод создания Laout edit.xml
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.show_day);
		imageWeek = (ImageView) findViewById(R.id.imageDayWeek);
		noLesson = (TextView) findViewById(R.id.tvDayNoLesson);
		// Заполнение List View при создании формы
		listview = (ListView) findViewById(R.id.listViewDay);
		Calendar c = Calendar.getInstance();
		Log.d(TAG, " day = "+ c.get(c.DAY_OF_WEEK));
		i = c.get(c.DAY_OF_WEEK);
		if(i==1)
			i=7;
		week(i);
		Home home = new Home();
		TextView status = (TextView) findViewById(R.id.infoTV1);
		if(home.lineOfWeek.length() == 4)
			status.setText(c.get(c.DAY_OF_MONTH)+ ", " + statusWeek);
		else
			status.setText(c.get(c.DAY_OF_MONTH) + ", " + statusWeek + ", " + home.lineOfWeek);

	}
		
	
	//Написание название недели в lbWeek
	public void week(int i){
		if (2==i){
			imageWeek.setImageResource(R.drawable.show_day_one);
			week = "Monday";
			statusWeek = "Понедельник";
			writeOnCreat();
			}
		else if (3==i){
			imageWeek.setImageResource(R.drawable.show_day_two);
			week = "Tuesday";
			statusWeek = "Вторник";
			writeOnCreat();

		}
		else if (4==i){
			imageWeek.setImageResource(R.drawable.show_day_three);
			week = "Wensday";
			statusWeek = "Среда";
			writeOnCreat();

		}
		else if (5==i){
			imageWeek.setImageResource(R.drawable.show_day_four);
			week = "Thursday";
			statusWeek = "Четверг";
			writeOnCreat();

		}
		else if (6==i){
			imageWeek.setImageResource(R.drawable.show_day_five);
			week = "Friday";
			statusWeek = "Пятница";
			writeOnCreat();

		}
		else if (7==i){
			imageWeek.setImageResource(R.drawable.show_day_sixe);
			week = "Sunday";
			statusWeek = "Суббота";
			writeOnCreat();

		}
		else if (8==i){
			imageWeek.setImageResource(R.drawable.show_day_seven);
			week = "Saturday";
			statusWeek = "Воскресенье";
			writeOnCreat();

		}


	}

	// Обработка нажатия кнопки вправо верхней части экрана
	public void rightClick(View v){
		if (8 == i)
			i = 2;
		
		else i++;
		week(i);
		Log.d(TAG, "" + i + " "+ statusWeek);
	}
	
	// Обработка нажатия кнопки влево верхней части экрана
	public void leftClick(View v){
		if (2 == i) 
			i = 8;
	
		else i--;
		week(i);
		Log.d(TAG, "" + i + " "+ statusWeek);

	}
	
	//заполнение ListView
	public void writeOnCreat(){
						com.example.dataBase.DBWrite dbWrite = new com.example.dataBase.DBWrite();
						
						// Записываем в ListView Адаптер
						listview = (ListView) findViewById(R.id.listViewDay);
						listview.setAdapter(dbWrite.writeDay(week, this));

						
//**********************При пустом ListView написание соответствующей надписи****************
				// Проверка записей в базе, если НЕТ то вернется FALSE		
				if (!dbWrite.flagNull) {
						noLesson.setVisibility(2);
						switch(i){	
							case 2:{
								noLesson.setText("У вас нет расписания на Понедельник, но можно создать! (Главное Меню - Создать/Изменить)");
								break;
								}
							case 3:{
								noLesson.setText("У вас нет расписания на Вторник, но можно создать! (Главное Меню - Создать/Изменить)");
								break;
								}
							case 4:{
								noLesson.setText("У вас нет расписания на Среду, но можно создать! (Главное Меню - Создать/Изменить)");
								break;
								}
							case 5:{
								noLesson.setText("У вас нет расписания на Четверг, но можно создать! (Главное Меню - Создать/Изменить)");
								break;
								}
							case 6:{
								noLesson.setText("У вас нет расписания на Пятницу, но можно создать! (Главное Меню - Создать/Изменить)");
								break;
								}
							case 7:{
								noLesson.setText("У вас нет расписания на Субботу, но можно создать! (Главное Меню - Создать/Изменить)");
								break;
								}
							case 8:{
								noLesson.setText("У вас нет расписания на Воскресенье, но можно создать! (Главное Меню - Создать/Изменить)");
								break;
								}
						
						}
						}
						
				else
					noLesson.setVisibility(8); 
	}
	

    
    
}