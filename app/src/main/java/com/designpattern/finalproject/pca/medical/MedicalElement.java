package com.designpattern.finalproject.pca.medical;

import com.designpattern.finalproject.pca.Utility;
import com.designpattern.finalproject.pca.pages.LoginPage;

/**
 * Created by Asus-pc on 2018/02/13.
 */

public abstract class MedicalElement {

    protected int PatientId ;
    protected long time;
    protected int value;

    public MedicalElement(int patientId, long time, int value) {
        PatientId = patientId;
        this.time = time;
        this.value = value;
    }


    public String toString() {
        return "{"+"\n" +
                "value: " + value + "\n" +
                "date: " + Utility.getDate(time, "yyyy/MM/dd HH:mm:ss") +"\n" +
                '}';
    }

    public abstract void accept(MedicalElementVisitor medicalElementVisitor);

    public int getPatientId() {
        return PatientId;
    }

    public long getTime() {
        return time;
    }

    public int getValue() {
        return value;
    }
}
