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

		TextView tv1 = (TextView) findViewById(R.id.infoTv1);
		TextView tv2 = (TextView) findViewById(R.id.infoTv2);
		TextView tv3 = (TextView) findViewById(R.id.infoTv3);
		TextView tv4 = (TextView) findViewById(R.id.infoTv4);

		com.example.dataBase.DBWrite dbWrite = new DBWrite();
		ShowWeek showWeek = new ShowWeek();
		if(dbWrite.writeCorrect(this, showWeek.week, showWeek.positionVertical)){
			Log.d(TAG, "День - "+showWeek.week+"; Номер записи - "+showWeek.positionVertical);
		tv1.setText(dbWrite.e1);
		tv2.setText(dbWrite.e2);
		tv3.setText(dbWrite.t1);
		tv4.setText(dbWrite.t2);
		}
		else 
			finish();

	}
	
	public void closeClick(View v){
		finish();
	}

}
