package com.designpattern.finalproject.pca;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import com.designpattern.finalproject.pca.pages.AddScedulePage;
import com.designpattern.finalproject.pca.pages.TurnOffAlarmPage;

/**
 * Created by Asus-pc on 2018/02/08.
 */

public class RingtonePlayingService extends Service {

    MediaPlayer mediaSong;
    int startId;
    boolean isRunning;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("LocalService", "Received start id " + startId + ": " + intent);
        Log.e("in ringtone playing service", "yes");

        //fetch the extra string value
        String extraString=intent.getExtras().getString("extra");
        Log.e("ring tone state is",extraString);

        //fetch the pendingIntentId value
        int pendingIntentId=intent.getIntExtra("pendingIntentId", 0);
        Log.e("ring tone pending intent id is ",pendingIntentId+"");

        //notification
        //set up the notification service
        NotificationManager notificationManager=(NotificationManager)
                getSystemService(NOTIFICATION_SERVICE);


        //set up an intent that goes to the AddScadulePage
        //Intent intentGoAddScadulePage=new Intent(this.getApplicationContext(), AddScedulePage.class);
        //set the pending intent
        //PendingIntent pendingIntentAddScadulePage=PendingIntent.getActivity(this,0,intentGoAddScadulePage,0);



        //set up an intent that goes to the TurnOffAlarmPage
        Intent intentGoTurnOffAlarmPage=new Intent(this.getApplicationContext(), TurnOffAlarmPage.class);
        intentGoTurnOffAlarmPage.putExtra("pendingIntentId", pendingIntentId);

        //set the pending intent
        PendingIntent pendingIntentTurnOffAlarmPage=PendingIntent.getActivity(this,0,intentGoTurnOffAlarmPage,PendingIntent.FLAG_CANCEL_CURRENT);




        //this convert extra srting to int startId 0 or 1
        assert extraString!=null;
        switch (extraString) {
            case "alarm on":
                startId = 1;
                break;
            case "alarm off":
                startId = 0;
                break;
            default:
                startId = 0;
                break;
        }





        //if else statements

        //if there is no music playing and the user press the "alarm on"
        //music should start playing.
        if(!this.isRunning && startId == 1){
            Log.e("there is no music","and you want start");
            Log.e("ring tone pending intent id is ",pendingIntentId+"");
            //create an instance of a mediaplayer
            mediaSong = MediaPlayer.create(this,R.raw.song);
            //start the ringtone
            mediaSong.start();
            mediaSong.setLooping(true);

            this.isRunning=true;
            this.startId=0; //set the class variable . not local one




            //make the notification parameters
            Notification notificationPopup = new Notification.Builder(this)
                    .setSmallIcon(R.drawable.logo)
                    .setContentTitle("An alarm is going off")
                    .setContentText("Click me")
                    //.setContentIntent(pendingIntentAddScadulePage)
                    .setContentIntent(pendingIntentTurnOffAlarmPage)
                    .setAutoCancel(true)
                    .build();

            //set up the notification call command
            notificationManager.notify(0,notificationPopup);


        }

        //if there is music playing and the user press the "alarm off"
        //music should stop playing.
        else if (this.isRunning && startId == 0){
            Log.e("there is music","and you want end");

            //stop the ringtone
            mediaSong.stop();
            mediaSong.reset();

            this.isRunning=false;
            this.startId=0; //set the class variable . not local one

        }

        //if there is no music playing and the user press the "alarm off"
        //do nothing
        else if (!this.isRunning && startId == 0){
            Log.e("there is no music","and you want end");

            this.isRunning=false;
            this.startId=0; //set the class variable . not local one

        }

        //if there is music playing and the user press the "alarm on"
        //do nothing
        else if(this.isRunning && startId == 1){
            Log.e("there is music","and you want start");

            this.isRunning=true;
            this.startId=0; //set the class variable . not local one

        }

        //cant think of anything else. just to catch the odd events!
        else{
            Log.e("else","maybe you rich this!");

        }









        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        //tell the user we stopped
        Log.e("on Destroy called","te da");

        super.onDestroy();
        this.isRunning=false;

    }






}
