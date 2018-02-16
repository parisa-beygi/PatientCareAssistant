package com.designpattern.finalproject.pca.pages;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;


import com.designpattern.finalproject.pca.AlarmReceiver;
import com.designpattern.finalproject.pca.R;
import com.designpattern.finalproject.pca.Schedule;
import com.designpattern.finalproject.pca.Utility;
import com.designpattern.finalproject.pca.command.AddScheduleCommand;
import com.designpattern.finalproject.pca.command.FindPatientIDCommand;

import java.util.Calendar;

/**
 * Created by Asus-pc on 2018/02/03.
 */

public class AddScedulePage extends AppCompatActivity {
    //drug type checkboxes
    private CheckBox checkBoxDrugTypeTablet,checkBoxDrugTypeSyrup,checkBoxDrugTypeInhaler,checkBoxDrugTypeInjection;

    //drug name edit text
    private EditText editTextDrugName;

    //drug dose edit text
    private EditText editTextDrugDose;

    //food relation checkboxes
    private CheckBox checkBoxFoodRelationBefore, checkBoxFoodRelationAfter, checkBoxFoodRelationNotRelated;


    private LinearLayout linearLayoutDrugName;
    private LinearLayout linearLayoutDrugDose;
    private LinearLayout linearLayoutDrugFoodRelation;

    String drugType="talet";
    String foodRelation="not related";
    String drugName="__";
    String drugDose="__";


    //to make our alarm manager
    Button buttonAlarmOn;
    AlarmManager alarmManager;
    TimePicker alarm_timepicker;
    DatePicker alarm_datepicker;
    TextView textViewUpdateText;
    Context context;
    Intent intent;
    PendingIntent pendingIntent;
    int pendingIntentId=0;

    private void setDrugName() {
        String name= editTextDrugName.getText().toString();
        if ((name.equals(""))|| (name.trim().length() == 0)) {
            drugName="__";
        }else{
            drugName=name;
        }
        }

    private void setDrugDose() {
        String dose= editTextDrugDose.getText().toString();
        if ((dose.equals(""))|| (dose.trim().length() == 0)) {
            drugDose="__";
        }else{
            drugDose=dose;
        }
    }

    public void makeRestViewsInvisible(){
        //_____invisible elements in the page insted of check boxes for drug type________
        linearLayoutDrugName.setVisibility(View.GONE);
        linearLayoutDrugDose.setVisibility(View.GONE);
        linearLayoutDrugFoodRelation.setVisibility(View.GONE);

        alarm_datepicker.setVisibility(View.GONE);
        alarm_timepicker.setVisibility(View.GONE);
        textViewUpdateText.setVisibility(View.GONE);
        buttonAlarmOn.setVisibility(View.GONE);
    }

    public void makeRestViewsVisible(){
        //_____visible elements in the page insted of check boxes for drug type________
        linearLayoutDrugName.setVisibility(View.VISIBLE);
        linearLayoutDrugDose.setVisibility(View.VISIBLE);
        linearLayoutDrugFoodRelation.setVisibility(View.VISIBLE);

        alarm_datepicker.setVisibility(View.VISIBLE);
        alarm_timepicker.setVisibility(View.VISIBLE);
        textViewUpdateText.setVisibility(View.VISIBLE);
        buttonAlarmOn.setVisibility(View.VISIBLE);
    }

    public void setDrugType(String drugType) {
        this.drugType = drugType;
    }

    public void setFoodRelation(String foodRelation) {
        this.foodRelation = foodRelation;
    }

    private void setUpdateTextView(String s) {
        textViewUpdateText.setText(s);
    }

    public void drugTypeCheckboxClicked(View view) {
        // Is the view now checked?
        boolean checked = ((CheckBox) view).isChecked();

        // Check which checkbox was clicked
        switch(view.getId()) {

            case R.id.AddSchedulePage_checkBox_drugType_tablet:
                if (checked) {
                    checkBoxDrugTypeSyrup.setChecked(false);
                    checkBoxDrugTypeInhaler.setChecked(false);
                    checkBoxDrugTypeInjection.setChecked(false);
                    setDrugType("tablet");
                    makeRestViewsVisible();
                }
                else{
                    setDrugType("tablet");
                    makeRestViewsInvisible();
                }
                break;

            case R.id.AddSchedulePage_checkBox_drugType_syrup:
                if (checked) {
                    checkBoxDrugTypeTablet.setChecked(false);
                    checkBoxDrugTypeInhaler.setChecked(false);
                    checkBoxDrugTypeInjection.setChecked(false);
                    setDrugType("syrup");
                    makeRestViewsVisible();
                }
                else {
                    setDrugType("tablet");
                    makeRestViewsInvisible();
                }
                break;

            case R.id.AddSchedulePage_checkBox_drugType_inhaler:
                if (checked) {
                    checkBoxDrugTypeTablet.setChecked(false);
                    checkBoxDrugTypeSyrup.setChecked(false);
                    checkBoxDrugTypeInjection.setChecked(false);
                    setDrugType("inhaler");
                    makeRestViewsVisible();
                }
                else {
                    setDrugType("tablet");
                    makeRestViewsInvisible();
                }
                break;

            case R.id.AddSchedulePage_checkBox_drugType_injection:
                if (checked) {
                    checkBoxDrugTypeTablet.setChecked(false);
                    checkBoxDrugTypeSyrup.setChecked(false);
                    checkBoxDrugTypeInhaler.setChecked(false);
                    setDrugType("injection");
                    makeRestViewsVisible();
                }
                else {
                    setDrugType("tablet");
                    makeRestViewsInvisible();
                }
                break;

        }
        Log.e("drug type is:",drugType);



    }

    public void foodRelationCheckboxClicked(View view) {
        // Is the view now checked?
        boolean checked = ((CheckBox) view).isChecked();

        // Check which checkbox was clicked
        switch(view.getId()) {

            case R.id.AddSchedulePage_checkBox_foodRelation_before:
                if (checked) {
                    checkBoxFoodRelationAfter.setChecked(false);
                    checkBoxFoodRelationNotRelated.setChecked(false);
                    setFoodRelation("before");
                }
                else{
                    setFoodRelation("not related");
                }
                break;

            case R.id.AddSchedulePage_checkBox_foodRelation_after:
                if (checked) {
                    checkBoxFoodRelationNotRelated.setChecked(false);
                    checkBoxFoodRelationBefore.setChecked(false);
                    setFoodRelation("after");
                }
            else {
                    setFoodRelation("not related");
                }
                break;
            case R.id.AddSchedulePage_checkBox_foodRelation_notRelated:
                if (checked) {
                    checkBoxFoodRelationAfter.setChecked(false);
                    checkBoxFoodRelationBefore.setChecked(false);
                    setFoodRelation("not related");
                }
                else {
                    setFoodRelation("not related");
                }
                break;
        }
        Log.e("food relation is:",foodRelation);

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addschedule_page);

        this.context=this;


        //__________________page UI:___________________________
        //initialize our alarm update box
        textViewUpdateText=(TextView)findViewById(R.id.AddSchedulePage_textView_updateText);


        //initialize start button
        buttonAlarmOn=(Button)findViewById(R.id.AddSchedulePage_button_alarm_on);


        //initialize our timepicker
        alarm_timepicker = (TimePicker) findViewById(R.id.AddSchedulePage_timePicker);
        //initialize our datepicker
        alarm_datepicker = (DatePicker) findViewById(R.id.AddSchedulePage_DatePicker);

        //initialize food relation checkboxes
        checkBoxFoodRelationBefore=(CheckBox)findViewById(R.id.AddSchedulePage_checkBox_foodRelation_before);
        checkBoxFoodRelationAfter=(CheckBox)findViewById(R.id.AddSchedulePage_checkBox_foodRelation_after);
        checkBoxFoodRelationNotRelated=(CheckBox)findViewById(R.id.AddSchedulePage_checkBox_foodRelation_notRelated);
        checkBoxFoodRelationNotRelated.setChecked(true);


        //initialize drug type checkboxes
        checkBoxDrugTypeTablet=(CheckBox)findViewById(R.id.AddSchedulePage_checkBox_drugType_tablet);
        checkBoxDrugTypeSyrup=(CheckBox)findViewById(R.id.AddSchedulePage_checkBox_drugType_syrup);
        checkBoxDrugTypeInhaler=(CheckBox)findViewById(R.id.AddSchedulePage_checkBox_drugType_inhaler);
        checkBoxDrugTypeInjection=(CheckBox)findViewById(R.id.AddSchedulePage_checkBox_drugType_injection);
        //checkBoxDrugTypeTablet.setChecked(true);


        //initialize edit texes
        editTextDrugName=(EditText)findViewById(R.id.AddSchedulePage_editText_drugName);
        editTextDrugDose=(EditText)findViewById(R.id.AddSchedulePage_editText_drugDose);

        //linear layouts
        linearLayoutDrugName=(LinearLayout)findViewById(R.id.AddSchedulePage_linearLayout_drugName);
        linearLayoutDrugDose=(LinearLayout)findViewById(R.id.AddSchedulePage_linearLayout_drugDose);
        linearLayoutDrugFoodRelation=(LinearLayout)findViewById(R.id.AddSchedulePage_linearLayout_drugFoodRelation);



        //__________________end of page UI___________________________

        makeRestViewsInvisible();
        Toast.makeText(context,"** patient "+LoginPage.getSelectedPatientName()+" in online **", Toast.LENGTH_SHORT).show();




        //create an instance of a calender
        final Calendar calendar = Calendar.getInstance();


        //initialize our alarm manager
        alarmManager=(AlarmManager) getSystemService(ALARM_SERVICE);


        //create an on click listener to start the alarm
        buttonAlarmOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                pendingIntentId= Utility.readBiggestPendingIntentId(context);
                pendingIntentId++;
                Utility.saveBiggestPendingIntentId(pendingIntentId,context);







                //setting calender year, month, day base on date picker
               calendar.set(alarm_datepicker.getYear(), alarm_datepicker.getMonth(), alarm_datepicker.getDayOfMonth());
               //setting calender minute and hour base on time picker
               calendar.set(Calendar.HOUR_OF_DAY,alarm_timepicker.getHour());
               calendar.set(Calendar.MINUTE,alarm_timepicker.getMinute());
               calendar.set(Calendar.SECOND,0);

                //get the int value of hour and minute picker
                int hour=alarm_timepicker.getHour();
                int minute=alarm_timepicker.getMinute();
                //get the String value of hour and minute picker
                String hourString=String.valueOf(hour);
                String minuteString=String.valueOf(minute);

                //convert 10:3 to 10:03
                if (minute<10)
                    minuteString="0"+minuteString;


                //method that changes the update text view
                setUpdateTextView("alarm set to: "+hourString+":"+minuteString);


                //create an intent to AlarmReceiver class
                intent =new Intent(context, AlarmReceiver.class);
                //pass the pending intent id
                intent.putExtra("pendingIntentId", pendingIntentId);
                //tell that you pressed the "alarm on button"
                intent.putExtra("extra","alarm on");


                pendingIntent=PendingIntent.getBroadcast(AddScedulePage.this, pendingIntentId ,intent,PendingIntent.FLAG_UPDATE_CURRENT);

                //set the alarm manager
                alarmManager.set(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),pendingIntent);




                //___________save alarm in the database____________________
                int patientId;
                long alarmTime=calendar.getTimeInMillis();
                setDrugName();
                setDrugDose();


                SQLiteDatabase database=openOrCreateDatabase("pcaDatabase",MODE_PRIVATE,null);
                FindPatientIDCommand findPatientIDCommand=new FindPatientIDCommand(LoginPage.getSelectedPatientName(),database,AddScedulePage.this);
                findPatientIDCommand.execute();
                patientId=findPatientIDCommand.getPatientID();


                Schedule schedule=new Schedule(patientId,pendingIntentId,alarmTime,drugType,drugName,drugDose,foodRelation);
                AddScheduleCommand command =new AddScheduleCommand(schedule,database,AddScedulePage.this);
                command.execute();

                //Log.e("_____________"+schedule.toString(),"___________");
                   //___________________test part___________________:

                    String query="select pid,pendingintentid,alarmtime,drugtype,drugname,drugdose,drugfoodrelation from schedule ;";
                    Cursor cursor = database.rawQuery(query, null);

                    int patientId2;
                    int pendingIntentId2;
                    long alarmTime2;
                    String drugType2;
                    String drugName2;
                    String drugDose2;
                    String drugFoodRelation2;

                    String date;

                    while (cursor.moveToNext()) {

                    patientId2=cursor.getInt(cursor.getColumnIndex("pid"));
                    pendingIntentId2=cursor.getInt(cursor.getColumnIndex("pendingintentid"));
                    alarmTime2=cursor.getLong(cursor.getColumnIndex("alarmtime"));
                    drugType2=cursor.getString(cursor.getColumnIndex("drugtype"));
                    drugName2=cursor.getString(cursor.getColumnIndex("drugname"));
                    drugDose2=cursor.getString(cursor.getColumnIndex("drugdose"));
                    drugFoodRelation2=cursor.getString(cursor.getColumnIndex("drugfoodrelation"));

                    date=Utility.getDate(alarmTime2, "yyyy/MM/dd HH:mm:ss");
                    Toast.makeText(AddScedulePage.this,patientId2+"__"+pendingIntentId2+"__"+date+"__"+drugType2+"__"+drugName2+"__"+drugDose2+"__"+drugFoodRelation2     , Toast.LENGTH_SHORT).show();


                    }
                //Toast.makeText(AddScedulePage.this,schedule.toString()  , Toast.LENGTH_SHORT).show();







                database.close();






                //_____________end of database part_____________




            }
        });



    }




}
