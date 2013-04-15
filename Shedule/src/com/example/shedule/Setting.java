package com.example.shedule;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface.OnClickListener;
import android.database.sqlite.SQLiteDatabase;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.Vibrator;
import android.provider.Settings.System;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.dataBase.DBHelper;


public class Setting extends Activity {
	
	private static final String TAG = "myLogs";
	boolean radioLineClick = true, nadClick = false, vibration = true, volume = true;
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
	
	protected void onStart(){
		super.onStart();
		Home home = new Home();
		String line = home.lineOfWeek;

		if(line.length() == 10){
			radioLine.setImageResource(R.drawable.radio_button_true);
			im2.setImageResource(R.drawable.setting_radio_nad);
			radioLineClick = false;
			im2.setVisibility(2);
			tv2.setVisibility(2);
			im2.setImageResource(R.drawable.setting_radio_nad);
			nadClick = false;
			}
		else if(line.length() == 11){
			radioLine.setImageResource(R.drawable.radio_button_true);
			im2.setImageResource(R.drawable.setting_radio_nad);
			radioLineClick = false;
			im2.setVisibility(2);
			tv2.setVisibility(2);
			im2.setImageResource(R.drawable.setting_radio_pod);
			nadClick = true;
			}
			
			else if(line.length() == 1){
				radioLine.setImageResource(R.drawable.radio_button_false);
				radioLineClick = true;
				im2.setVisibility(8);
				tv2.setVisibility(8);
			} 


	}

	public void readLine(String c){
				com.example.dataBase.DBHelper dbHelper = new DBHelper(this);
				SQLiteDatabase db = dbHelper.getWritableDatabase();
						ContentValues cv = new ContentValues();
						cv.put("lineOfWeek", c);
						db.update("Setting", cv, "id = 1", null);
						dbHelper.close();
						Log.d(TAG, " " + cv);
		}
	
	public void nadClick(View v){
		if(nadClick){
			im2.setImageResource(R.drawable.setting_radio_nad);
			nadClick = false;
			readLine("Над чертой");
			}
		else{
			im2.setImageResource(R.drawable.setting_radio_pod);
			nadClick = true;
			readLine("Под чертой ");
		}
	}
	
	public void radioLineClick(View v){
		if(radioLineClick){
			radioLine.setImageResource(R.drawable.radio_button_true);
			readLine("Над чертой");
			im2.setImageResource(R.drawable.setting_radio_nad);
			radioLineClick = false;
			im2.setVisibility(2);
			tv2.setVisibility(2);}
		else{
			radioLine.setImageResource(R.drawable.radio_button_false);
			radioLineClick = true;
			im2.setVisibility(8);
			tv2.setVisibility(8);
			readLine(" ");}
			
	}
	
	public void vibrationOn(View v){
		if(vibration){
			vibra.setImageResource(R.drawable.setting_on);
			vibro.vibrate(70);
			vibration = false;
		}
		else{
			vibra.setImageResource(R.drawable.setting_off);
			vibration = true;
		}
	}
	
	public void volumeOn(View v){
		if(volume){
			vol.setImageResource(R.drawable.setting_on);
			volume = false;

//			SoundPool soundPool = new SoundPool(4, 3, 0);
//			int explosionSound = soundPool.load(this, R., 1);
//			soundPool.play(explosionSound, 100, 100, 1, 0, 1);
			
		}
		else{
			vol.setImageResource(R.drawable.setting_off);
			volume = true;
			
		}
		
	}
	

	
	

}