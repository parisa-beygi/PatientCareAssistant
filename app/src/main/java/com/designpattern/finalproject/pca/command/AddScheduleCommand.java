package com.designpattern.finalproject.pca.command;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.designpattern.finalproject.pca.Patient;
import com.designpattern.finalproject.pca.Schedule;
import com.designpattern.finalproject.pca.databaseManager.DatabaseScheduleManager;

/**
 * Created by Asus-pc on 2018/02/10.
 */

public class AddScheduleCommand extends Command  {
    private Schedule schedule;
    private SQLiteDatabase database;
    private Context context;

    public AddScheduleCommand(Schedule schedule, SQLiteDatabase database, Context context) {
        this.schedule = schedule;
        this.database = database;
        this.context = context;
    }

    @Override
    public void execute() {
        DatabaseScheduleManager dbScheduleManager=new DatabaseScheduleManager();
        dbScheduleManager.createScheduleTable(database);
        if(!(dbScheduleManager.existsSchedule(database,schedule.getPendingIntentId()))) {
            dbScheduleManager.insertSchedule(database,schedule);
            Toast.makeText(context,"** Schedule added **", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context,"** alarm for pending intent id "+schedule.getPatientId()+" already exists , some thing wrong happens!**", Toast.LENGTH_SHORT).show();
            //maybe pending intent id over flows so insteead of insert update existing schedule
        }

  }
}
