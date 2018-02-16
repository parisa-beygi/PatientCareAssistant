package com.designpattern.finalproject.pca.databaseManager;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.designpattern.finalproject.pca.medical.BloodPressureMedicalElement;
import com.designpattern.finalproject.pca.medical.HeartBeatMedicalElement;
import com.designpattern.finalproject.pca.medical.MedicalElement;

import java.util.ArrayList;

/**
 * Created by Asus-pc on 2018/02/13.
 */

public class DatabaseMedicalManager
{

    public void createHeartBeatTable(SQLiteDatabase database) {
        String query= " CREATE TABLE if not exists heartbeat( "+
                "pid INTEGER NOT NULL , "+
                "time INTEGER NOT NULL , "+
                "value INTEGER NOT NULL , "+
                "PRIMARY KEY (pid,time) " +
                " , FOREIGN KEY (pid) REFERENCES patient(id) " +
                " ON DELETE CASCADE "+
                ");";
        database.execSQL(query);
    }

    public void createBloodPresureTable(SQLiteDatabase database) {
            String query= " CREATE TABLE if not exists bloodpressure( "+
                    "pid INTEGER NOT NULL , "+
                    "time INTEGER NOT NULL , "+
                    "value INTEGER NOT NULL , "+
                    "PRIMARY KEY (pid,time) " +
                    " , FOREIGN KEY (pid) REFERENCES patient(id) " +
                    " ON DELETE CASCADE "+
                    ");";
            database.execSQL(query);

    }

    public void insertHeartBeat(SQLiteDatabase database, HeartBeatMedicalElement heartBeatMedicalElement){

        //_________________insert in Heart beat table:____________________

        String query;

        int patientId=heartBeatMedicalElement.getPatientId();
        long time=heartBeatMedicalElement.getTime();
        int value=heartBeatMedicalElement.getValue();

        // Toast.makeText(context,"diseas is: "+disItem+id, Toast.LENGTH_SHORT).show();
        query= " INSERT INTO heartbeat (pid,time,value)"+
                " VALUES (" +
                patientId+
                ","+
                time+
                ","+
                value+
               ") "+
                ";";

        database.execSQL(query);
    }

    public void insertBloodPressure(SQLiteDatabase database, BloodPressureMedicalElement bloodPressureMedicalElement){

        //_________________insert in blood pressure table:____________________

        String query;

        int patientId=bloodPressureMedicalElement.getPatientId();
        long time=bloodPressureMedicalElement.getTime();
        int value=bloodPressureMedicalElement.getValue();

        // Toast.makeText(context,"diseas is: "+disItem+id, Toast.LENGTH_SHORT).show();
        query= " INSERT INTO bloodpressure (pid,time,value)"+
                " VALUES (" +
                patientId+
                ","+
                time+
                ","+
                value+
                ") "+
                ";";

        database.execSQL(query);
    }

    public ArrayList<HeartBeatMedicalElement> getPatientHeartBeatMedicalElementList(SQLiteDatabase database, int patientId) {

        ArrayList<HeartBeatMedicalElement> heartBeatMedicalElementList;
        heartBeatMedicalElementList=new ArrayList<HeartBeatMedicalElement>();


        HeartBeatMedicalElement heartBeatMedicalElement ;


        int patientId2=patientId;
        long time2=0;
        int value2=0;

        String query="select pid,time,value from heartbeat where pid="+patientId+";";
        Cursor cursor = database.rawQuery(query, null);


        while (cursor.moveToNext()) {
            patientId2 = cursor.getInt(cursor.getColumnIndex("pid"));
            time2 = cursor.getLong(cursor.getColumnIndex("time"));
            value2 = cursor.getInt(cursor.getColumnIndex("value"));

            heartBeatMedicalElement=new HeartBeatMedicalElement(patientId2,time2,value2);
            heartBeatMedicalElementList.add(heartBeatMedicalElement);
        }

        cursor.close();


        return heartBeatMedicalElementList;



    }

    public ArrayList<BloodPressureMedicalElement> getPatientBloodPressureMedicalElementList(SQLiteDatabase database, int patientId) {

        ArrayList<BloodPressureMedicalElement> bloodPressureMedicalElementList;
        bloodPressureMedicalElementList=new ArrayList<BloodPressureMedicalElement>();


        BloodPressureMedicalElement bloodPressureMedicalElement ;


        int patientId2=patientId;
        long time2=0;
        int value2=0;

        String query="select pid,time,value from bloodpressure where pid="+patientId+";";
        Cursor cursor = database.rawQuery(query, null);


        while (cursor.moveToNext()) {
            patientId2 = cursor.getInt(cursor.getColumnIndex("pid"));
            time2 = cursor.getLong(cursor.getColumnIndex("time"));
            value2 = cursor.getInt(cursor.getColumnIndex("value"));

            bloodPressureMedicalElement=new BloodPressureMedicalElement(patientId2,time2,value2);
            bloodPressureMedicalElementList.add(bloodPressureMedicalElement);
        }

        cursor.close();


        return bloodPressureMedicalElementList;



    }
}
