package com.designpattern.finalproject.pca.command;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.designpattern.finalproject.pca.Schedule;
import com.designpattern.finalproject.pca.databaseManager.DatabaseMedicalManager;
import com.designpattern.finalproject.pca.databaseManager.DatabaseScheduleManager;
import com.designpattern.finalproject.pca.medical.HeartBeatMedicalElement;

/**
 * Created by Asus-pc on 2018/02/13.
 */

public class AddHeartBeatCommand extends Command {

    private HeartBeatMedicalElement heartBeatMedicalElement;
    private SQLiteDatabase database;
    private Context context;

    public AddHeartBeatCommand(HeartBeatMedicalElement heartBeatMedicalElement, SQLiteDatabase database, Context context) {
        this.heartBeatMedicalElement = heartBeatMedicalElement;
        this.database = database;
        this.context = context;
    }


    @Override
    public void execute() {
        DatabaseMedicalManager databaseMedicalManager = new DatabaseMedicalManager();
        databaseMedicalManager.createHeartBeatTable(database);
        databaseMedicalManager.insertHeartBeat(database, heartBeatMedicalElement);
        Toast.makeText(context, "** heart beat element added **", Toast.LENGTH_SHORT).show();
    }



    }
