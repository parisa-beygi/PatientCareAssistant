package com.designpattern.finalproject.pca.command;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.designpattern.finalproject.pca.databaseManager.DatabasePatientManager;

/**
 * Created by Asus-pc on 2018/02/11.
 */

public class FindPatientIDCommand extends Command {
    private int PatientID;
    private String patientName;
    private SQLiteDatabase database;
    private Context context;

    public FindPatientIDCommand(String patientName, SQLiteDatabase database, Context context) {
        this.patientName = patientName;
        this.database = database;
        this.context = context;
    }

    @Override
    public void execute() {
        DatabasePatientManager dbPatientManager=new DatabasePatientManager();
        PatientID=dbPatientManager.getpatientId(database,patientName);
 }

    public int getPatientID() {
        return PatientID;
    }
}
