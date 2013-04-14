package com.example.shedule;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.dataBase.DBWrite;


public class ShowDialogInfoItem extends Activity{
private static final String TAG = "myLogs";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dialog_info_show_week);

		TextView ed1 = (TextView) findViewById(R.id.infoTV1);
		TextView ed2 = (TextView) findViewById(R.id.infoTV2);
		TextView tv1 = (TextView) findViewById(R.id.infoTime1);
		TextView tv2 = (TextView) findViewById(R.id.infoTime2);
		com.example.dataBase.DBWrite dbWrite = new DBWrite();
		ShowWeek showWeek = new ShowWeek();
		if(dbWrite.writeCorrect(this, showWeek.week, showWeek.positionVertical)){
			Log.d(TAG, "День - "+showWeek.week+"; Номер записи - "+showWeek.positionVertical);
		ed1.setText(dbWrite.e1);
		ed2.setText(dbWrite.e2);
		tv1.setText(dbWrite.t1);
		tv2.setText(dbWrite.t2);
		}
		else 
			finish();

	}
	
	public void closeClick(View v){
		finish();
	}

}
