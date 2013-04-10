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

	// ��������� ID ������� ����
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
		

	
	
	
	//����� �������� Laout edit.xml
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.edit);
	}
	
	
	protected void onResume(){
		super.onResume();
		lbWeek = (TextView) findViewById(R.id.tvDayWeek);
		noLesson = (TextView) findViewById(R.id.tvDayNoLesson);
		// ���������� List View ��� �������� �����
		listview = (ListView) findViewById(R.id.lvList);
		week(i);
		writeOnCreat();
		registerForContextMenu(listview);

	}
	
		
	//�������� ����
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		menu.add(1, ADD_MENU_ID, 0, "��������");
		menu.add(1, ALLDELETE_MENU_ID, 0, "������� ���");
		return super.onCreateOptionsMenu(menu);
		}	
	
	//�������� ������������ ����
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
	super.onCreateContextMenu(menu, v, menuInfo);
		menu.add(0, 1, 0, "������� ������");
		menu.add(0, 2, 0, "�������������");

	}
	
	// ��������� ������� �� ������ ������������ ����
	@Override
	public boolean onContextItemSelected(MenuItem item) {

		//������� ������
		if (item.getItemId() == 1) {
			// �������� ���� � ������ ������
			AdapterContextMenuInfo acmi = (AdapterContextMenuInfo) item.getMenuInfo();
			com.example.dataBase.DBDelete delete = new com.example.dataBase.DBDelete();
			delete.deleteRecord(acmi.position, week, this);
			writeOnCreat();
			Toast.makeText(this, "�������", Toast.LENGTH_SHORT).show();
		}
		// �������� ������
		else {
			AdapterContextMenuInfo acmi = (AdapterContextMenuInfo) item.getMenuInfo();
			correctId = acmi.position;
			Toast.makeText(this, "��������", Toast.LENGTH_SHORT).show();
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
		adb.setTitle("��������");
		adb.setMessage("������� ��� ������?");
		adb.setPositiveButton("��", myClickListener);
		adb.setNegativeButton("���", myClickListener);
		return adb.create();}
		
		return super.onCreateDialog(idDialogDeleteAll);
		}
	
	OnClickListener myClickListener = new OnClickListener() {
	
		
	public void onClick(DialogInterface dialog, int which) {
	if(idDialogDeleteAll == 1)
		
			switch (which) {
			// ������������� ������
			case Dialog.BUTTON_POSITIVE:
				deleteAll();
				dialog.cancel();
				break;
			// ���������� ������
			case Dialog.BUTTON_NEGATIVE:
				dialog.cancel();
				break;}

	
	}
	};


	
	//���������� ������� ������� ����
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
		// TODO Auto-generated method stub
		Intent intent;
		// ����������� ������ ���� �� Id	
		switch (item.getItemId()){
		
		//��������� ������� ������ �������� �� ����
		case ADD_MENU_ID:
			Toast.makeText(this, "��������", Toast.LENGTH_SHORT).show();
			// ������� �� Add ��������
			
			startActivity(new Intent(this, Add.class));
			
			break;
		case ALLDELETE_MENU_ID:
			showDialog(1);
			break;

		}

			return super.onOptionsItemSelected(item);

		}
	
	//��������� �������� ������ � lbWeek
	public void week(int i){
		if (0==i){
			lbWeek.setTextColor(getResources().getColor(R.color.textColor));
			lbWeek.setText("�����������");
			week = "Monday";
			writeOnCreat();
			}
		else if (1==i){
			lbWeek.setText("�������");
			week = "Tuesday";
			writeOnCreat();

		}
		else if (2==i){
			lbWeek.setText("�����");
			week = "Wensday";
			writeOnCreat();

		}
		else if (3==i){
			lbWeek.setText("�������");
			week = "Thursday";
			writeOnCreat();

		}
		else if (4==i){
			lbWeek.setTextColor(getResources().getColor(R.color.textColor));
			lbWeek.setText("�������");
			week = "Friday";
			writeOnCreat();

		}
		else if (5==i){
			lbWeek.setText("�������");
			lbWeek.setTextColor(getResources().getColor(R.color.freeDayColor));
			week = "Saturday";
			writeOnCreat();

		}
		else if (6==i){
			lbWeek.setTextColor(getResources().getColor(R.color.freeDayColor));
			lbWeek.setText("�����������");
			week = "Sunday";
			writeOnCreat();

		}
	}

	// ��������� ������� ������ ������ ������� ����� ������
	public void rightClick(View v){
		if (6==i)
			i=0;
		
		else i++;
		week(i);
	}
	
	// ��������� ������� ������ ����� ������� ����� ������
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
	
	//���������� ListView
	public void writeOnCreat(){
						com.example.dataBase.DBWrite dbWrite = new com.example.dataBase.DBWrite();
						
						// ���������� � ListView �������
						listview = (ListView) findViewById(R.id.lvList);
						listview.setAdapter(dbWrite.write(week, this));

						
//**********************��� ������ ListView ��������� ��������������� �������****************
				// �������� ������� � ����, ���� ��� �� �������� FALSE		
				if (!dbWrite.flagNull) {
						noLesson.setVisibility(2);
							
						if(i == 2) 
									noLesson.setText("� ��� ��� ���������� �� �����, �� ����� �������! (���� - ��������)");
						else if(i == 4)
									noLesson.setText("� ��� ��� ���������� �� �������, �� ����� �������! (���� - ��������)");
						else if(i == 5)
									noLesson.setText("� ��� ��� ���������� �� �������, �� ����� �������! (���� - ��������)");
						else
									noLesson.setText("� ��� ��� ���������� �� " + lbWeek.getText() + ", �� ����� �������! (���� - ��������)");

							
								
						}
						else
							noLesson.setVisibility(8); 
			      
		
	
	


	
	
	}
	
	public void deleteAll(){
		com.example.dataBase.DBDelete dbDelete = new com.example.dataBase.DBDelete();
		dbDelete.allDelete(week, this);
		writeOnCreat();
		if(dbDelete.moveToFirst){
				Toast.makeText(this, "�������", Toast.LENGTH_SHORT).show();}
			else
				Toast.makeText(this, "��� �������", Toast.LENGTH_SHORT).show();

	}

	

		}
	      
