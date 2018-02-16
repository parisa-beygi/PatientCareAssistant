package com.designpattern.finalproject.pca.databaseManager;
import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.designpattern.finalproject.pca.Patient;

import java.util.ArrayList;
import java.util.Iterator;

import static android.database.sqlite.SQLiteDatabase.openOrCreateDatabase;

/**
 * Created by Asus-pc on 2018/01/24.
 */

public class DatabasePatientManager extends Activity {

    public  SQLiteDatabase openOrCreateDatabase(){
         return  openOrCreateDatabase("pcaDatabase",MODE_PRIVATE,null);
     }

    public void closeDatabase(SQLiteDatabase database){
        database.close();
    }

    public void createPatientTable(SQLiteDatabase database){

        String query= " CREATE TABLE if not exists patient( "+
                "id INTEGER PRIMARY KEY   AUTOINCREMENT, "+
                "name           TEXT      NOT NULL  UNIQUE, "+
                "age            INT       NOT NULL "+
                ");";

        database.execSQL(query);
    }

    public void createDiseasTable(SQLiteDatabase database){

        String query= " CREATE TABLE if not exists diseases( "+
                "pid INTEGER , "+
                "name           TEXT      NOT NULL  ," +
                "PRIMARY KEY (pid,name) " +
                " , FOREIGN KEY (pid) REFERENCES patient(id) " +
                " ON DELETE CASCADE "+
                 ");";

        database.execSQL(query);
    }

    public ArrayList<String> getSpecialDiseasesByPatient(SQLiteDatabase database, int patientId) {
        String query="select name from diseases where pid='"+patientId+"';";
        Cursor cursor = database.rawQuery(query, null);

        String name2;
        ArrayList<String> diseases = new ArrayList<>();
        while (cursor.moveToNext()) {
            name2 = cursor.getString(cursor.getColumnIndex("name"));
            diseases.add(name2);
        }

        cursor.close();

        return diseases;
    }

    public int getpatientId(SQLiteDatabase database,String patientname){
        String query="select id,name,age from patient where name='"+patientname+"';";
        Cursor cursor = database.rawQuery(query, null);
        cursor.moveToFirst();
        int id=cursor.getInt(cursor.getColumnIndex("id"));
        cursor.close();
        return  id;
    }

    public void insertPatient(SQLiteDatabase database, Patient patient){
        String query;
       //___________________insert in patient table:___________________
        String name=patient.getName();
        int age=patient.getAge();
        query= " INSERT INTO patient (name,age)"+
                      "VALUES ( '"+name+"',"+age+") "+
                      ";";
        database.execSQL(query);

        //_________________insert in diseas table:____________________
        //get patient id that set for it in patient table:
        int id =getpatientId(database,name);
        ArrayList<String> diseasList=patient.getSpecialDiseasesList();
        //insert all diseas in  disias table :
        String disItem;
        for (Iterator<String> it = diseasList.iterator(); it.hasNext(); ) {
            disItem=it.next();
           // Toast.makeText(context,"diseas is: "+disItem+id, Toast.LENGTH_SHORT).show();
            query= " INSERT INTO diseases (pid,name)"+
                    " VALUES ("+id+",'"+disItem+"') "+
                    ";";

            database.execSQL(query);
        }





    }

    public void deletePatient(SQLiteDatabase database, String patientname){
       // int id =getpatientId(database,patient.getName());
        String query= " DELETE FROM patient WHERE name='"+patientname+"';";
        database.execSQL(query);
    }

    public ArrayList<String> getPatientsNameList(SQLiteDatabase database){
        ArrayList<String> patientsNameList;
        patientsNameList=new ArrayList<String>();

        String query="select name from patient ;";
        Cursor cursor = database.rawQuery(query, null);

        if (cursor.moveToFirst()){
            do {
                patientsNameList.add(cursor.getString(cursor.getColumnIndex("name")));
            } while(cursor.moveToNext());
        }
        cursor.close();

        return patientsNameList;
    }

    public Boolean existsPatient(SQLiteDatabase database,String patientname){
        Boolean exists =false;
        String query="select name from patient WHERE name='"+patientname+"';";
        Cursor cursor = database.rawQuery(query, null);

        if (cursor.moveToFirst()){
            do {
                exists=true;
            } while(cursor.moveToNext());
        }
        cursor.close();
        return exists;
    }


}
