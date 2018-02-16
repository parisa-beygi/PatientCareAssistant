package com.designpattern.finalproject.pca;

import com.designpattern.finalproject.pca.pages.LoginPage;

import java.util.Calendar;

/**
 * Created by Asus-pc on 2018/02/10.
 */

public class Schedule {
    private int patientId;
    private int pendingIntentId;
    private long alarmTime;
    private String drugType;
    private String drugName;
    private String drugDose;
    private String drugFoodRelation;

    @Override
    public String toString() {
        return "Schedule{"+"\n" +
                "patientName=" + LoginPage.getSelectedPatientName() +"\n" +
                //", pendingIntentId=" + pendingIntentId +
                "alarmTime=" + Utility.getDate(alarmTime, "yyyy/MM/dd HH:mm:ss") +"\n" +
                "drugType='" + drugType + '\'' +"\n" +
                "drugName='" + drugName + '\'' +"\n" +
                "drugDose='" + drugDose + '\'' +"\n" +
                "drugFoodRelation='" + drugFoodRelation + '\'' +"\n" +
                '}';
    }

    public Schedule(int patientId, int pendingIntentId, long alarmTime, String drugType, String drugName, String drugDose, String drugFoodRelation) {
        this.patientId = patientId;
        this.pendingIntentId = pendingIntentId;
        this.alarmTime = alarmTime;
        this.drugType = drugType;
        this.drugName = drugName;
        this.drugDose = drugDose;
        this.drugFoodRelation = drugFoodRelation;
    }


    public int getPatientId() {
        return patientId;
    }

    public int getPendingIntentId() {
        return pendingIntentId;
    }

    public long getAlarmTime() {
        return alarmTime;
    }

    public String getDrugType() {
        return drugType;
    }

    public String getDrugName() {
        return drugName;
    }

    public String getDrugDose() {
        return drugDose;
    }

    public String getDrugFoodRelation() {
        return drugFoodRelation;
    }
}
