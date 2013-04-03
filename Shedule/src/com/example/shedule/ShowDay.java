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

	// ��������� ID ������� ����
		final int CANCEL_MENU_ID = 1;
		
	
		TextView noLesson;
		ImageView imageWeek;
		public static String week = "Monday";
		ListView listview;

		boolean flagUpdate = false;
		static int i = 0;
		int j = 0;
		

	
	
	
	//����� �������� Laout edit.xml
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.show_day);
		imageWeek = (ImageView) findViewById(R.id.imageDayWeek);
		noLesson = (TextView) findViewById(R.id.tvDayNoLesson);
		// ���������� List View ��� �������� �����
		listview = (ListView) findViewById(R.id.listViewDay);
		Calendar c = Calendar.getInstance();
		Log.d(TAG, " day = "+ c.get(c.DAY_OF_WEEK));
		i = c.get(c.DAY_OF_WEEK);
		if(i==1)
			i=7;
		else if(i==7)
			i=8;

		week(i);
	}
		
	//�������� ����
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		menu.add(1, CANCEL_MENU_ID, 0, "�����");
		return super.onCreateOptionsMenu(menu);
		}	
	
	//���������� ������� ������� ����
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
		// TODO Auto-generated method stub
		Intent intent;
		// ����������� ������ ���� �� Id	
		switch (item.getItemId()){
		
		//��������� ������� ������ �������� �� ����
		case CANCEL_MENU_ID:
			finish();
			intent = new Intent(this, Home.class);
			startActivity(intent);
			break;
		}
			return super.onOptionsItemSelected(item);
		}
	
	//��������� �������� ������ � lbWeek
	public void week(int i){
		if (7==i){
			imageWeek.setImageResource(R.drawable.show_day_sixe);
			week = "Saturday";
			writeOnCreat();

		}

		else if (2==i){
			imageWeek.setImageResource(R.drawable.show_day_one);
			week = "Monday";
			writeOnCreat();
			}
		else if (3==i){
			imageWeek.setImageResource(R.drawable.show_day_two);
			week = "Tuesday";
			writeOnCreat();

		}
		else if (4==i){
			imageWeek.setImageResource(R.drawable.show_day_three);
			week = "Wensday";
			writeOnCreat();

		}
		else if (5==i){
			imageWeek.setImageResource(R.drawable.show_day_four);
			week = "Thursday";
			writeOnCreat();

		}
		else if (6==i){
			imageWeek.setImageResource(R.drawable.show_day_five);
			week = "Friday";
			writeOnCreat();

		}
		else if (8==i){
			imageWeek.setImageResource(R.drawable.show_day_seven);
			week = "Sunday";
			writeOnCreat();

		}
	}

	// ��������� ������� ������ ������ ������� ����� ������
	public void rightClick(View v){
		if (8==i)
			i=2;
		
		else i++;
		week(i);
	}
	
	// ��������� ������� ������ ����� ������� ����� ������
	public void leftClick(View v){
		if (2 == i){ 
			i = 8;
			week(i);
		}
		else{
		i--;
		week(i);
		}
	}
	
	//���������� ListView
	public void writeOnCreat(){
						com.example.dataBase.DBWrite dbWrite = new com.example.dataBase.DBWrite();
						
						// ���������� � ListView �������
						listview = (ListView) findViewById(R.id.listViewDay);
						listview.setAdapter(dbWrite.writeDay(week, this));

						
//**********************��� ������ ListView ��������� ��������������� �������****************
				// �������� ������� � ����, ���� ��� �� �������� FALSE		
				if (!dbWrite.flagNull) {
						noLesson.setVisibility(2);
						switch(i){	
							case 0:{
								noLesson.setText("� ��� ��� ���������� �� �����������, �� ����� �������! (������� ���� - �������/��������)");
								break;
								}
							case 1:{
								noLesson.setText("� ��� ��� ���������� �� �������, �� ����� �������! (������� ���� - �������/��������)");
								break;
								}
							case 2:{
								noLesson.setText("� ��� ��� ���������� �� �����, �� ����� �������! (������� ���� - �������/��������)");
								break;
								}
							case 3:{
								noLesson.setText("� ��� ��� ���������� �� �������, �� ����� �������! (������� ���� - �������/��������)");
								break;
								}
							case 4:{
								noLesson.setText("� ��� ��� ���������� �� �������, �� ����� �������! (������� ���� - �������/��������)");
								break;
								}
							case 5:{
								noLesson.setText("� ��� ��� ���������� �� �������, �� ����� �������! (������� ���� - �������/��������)");
								break;
								}
							case 6:{
								noLesson.setText("� ��� ��� ���������� �� �����������, �� ����� �������! (������� ���� - �������/��������)");
								break;
								}
						
						}
						}
						
				else
					noLesson.setVisibility(8); 
	}
	

    
    
}