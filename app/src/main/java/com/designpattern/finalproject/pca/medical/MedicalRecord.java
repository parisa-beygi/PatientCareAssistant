package com.designpattern.finalproject.pca.medical;

/**
 * Created by Parisa on 2/15/2018.
 */

public abstract class MedicalRecord {
    private MedicalDrawing medicalDrawing;

    public MedicalRecord(MedicalDrawing medicalDrawing) {
        this.medicalDrawing = medicalDrawing;
    }

    public abstract void draw();
}
