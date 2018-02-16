package com.designpattern.finalproject.pca.command;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.designpattern.finalproject.pca.Patient;
import com.designpattern.finalproject.pca.databaseManager.DatabasePatientManager;

import static android.content.Context.MODE_PRIVATE;
import static android.database.sqlite.SQLiteDatabase.openOrCreateDatabase;

/**
 * Created by Asus-pc on 2018/01/26.
 */

public class AddPatientCommand extends Command {
    private Patient patient;
    private SQLiteDatabase database;
    private Context context;

    public AddPatientCommand(Patient patient, SQLiteDatabase database,Context context) {
        this.patient = patient;
        this.database = database;
        this.context=context;
    }



    @Override
    public void execute() {
        DatabasePatientManager dbPatientManager=new DatabasePatientManager();
        dbPatientManager.createPatientTable(database);
        dbPatientManager.createDiseasTable(database);
        if(!(dbPatientManager.existsPatient(database,patient.getName()))) {
            dbPatientManager.insertPatient(database,patient);
            Toast.makeText(context,"** patient "+patient.getName()+" added **", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context,"** patient "+patient.getName()+" already exists **", Toast.LENGTH_SHORT).show();
        }

    }
}
