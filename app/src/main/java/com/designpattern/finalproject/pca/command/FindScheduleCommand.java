package com.designpattern.finalproject.pca.command;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.designpattern.finalproject.pca.Schedule;
import com.designpattern.finalproject.pca.databaseManager.DatabaseScheduleManager;

/**
 * Created by Asus-pc on 2018/02/11.
 */

public class FindScheduleCommand extends Command {
    int pendingIntentId;
    private Schedule schedule;
    private SQLiteDatabase database;
    private Context context;

    public FindScheduleCommand(int pendingIntentId, SQLiteDatabase database, Context context) {
        this.pendingIntentId = pendingIntentId;
        this.database = database;
        this.context = context;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    @Override
    public void execute() {
        DatabaseScheduleManager dbScheduleManager=new DatabaseScheduleManager();
        schedule=dbScheduleManager.findScheduleByPendingIntentId(database,pendingIntentId);
    }
}
