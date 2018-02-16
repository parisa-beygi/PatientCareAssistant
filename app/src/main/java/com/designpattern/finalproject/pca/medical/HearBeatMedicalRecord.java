package com.designpattern.finalproject.pca.medical;

import java.util.ArrayList;

/**
 * Created by Parisa on 2/15/2018.
 */

public class HearBeatMedicalRecord extends MedicalRecord {
    private ArrayList<HeartBeatMedicalElement> heartBeatMedicalElements;

    public HearBeatMedicalRecord(MedicalDrawing medicalDrawing, ArrayList<HeartBeatMedicalElement> heartBeatMedicalElements) {
        super(medicalDrawing);
        this.heartBeatMedicalElements = heartBeatMedicalElements;
    }

    @Override
    public void draw() {

    }

    public void drawHeartBeat() {

    }
}
