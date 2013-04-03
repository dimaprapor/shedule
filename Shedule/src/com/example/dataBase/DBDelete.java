package com.example.dataBase;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.shedule.Edit;

public class DBDelete extends Edit {
	
	public boolean moveToFirst = false;

	// �������� ����� ������ �� ����, �� ����� ����� ������, ��� ���� � ��������
	public void deleteRecord(int numberItem, String week, Context x){
				// ������� ������ � ������������ � ����
				DBHelper dbHelper = new DBHelper(x);
				SQLiteDatabase db = dbHelper.getWritableDatabase();
				//�������� ������
				Cursor cursor = db.query(week, null, null, null, null, null, null);
				//���� � ���� ��� ������� �������� false
				if(cursor.moveToFirst()){
						//��������� �� ��������� ������� ��������� ������
						cursor.moveToPosition(numberItem);
						int id = cursor.getInt(0);
						//������� ������ ��������� �����������
						db.delete(week, "id = " + id, null); 
						db.close();}
					else
						db.close();
	 

		}

	
	public void allDelete(String week, Context x){
		// ������� ������ � ������������ � ����
		DBHelper dbHelper = new DBHelper(x);
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		//�������� ������
		Cursor cursor = db.query(week, null, null, null, null, null, null);
		//���� � ���� ��� ������� �������� false
		if(cursor.moveToFirst()){
			db.delete(week, null, null);
			db.close();
			moveToFirst = true;

		}
		else
			moveToFirst = false;

		
	}


}
