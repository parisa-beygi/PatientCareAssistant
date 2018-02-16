package com.designpattern.finalproject.pca.command;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.designpattern.finalproject.pca.databaseManager.DatabaseMedicalManager;
import com.designpattern.finalproject.pca.medical.BloodPressureMedicalElement;

/**
 * Created by Asus-pc on 2018/02/13.
 */

public class AddBloodPressureCommand extends Command {


    private BloodPressureMedicalElement bloodPressureMedicalElement;
    private SQLiteDatabase database;
    private Context context;

    public AddBloodPressureCommand(BloodPressureMedicalElement bloodPressureMedicalElement, SQLiteDatabase database, Context context) {
        this.bloodPressureMedicalElement = bloodPressureMedicalElement;
        this.database = database;
        this.context = context;
    }

    @Override
    public void execute() {
        DatabaseMedicalManager databaseMedicalManager = new DatabaseMedicalManager();
        databaseMedicalManager.createBloodPresureTable(database);
        databaseMedicalManager.insertBloodPressure(database, bloodPressureMedicalElement);
        Toast.makeText(context, "** blood pressure element added **", Toast.LENGTH_SHORT).show();
    }

}
