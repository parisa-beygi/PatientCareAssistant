package com.designpattern.finalproject.pca;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by Asus-pc on 2018/02/04.
 */

public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        int pendingIntentId=intent.getIntExtra("pendingIntentId", 0);
        Log.e("im in the receiver","yeah");
        Log.e("im in the "+ pendingIntentId+" receiver","yeah");

        //fetch extra string from intent
        String extraString=intent.getExtras().getString("extra");
        Log.e("what is the key",extraString);


        //create an intent to ringtone service
        Intent service_intent= new Intent(context,RingtonePlayingService.class);

        //pass the extra string from AddScedulePage to ringtone playing service
        service_intent.putExtra("extra",extraString);
        //pass the pending intent id from AddScedulePage to ringtone playing service
        service_intent.putExtra("pendingIntentId", pendingIntentId);


        //start the ringthone service
        context.startService(service_intent);


    }
}
