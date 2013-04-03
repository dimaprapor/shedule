package com.example.shedule;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;

public class ShowWeek extends Activity {


/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.show_week);
		com.example.dataBase.DBWrite dbWrite = new com.example.dataBase.DBWrite();
		GridView gvMain = (GridView) findViewById(R.id.gridView1);
		gvMain.setAdapter(dbWrite.writeWeek(this));

		GridView gvNumber = (GridView) findViewById(R.id.gridView2);
		gvNumber.setAdapter(dbWrite.writeNumberInGrid(this));
		adjustGridView(); 
	}
	
	//Создание меню
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		menu.add(1, 0, 0, "Назад");

		return super.onCreateOptionsMenu(menu);
		}	
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
		// TODO Auto-generated method stub
		Intent intent;
		// определения пункта меню по Id	
		switch (item.getItemId()){
		
		//Обработка нажатия кнопки добавить из меню
		case 0:
			
			intent = new Intent(this, Home.class);
			startActivity(intent);
			finish();
			break;
		}

			return super.onOptionsItemSelected(item);

		}


	private void adjustGridView() {
	}

}