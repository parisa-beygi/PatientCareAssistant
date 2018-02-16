package com.designpattern.finalproject.pca.pages;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.transition.Scene;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import com.designpattern.finalproject.pca.AlarmReceiver;
import com.designpattern.finalproject.pca.R;
import com.designpattern.finalproject.pca.Schedule;
import com.designpattern.finalproject.pca.command.FindScheduleCommand;

import java.util.Calendar;

/**
 * Created by Asus-pc on 2018/02/08.
 */

public class TurnOffAlarmPage extends AppCompatActivity {
    Schedule schedule;
    TextView textViewScheduleText;

    AlarmManager alarmManager;
    TextView textViewUpdateText;
    Context context;
    Intent intent;
    PendingIntent pendingIntent;
    int pendingIntentId = 0;


    private void setUpdateTextView(String s) {
        textViewUpdateText.setText(s);
    }

    private void setScheduleTextView(String s) {
        textViewScheduleText.setText(s);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_turnoffalarm_page);

        this.context = this;


        //__________________ page UI___________________________
        //initialize our schedule text view
        textViewScheduleText = (TextView) findViewById(R.id.TurnOffAlarmPage_textView_scheduleText);


        //initialize our alarm update box
        textViewUpdateText = (TextView) findViewById(R.id.TurnOffAlarmPage_textView_updateText);

        //initialize stop button
        Button buttonAlarmOff = (Button) findViewById(R.id.TurnOffAlarmPage_button_alarm_off);

        //__________________end of page UI___________________________


        //initialize our alarm manager
        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);



        Intent intent2 = getIntent();
        //fetch the pendingIntentId value
        pendingIntentId=intent2.getIntExtra("pendingIntentId", 0);

        //_________read schedule from database______________
        SQLiteDatabase database=openOrCreateDatabase("pcaDatabase",MODE_PRIVATE,null);
        FindScheduleCommand findScheduleCommand=new FindScheduleCommand(pendingIntentId,database,TurnOffAlarmPage.this);
        findScheduleCommand.execute();
        schedule=findScheduleCommand.getSchedule();
        database.close();
        //_________end of reading schedule from database___________
        setScheduleTextView(schedule.toString());

        Log.e("im in the turn off alarm page","yeah");
        Log.e("im in the "+ pendingIntentId+" turn off alarm page","yeah");




        intent = new Intent(context, AlarmReceiver.class);

        //tell that you pressed the "alarm off button"
        intent.putExtra("extra", "alarm off");

        intent.putExtra("pendingIntentId", pendingIntentId);

        pendingIntent = PendingIntent.getBroadcast(TurnOffAlarmPage.this, pendingIntentId, intent, PendingIntent.FLAG_UPDATE_CURRENT);


        //method that changes the update text view
        setUpdateTextView("click to stop "+pendingIntentId+" alarm");


        //create an on click listener to stop the alarm
        buttonAlarmOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                //method that changes the update text view
                setUpdateTextView(" alarm turned off ");


                //be carefull negar this cancell your last pending intent
                // that set on onclicked listener for set alarm button
                //in your app its better to create page for cancelling schadules
                alarmManager.cancel(pendingIntent);



                //stop the alarm
                sendBroadcast(intent); //immediately start intent

            }
        });


    }
}