package com.designpattern.finalproject.pca.medical;

/**
 * Created by Asus-pc on 2018/02/13.
 */

public class HeartBeatMedicalElement extends MedicalElement {
    public HeartBeatMedicalElement(int patientId, long time, int value) {
        super(patientId, time, value);
    }

    @Override
    public void accept(MedicalElementVisitor medicalElementVisitor) {
        medicalElementVisitor.visit(this);
    }

}
