package com.designpattern.finalproject.pca.command;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.designpattern.finalproject.pca.databaseManager.DatabaseMedicalManager;
import com.designpattern.finalproject.pca.medical.HeartBeatMedicalElement;

import java.util.ArrayList;

/**
 * Created by Parisa on 2018/02/15.
 */

public class ViewHeartBeatCommand extends Command {

    private int patientId;
    private SQLiteDatabase database;
    private Context context;

    private ArrayList<HeartBeatMedicalElement> heartBeatMedicalElements;

    public ArrayList<HeartBeatMedicalElement> getHeartBeatMedicalElements() {
        return heartBeatMedicalElements;
    }

    public ViewHeartBeatCommand(int patientId, SQLiteDatabase database, Context context) {
        this.patientId = patientId;
        this.database = database;
        this.context = context;
    }


    @Override
    public void execute() {
        DatabaseMedicalManager databaseMedicalManager = new DatabaseMedicalManager();
        databaseMedicalManager.createHeartBeatTable(database);
        heartBeatMedicalElements = databaseMedicalManager.getPatientHeartBeatMedicalElementList(database, patientId);
        Toast.makeText(context, "** heart beat elements listed**", Toast.LENGTH_SHORT).show();
    }



    }
