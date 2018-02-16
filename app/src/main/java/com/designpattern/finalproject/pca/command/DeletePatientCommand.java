package com.designpattern.finalproject.pca.command;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.designpattern.finalproject.pca.databaseManager.DatabasePatientManager;

/**
 * Created by Asus-pc on 2018/01/27.
 */

public class DeletePatientCommand extends Command {
    private String patientName;
    private SQLiteDatabase database;
    private Context context;
    public DeletePatientCommand(String patientName, SQLiteDatabase database,Context context) {
        this.patientName = patientName;
        this.database = database;
        this.context=context;
    }

    @Override
    public void execute() {
        DatabasePatientManager dbPatientManager=new DatabasePatientManager();
        dbPatientManager.deletePatient(database,patientName);
        Toast.makeText(context,"** patient "+patientName+" removed **", Toast.LENGTH_SHORT).show();

    }
}
