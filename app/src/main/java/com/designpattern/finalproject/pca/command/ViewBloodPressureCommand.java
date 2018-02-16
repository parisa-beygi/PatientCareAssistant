package com.designpattern.finalproject.pca.command;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.designpattern.finalproject.pca.databaseManager.DatabaseMedicalManager;
import com.designpattern.finalproject.pca.medical.BloodPressureMedicalElement;
import com.designpattern.finalproject.pca.medical.HeartBeatMedicalElement;

import java.util.ArrayList;

/**
 * Created by Parisa on 2018/02/15.
 */

public class ViewBloodPressureCommand extends Command {

    private int patientId;
    private SQLiteDatabase database;
    private Context context;

    private ArrayList<BloodPressureMedicalElement> bloodPressureMedicalElements;

    public ArrayList<BloodPressureMedicalElement> getBloodPressureMedicalElements() {
        return bloodPressureMedicalElements;
    }

    public ViewBloodPressureCommand(int patientId, SQLiteDatabase database, Context context) {
        this.patientId = patientId;
        this.database = database;
        this.context = context;
    }


    @Override
    public void execute() {
        DatabaseMedicalManager databaseMedicalManager = new DatabaseMedicalManager();
        databaseMedicalManager.createBloodPresureTable(database);
        bloodPressureMedicalElements = databaseMedicalManager.getPatientBloodPressureMedicalElementList(database, patientId);
        Toast.makeText(context, "** blood pressure elements listed**", Toast.LENGTH_SHORT).show();
    }



    }
