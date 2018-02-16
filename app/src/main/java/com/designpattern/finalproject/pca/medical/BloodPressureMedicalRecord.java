package com.designpattern.finalproject.pca.medical;

import java.util.ArrayList;

/**
 * Created by Parisa on 2/15/2018.
 */

public class BloodPressureMedicalRecord extends MedicalRecord {

    private ArrayList<BloodPressureMedicalElement> bloodPressureMedicalElements;

    public BloodPressureMedicalRecord(MedicalDrawing medicalDrawing, ArrayList<BloodPressureMedicalElement> bloodPressureMedicalElements) {
        super(medicalDrawing);
        this.bloodPressureMedicalElements = bloodPressureMedicalElements;
    }

    @Override
    public void draw() {

    }

    public void drawBloodPressure() {

    }
}
