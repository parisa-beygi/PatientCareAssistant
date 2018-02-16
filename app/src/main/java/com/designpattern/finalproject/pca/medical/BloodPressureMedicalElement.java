package com.designpattern.finalproject.pca.medical;

/**
 * Created by Asus-pc on 2018/02/13.
 */

public class BloodPressureMedicalElement extends MedicalElement {


    public BloodPressureMedicalElement(int patientId, long time, int value) {
        super(patientId, time, value);
    }

    @Override
    public void accept(MedicalElementVisitor medicalElementVisitor) {
        medicalElementVisitor.visit(this);
    }
}
