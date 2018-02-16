package com.designpattern.finalproject.pca;

import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import static android.database.sqlite.SQLiteDatabase.openOrCreateDatabase;

/**
 * Created by Asus-pc on 2018/01/24.
 */

public class Patient {
    public final static String availableDiseas[]={
            "Bangalore",
            "Chennai",
            "Mumbai",
            "Pune",
            "Delhi",
            "Jabalpur",
            "Indore",
            "Ranchi",
            "Hyderabad",
            "Ahmedabad",
            "Kolkata",
            "Bhopal"
    };


    private String name;
    private int age;
    private ArrayList<String> specialDiseasesList;

    public Patient(String name, int age, ArrayList<String> specialDiseasesList) {
        this.name = name;
        this.age = age;
        this.specialDiseasesList = specialDiseasesList;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public ArrayList<String> getSpecialDiseasesList() {
        return specialDiseasesList;
    }
}
