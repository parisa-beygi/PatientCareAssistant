package com.designpattern.finalproject.pca.medical;

/**
 * Created by Parisa on 2/15/2018.
 */

public abstract class MedicalElementVisitor {
    public abstract void visit(HeartBeatMedicalElement heartBeatMedicalElement);
    public abstract void visit(BloodPressureMedicalElement bloodPressureMedicalElement);
}
