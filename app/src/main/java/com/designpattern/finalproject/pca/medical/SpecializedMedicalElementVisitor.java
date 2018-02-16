package com.designpattern.finalproject.pca.medical;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.designpattern.finalproject.pca.databaseManager.DatabasePatientManager;
import com.designpattern.finalproject.pca.pages.AddMedicalElementPage;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Parisa on 2/15/2018.
 */

public class SpecializedMedicalElementVisitor extends MedicalElementVisitor {
    private SQLiteDatabase database;
    private Context context;
    private static final DatabasePatientManager databasePatientManager = new DatabasePatientManager();

    public SpecializedMedicalElementVisitor(SQLiteDatabase database, Context context) {
        this.database = database;
        this.context = context;
    }

    @Override
    public void visit(HeartBeatMedicalElement heartBeatMedicalElement) {
        int patientId = heartBeatMedicalElement.getPatientId();
        ArrayList<String> diseases = databasePatientManager.getSpecialDiseasesByPatient(database, patientId);

        if(diseases.contains("Mumbai") && heartBeatMedicalElement.getValue() < 60) {
            Toast.makeText(context, "You need to see a doctor!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void visit(BloodPressureMedicalElement bloodPressureMedicalElement) {
        int patientId = bloodPressureMedicalElement.getPatientId();
        ArrayList<String> diseases = databasePatientManager.getSpecialDiseasesByPatient(database, patientId);

        if(diseases.contains("Indore") && bloodPressureMedicalElement.getValue() > 14) {
            Toast.makeText(context, "Your blood pressure is unexpectedly high!", Toast.LENGTH_SHORT).show();
        }

    }
}
