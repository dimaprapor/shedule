package com.example.shedule;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface.OnClickListener;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.dataBase.DBHelper;

public class Setting extends Activity {

	public static String lineOfWeek, vibrator, volume;

	private static final String TAG = "myLogs";
	boolean radioLineClick = true, nadClick = false, vibrationFlag = true,
			volumeFlag = true;
	ImageView radioLine, im2;
	OnClickListener radioListener;
	String c;
	RadioGroup radiogroup;
	ImageView bt1, bt2, vibra, vol;
	TextView tv2;
	Vibrator vibro;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.setting);
		
		
		radioLine = (ImageView) findViewById(R.id.imageRadioLine);
		im2 = (ImageView) findViewById(R.id.settingImage2);
		tv2 = (TextView) findViewById(R.id.settingText2);

		vol = (ImageView) findViewById(R.id.settingVolume);
		vibra = (ImageView) findViewById(R.id.settingVibration);
		vibro = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

	}

	protected void onStart() {
		super.onStart();
		defaultSetting(this);
		if (lineOfWeek.length() == 10) {
			radioLine.setImageResource(R.drawable.radio_button_true);
			im2.setImageResource(R.drawable.setting_radio_nad);
			radioLineClick = false;
			im2.setVisibility(2);
			tv2.setVisibility(2);
			im2.setImageResource(R.drawable.setting_radio_nad);
			nadClick = false;
		} else if (lineOfWeek.length() == 11) {
			radioLine.setImageResource(R.drawable.radio_button_true);
			im2.setImageResource(R.drawable.setting_radio_nad);
			radioLineClick = false;
			im2.setVisibility(2);
			tv2.setVisibility(2);
			im2.setImageResource(R.drawable.setting_radio_pod);
			nadClick = true;
		}
		else if (lineOfWeek.length() == 4) {
			radioLine.setImageResource(R.drawable.radio_button_false);
			radioLineClick = true;
			im2.setVisibility(8);
			tv2.setVisibility(8);
		}
		if(volume.length() == 2){
			vol.setImageResource(R.drawable.setting_on);
			volumeFlag = false;
		}
		else{
			vol.setImageResource(R.drawable.setting_off);
			volumeFlag = true;
		}

		if(vibrator.length() == 2){
			vibra.setImageResource(R.drawable.setting_on);
			vibro.vibrate(70);
			vibrationFlag = false;
		}
		else{
			vibra.setImageResource(R.drawable.setting_off);
			vibrationFlag = true;
		}
	}

	public void readDataBase(String line, String vol, String vib) {
		com.example.dataBase.DBHelper dbHelper = new DBHelper(this);
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		ContentValues cv = new ContentValues();
		
			cv.put("lineOfWeek", line);
			cv.put("volume", vol);
			cv.put("vibrator", vib);
			
		db.update("Setting", cv, "id = 1", null);
		dbHelper.close();
		Log.d("myLogs", "|"+lineOfWeek+"|"+volume+"|"+vibrator+"|");
	}

	public void nadClick(View v) {
		if (nadClick) {
			im2.setImageResource(R.drawable.setting_radio_nad);
			nadClick = false;
			lineOfWeek = "Над чертой";
			readDataBase(lineOfWeek, volume, vibrator);
		} else {
			im2.setImageResource(R.drawable.setting_radio_pod);
			nadClick = true;
			lineOfWeek = "Под чертой ";
			readDataBase(lineOfWeek, volume, vibrator);
		}
	}

	public void radioLineClick(View v) {
		if (radioLineClick) {
			radioLine.setImageResource(R.drawable.radio_button_true);
			lineOfWeek = "Над чертой";
			readDataBase(lineOfWeek, volume, vibrator);
			im2.setImageResource(R.drawable.setting_radio_nad);
			radioLineClick = false;
			im2.setVisibility(2);
			tv2.setVisibility(2);
		} else {
			radioLine.setImageResource(R.drawable.radio_button_false);
			radioLineClick = true;
			im2.setVisibility(8);
			tv2.setVisibility(8);
			lineOfWeek = "null";
			readDataBase(lineOfWeek, volume, vibrator);
		}

	}

	public void volumeOn(View v) {
		if (volumeFlag) {
			vol.setImageResource(R.drawable.setting_on);
			volumeFlag = false;
			volume = "on";
			readDataBase(lineOfWeek, volume, vibrator);
			// SoundPool soundPool = new SoundPool(4, 3, 0);
			// int explosionSound = soundPool.load(this, R., 1);
			// soundPool.play(explosionSound, 100, 100, 1, 0, 1);

		} else {
			vol.setImageResource(R.drawable.setting_off);
			volumeFlag = true;
			volume = "off";
			readDataBase(lineOfWeek, volume, vibrator);
		}

	}

	public void vibrationOn(View v) {
		if (vibrationFlag) {
			vibra.setImageResource(R.drawable.setting_on);
			vibro.vibrate(70);
			vibrationFlag = false;
			vibrator = "on";
			readDataBase(lineOfWeek, volume, vibrator);
		} else {
			vibra.setImageResource(R.drawable.setting_off);
			vibrationFlag = true;
			vibrator = "off";
			readDataBase(lineOfWeek, volume, vibrator);

		}
	}

	
	
	public void defaultSetting(Context x) {
		// Записываю в базу Стандартные настройки, если она пустая
		// Суть в том, что настройки записываются один раз, а потом изменяются
		// на вкладке Настройки
		// ******************************************************************************************
		com.example.dataBase.DBHelper dbHelper = new DBHelper(x);
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		Cursor cursor = db.query("Setting", null, null, null, null, null, null);
		// Черта - Null (отсутствие)
		if (!cursor.moveToFirst()) {
			ContentValues cv = new ContentValues();
			cv.put("lineOfWeek", "null");
			cv.put("volume", "off");
			cv.put("vibrator", "off");
			db.insert("Setting", null, cv);
			dbHelper.close();
			lineOfWeek = "null";
			volume = "off";
			vibrator = "off";
			Log.d("myLogs", "Default: |"+lineOfWeek+"|"+volume+"|"+vibrator+"|");
		} else {
			cursor.moveToFirst();
			int line = cursor.getColumnIndex("lineOfWeek");
			int volu = cursor.getColumnIndex("volume");
			int vibr = cursor.getColumnIndex("vibrator");
			lineOfWeek = cursor.getString(line);
			volume = cursor.getString(volu);
			vibrator = cursor.getString(vibr);
			Log.d("myLogs", "Default else:|"+lineOfWeek+"|"+volume+"|"+vibrator+"|");
			dbHelper.close();
		}
		// ******************************************************************************************

	}

	public void vibratorClick(Context x){
		defaultSetting(x);
		if(vibrator.length()==2){
			vibro.vibrate(70);
		}
	}
	
	public void volumeClick(Context x,int number){
		defaultSetting(x);
		if(volume.length()==2){
		switch(number){
        case 1:{
    		MediaPlayer mp = MediaPlayer.create(x, R.raw.click);   
            mp.start();}
        	break;
        case 2:{
    		MediaPlayer mp = MediaPlayer.create(x, R.raw.error);   
            mp.start();}
            break;
		}
		}
	}
}