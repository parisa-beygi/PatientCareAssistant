package com.designpattern.finalproject.pca.databaseManager;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import com.designpattern.finalproject.pca.Patient;
import com.designpattern.finalproject.pca.Schedule;
import com.designpattern.finalproject.pca.Utility;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Asus-pc on 2018/02/11.
 */

public class DatabaseScheduleManager {
    public void createScheduleTable(SQLiteDatabase database){

        String query= " CREATE TABLE if not exists schedule( "+
                "pid INTEGER NOT NULL , "+
                "pendingintentid INTEGER NOT NULL , "+
                "alarmtime INTEGER NOT NULL , "+
                "drugtype           TEXT      NOT NULL  ," +
                "drugname           TEXT      NOT NULL  ," +
                "drugdose           TEXT      NOT NULL  ," +
                "drugfoodrelation           TEXT      NOT NULL  ," +
                "PRIMARY KEY (pendingintentid) " +
                " , FOREIGN KEY (pid) REFERENCES patient(id) " +
                " ON DELETE CASCADE "+
                ");";


        database.execSQL(query);
    }
    public void insertSchedule(SQLiteDatabase database, Schedule schedule){

        //_________________insert in schedule table:____________________

        String query;

        int patientId=schedule.getPatientId();
        int pendingIntentId=schedule.getPendingIntentId();
        long alarmTime=schedule.getAlarmTime();
        String drugType=schedule.getDrugType();
        String drugName=schedule.getDrugName();
        String drugDose=schedule.getDrugDose();
        String drugFoodRelation=schedule.getDrugFoodRelation();


           // Toast.makeText(context,"diseas is: "+disItem+id, Toast.LENGTH_SHORT).show();
         query= " INSERT INTO schedule (pid,pendingintentid,alarmtime,drugtype,drugname,drugdose,drugfoodrelation)"+
                    " VALUES (" +
                    patientId+
                    ","+
                    pendingIntentId+
                    ","+
                    alarmTime+
                    ","+
                    "'"+drugType+"'"+
                    ","+
                    "'"+drugName+"'"+
                    ","+
                    "'"+drugDose+"'"+
                    ","+
                    "'"+drugFoodRelation+"') "+
                    ";";

         database.execSQL(query);


    }

    public void deleteSchedule(SQLiteDatabase database, int patientId,int alarmTime){
        // int id =getpatientId(database,patient.getName());
        String query= " DELETE FROM patient WHERE pid="+patientId+" and alarmtime= "+alarmTime+";";
        database.execSQL(query);
    }


    public Boolean existsSchedule(SQLiteDatabase database,int pendingIntentId){
        Boolean exists =false;
        String query="select pendingintentid from schedule WHERE pendingintentid="+pendingIntentId+" ;";
        Cursor cursor = database.rawQuery(query, null);

        if (cursor.moveToFirst()){
            do {
                exists=true;
            } while(cursor.moveToNext());
        }
        cursor.close();
        return exists;
    }


    public Schedule findScheduleByPendingIntentId(SQLiteDatabase database,int pendingIntentId){
        Schedule schedule ;

        int patientId2 = 0;
        int pendingIntentId2=0;
        long alarmTime2=0;
        String drugType2="";
        String drugName2="";
        String drugDose2="";
        String drugFoodRelation2="";

        String query="select pid,pendingintentid,alarmtime,drugtype,drugname,drugdose,drugfoodrelation from schedule where pendingintentid="+pendingIntentId+";";
        Cursor cursor = database.rawQuery(query, null);



        while (cursor.moveToNext()) {
            Log.e("im in the while in database schedule manager","yeeees");
            patientId2 = cursor.getInt(cursor.getColumnIndex("pid"));
            pendingIntentId2 = cursor.getInt(cursor.getColumnIndex("pendingintentid"));
            alarmTime2 = cursor.getLong(cursor.getColumnIndex("alarmtime"));
            drugType2 = cursor.getString(cursor.getColumnIndex("drugtype"));
            drugName2 = cursor.getString(cursor.getColumnIndex("drugname"));
            drugDose2 = cursor.getString(cursor.getColumnIndex("drugdose"));
            drugFoodRelation2 = cursor.getString(cursor.getColumnIndex("drugfoodrelation"));
        }

        schedule=new Schedule(patientId2,pendingIntentId2,alarmTime2,drugType2,drugName2,drugDose2,drugFoodRelation2);


        return schedule;

    }


    public ArrayList<Schedule> getPatientScheduleList(SQLiteDatabase database,int patientId) {

        ArrayList<Schedule> patientScheduleList;
        patientScheduleList=new ArrayList<Schedule>();


        Schedule schedule ;

        int patientId2 = 0;
        int pendingIntentId2=0;
        long alarmTime2=0;
        String drugType2="";
        String drugName2="";
        String drugDose2="";
        String drugFoodRelation2="";

        String query="select pid,pendingintentid,alarmtime,drugtype,drugname,drugdose,drugfoodrelation from schedule where pid="+patientId+";";
        Cursor cursor = database.rawQuery(query, null);


        while (cursor.moveToNext()) {
            patientId2 = cursor.getInt(cursor.getColumnIndex("pid"));
            pendingIntentId2 = cursor.getInt(cursor.getColumnIndex("pendingintentid"));
            alarmTime2 = cursor.getLong(cursor.getColumnIndex("alarmtime"));
            drugType2 = cursor.getString(cursor.getColumnIndex("drugtype"));
            drugName2 = cursor.getString(cursor.getColumnIndex("drugname"));
            drugDose2 = cursor.getString(cursor.getColumnIndex("drugdose"));
            drugFoodRelation2 = cursor.getString(cursor.getColumnIndex("drugfoodrelation"));
            schedule=new Schedule(patientId2,pendingIntentId2,alarmTime2,drugType2,drugName2,drugDose2,drugFoodRelation2);
            patientScheduleList.add(schedule);

        }



        cursor.close();



        return patientScheduleList;



    }
}
