package com.example.shedule;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shedule.R.drawable;


public class Edit extends Activity {

	private static final String TAG = "myLogs";

	// константы ID пунктов меню
		final int ADD_MENU_ID = 1;
		final int ALLDELETE_MENU_ID = 2;
		
	
		TextView lbWeek, noLesson;
		public static String week = "Monday";
		ListView listview;

		boolean flagUpdate = false;
		static int i = 0;

		public static int correctId;
		int j = 0;
		
		int idDialogDeleteAll = 1;
		

	
	
	
	//Метод создания Laout edit.xml
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.edit);
	}
	
	
	protected void onResume(){
		super.onResume();
		lbWeek = (TextView) findViewById(R.id.tvDayWeek);
		noLesson = (TextView) findViewById(R.id.tvDayNoLesson);
		// Заполнение List View при создании формы
		listview = (ListView) findViewById(R.id.lvList);
		week(i);
		writeOnCreat();
		registerForContextMenu(listview);

	}
	
		
	//Создание меню
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		menu.add(1, ADD_MENU_ID, 0, "Добавить");
		menu.add(1, ALLDELETE_MENU_ID, 0, "Удалить все");
		return super.onCreateOptionsMenu(menu);
		}	
	
	//Создание контекстного меню
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
	super.onCreateContextMenu(menu, v, menuInfo);
		menu.add(0, 1, 0, "Удалить запись");
		menu.add(0, 2, 0, "Редактировать");

	}
	
	// Обработка нажатия на пункты контекстного меню
	@Override
	public boolean onContextItemSelected(MenuItem item) {

		//Удалить запись
		if (item.getItemId() == 1) {
			// получаем инфу о пункте списка
			AdapterContextMenuInfo acmi = (AdapterContextMenuInfo) item.getMenuInfo();
			com.example.dataBase.DBDelete delete = new com.example.dataBase.DBDelete();
			delete.deleteRecord(acmi.position, week, this);
			writeOnCreat();
			Toast.makeText(this, "Удалено", Toast.LENGTH_SHORT).show();
		}
		// Изменить запись
		else {
			AdapterContextMenuInfo acmi = (AdapterContextMenuInfo) item.getMenuInfo();
			correctId = acmi.position;
			Toast.makeText(this, "Изменить", Toast.LENGTH_SHORT).show();
			Intent intent = new Intent(this, CorrectAdd.class);
			startActivity(intent);
//			com.example.dataBase.DBUpdate dbUpdate = new com.example.dataBase.DBUpdate();
//			AdapterContextMenuInfo acmi = (AdapterContextMenuInfo) item.getMenuInfo();
//			dbUpdate.update(acmi.position, this);
			
		}
	
	return super.onContextItemSelected(item);
	}
	
	protected Dialog onCreateDialog(int idDialogDeleteAll) {
		if (idDialogDeleteAll == 1) {
		AlertDialog.Builder adb = new AlertDialog.Builder(this);
		adb.setIcon(drawable.about_dialog);
		adb.setTitle("Удаление");
		adb.setMessage("Удалить все записи?");
		adb.setPositiveButton("Да", myClickListener);
		adb.setNegativeButton("Нет", myClickListener);
		return adb.create();}
		
		return super.onCreateDialog(idDialogDeleteAll);
		}
	
	OnClickListener myClickListener = new OnClickListener() {
	
		
	public void onClick(DialogInterface dialog, int which) {
	if(idDialogDeleteAll == 1)
		
			switch (which) {
			// положительная кнопка
			case Dialog.BUTTON_POSITIVE:
				deleteAll();
				dialog.cancel();
				break;
			// негаитвная кнопка
			case Dialog.BUTTON_NEGATIVE:
				dialog.cancel();
				break;}

	
	}
	};


	
	//Обработчик нажатия пунктов меню
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
		// TODO Auto-generated method stub
		Intent intent;
		// определения пункта меню по Id	
		switch (item.getItemId()){
		
		//Обработка нажатия кнопки добавить из меню
		case ADD_MENU_ID:
			Toast.makeText(this, "Добавить", Toast.LENGTH_SHORT).show();
			// переход на Add активити
			
			startActivity(new Intent(this, Add.class));
			
			break;
		case ALLDELETE_MENU_ID:
			showDialog(1);
			break;

		}

			return super.onOptionsItemSelected(item);

		}
	
	//Написание название недели в lbWeek
	public void week(int i){
		if (0==i){
			lbWeek.setTextColor(getResources().getColor(R.color.textColor));
			lbWeek.setText("Понедельник");
			week = "Monday";
			writeOnCreat();
			}
		else if (1==i){
			lbWeek.setText("Вторник");
			week = "Tuesday";
			writeOnCreat();

		}
		else if (2==i){
			lbWeek.setText("Среда");
			week = "Wensday";
			writeOnCreat();

		}
		else if (3==i){
			lbWeek.setText("Четверг");
			week = "Thursday";
			writeOnCreat();

		}
		else if (4==i){
			lbWeek.setTextColor(getResources().getColor(R.color.textColor));
			lbWeek.setText("Пятница");
			week = "Friday";
			writeOnCreat();

		}
		else if (5==i){
			lbWeek.setText("Суббота");
			lbWeek.setTextColor(getResources().getColor(R.color.freeDayColor));
			week = "Saturday";
			writeOnCreat();

		}
		else if (6==i){
			lbWeek.setTextColor(getResources().getColor(R.color.freeDayColor));
			lbWeek.setText("Воскресенье");
			week = "Sunday";
			writeOnCreat();

		}
	}

	// Обработка нажатия кнопки вправо верхней части экрана
	public void rightClick(View v){
		if (6==i)
			i=0;
		
		else i++;
		week(i);
	}
	
	// Обработка нажатия кнопки влево верхней части экрана
	public void leftClick(View v){
		if (0 == i){ 
			i = 6;
			week(i);
		}
		else{
		i--;
		week(i);
		}
	}
	
	//заполнение ListView
	public void writeOnCreat(){
						com.example.dataBase.DBWrite dbWrite = new com.example.dataBase.DBWrite();
						
						// Записываем в ListView Адаптер
						listview = (ListView) findViewById(R.id.lvList);
						listview.setAdapter(dbWrite.write(week, this));

						
//**********************При пустом ListView написание соответствующей надписи****************
				// Проверка записей в базе, если НЕТ то вернется FALSE		
				if (!dbWrite.flagNull) {
						noLesson.setVisibility(2);
							
						if(i == 2) 
									noLesson.setText("У вас нет расписания на Среду, но можно создать! (Меню - Добавить)");
						else if(i == 4)
									noLesson.setText("У вас нет расписания на Пятницу, но можно создать! (Меню - Добавить)");
						else if(i == 5)
									noLesson.setText("У вас нет расписания на Субботу, но можно создать! (Меню - Добавить)");
						else
									noLesson.setText("У вас нет расписания на " + lbWeek.getText() + ", но можно создать! (Меню - Добавить)");

							
								
						}
						else
							noLesson.setVisibility(8); 
			      
		
	
	


	
	
	}
	
	public void deleteAll(){
		com.example.dataBase.DBDelete dbDelete = new com.example.dataBase.DBDelete();
		dbDelete.allDelete(week, this);
		writeOnCreat();
		if(dbDelete.moveToFirst){
				Toast.makeText(this, "Удалено", Toast.LENGTH_SHORT).show();}
			else
				Toast.makeText(this, "Нет записей", Toast.LENGTH_SHORT).show();

	}

	

		}
	      
