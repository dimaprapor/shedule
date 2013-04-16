package com.example.shedule;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;

import com.example.dataBase.DBWrite;

public class Home extends Activity {
	

	
	private static final String TAG = "myLogs";
	
	final int DIALOG_EXIT = 1;
	final int DIALOG_ABOUT = 2;
	int id;
	ImageView im;
	Vibrator vibro;
	Setting setting;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
//********************************************ОБРАБОТЧИК EXEPTION***********
		//     	Thread.setDefaultUncaughtExceptionHandler(new CustomExceptionHandler(Environment.getExternalStorageDirectory().getPath() + "/AquaReaderLog", "http://aquagomel.ru/ISSUE/upload.php"));
//*************************************************************     	
		
    	super.onCreate(savedInstanceState);
		setContentView(R.layout.home_menu);
		vibro = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
		Setting setting = new Setting();
		setting.defaultSetting(this);
		setting = new Setting();

	}

	protected void onResume(){
		super.onResume();
		com.example.dataBase.DBWrite dbWrite = new DBWrite();
		im = (ImageView) findViewById(R.id.settingVibration);
		if(dbWrite.info(this))
			im.setImageResource(R.drawable.icon_home_edit);
			else
				im.setImageResource(R.drawable.icon_home_new);
		dbWrite.infoLine(this);
		
	}

	protected Dialog onCreateDialog(int id) {
		if (id == DIALOG_EXIT) {
		AlertDialog.Builder adb = new AlertDialog.Builder(this);
		adb.setIcon(android.R.drawable.ic_dialog_info);
		adb.setTitle("Выход");
		adb.setMessage("Вы уверены?");
		adb.setPositiveButton("Да", myClickListener);
		adb.setNegativeButton("Нет", myClickListener);
		return adb.create();}
		else if (id == DIALOG_ABOUT) {
			AlertDialog.Builder adb = new AlertDialog.Builder(this);
			adb.setIcon(android.R.drawable.ic_dialog_info);
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
	
	public void weekClick(View v){
//		setting.vibratorClick();
		setting.volumeClick(this, 1);
		Intent intent = new Intent(this, ShowWeek.class);
		startActivity(intent);
	}
	
	public void dayClick(View v){
//		setting.vibratorClick();
		setting.volumeClick(this, 1);
		Intent intent = new Intent(this, ShowDay.class);
		startActivity(intent);
	}
	
	public void settingClick(View v){
//		setting.vibratorClick();
		setting.volumeClick(this, 1);
		Intent intent = new Intent(this, Setting.class);
		startActivity(intent);
	}
	
	public void editClick(View v){
//		setting.vibratorClick();
		setting.volumeClick(this, 1);
		Intent intent = new Intent(this, Edit.class);
		startActivity(intent);
	}
	
	public void aboutClick(View v){
//		setting.vibratorClick();
		setting.volumeClick(this, 1);
		showDialog(2);	
		id = 2;
	}
	
	public void closeClick(View v){
//		setting.vibratorClick();
		setting.volumeClick(this, 1);
		showDialog(1);
		id = 1;
	}

	OnClickListener myClickListener = new OnClickListener() {
	public void onClick(DialogInterface dialog, int which) {
	if(id == DIALOG_EXIT)
			switch (which) {
			case Dialog.BUTTON_POSITIVE:
				System.exit(2);
				break;
			case Dialog.BUTTON_NEGATIVE:
				dialog.cancel();
				break;}
	else if(id == DIALOG_ABOUT)
			switch (which) {
			case Dialog.BUTTON_POSITIVE:
				dialog.cancel();
			break;}}};


}
