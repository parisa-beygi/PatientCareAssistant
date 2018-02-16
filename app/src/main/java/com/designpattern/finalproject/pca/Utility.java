package com.designpattern.finalproject.pca;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.designpattern.finalproject.pca.themeFactory.GirlThemeFactory;
import com.designpattern.finalproject.pca.themeFactory.GrannyThemeFactory;
import com.designpattern.finalproject.pca.themeFactory.GuyThemeFactory;
import com.designpattern.finalproject.pca.themeFactory.ThemeFactory;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Asus-pc on 2018/01/20.
 */

public class Utility {

    public static void saveTheme(String th , Context context) {
        //saved selected theme to the primefaces file:
        //get acsess or create primefaces file(it's an key value file)
        SharedPreferences sharedPref = context.getSharedPreferences(
                "designPatternFinalProjectThemePrimeFacesFile", Context.MODE_PRIVATE);

        //write on primefaces file
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("Theme", th);
        editor.commit();

    }



    public static String readTheme(Context context){

        SharedPreferences sharedPref = context.getSharedPreferences(
                "designPatternFinalProjectThemePrimeFacesFile", Context.MODE_PRIVATE);
        //retrieve values from a shared preferences file
        String defaultValue = "Girl";
        String selectedtheme = sharedPref.getString("Theme",defaultValue);
         return selectedtheme;

    }


    public static void saveBiggestPendingIntentId(int id , Context context) {
        //saved BiggestPendingIntentId to the primefaces file:
        //get acsess or create primefaces file(it's an key value file)
        SharedPreferences sharedPref = context.getSharedPreferences(
                "designPatternFinalProjectBiggestPendingIntentIdPrimeFacesFile", Context.MODE_PRIVATE);

        //write on primefaces file
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt("BiggestPendingIntentId", id);
        editor.commit();

    }
    public static int readBiggestPendingIntentId(Context context){

        SharedPreferences sharedPref = context.getSharedPreferences(
                "designPatternFinalProjectBiggestPendingIntentIdPrimeFacesFile", Context.MODE_PRIVATE);
        //retrieve values from a shared preferences file
        int defaultValue = 0;
        int BiggestPendingIntentId = sharedPref.getInt("BiggestPendingIntentId",defaultValue);
        return BiggestPendingIntentId;

    }




    public static void setFactory(Context context){
        String theme=Utility.readTheme(context);
        //Toast.makeText(context, "___"+theme, Toast.LENGTH_LONG).show();
        ThemeFactory.setUnigueInstanceToNull();
        ThemeFactory factory = null;
        switch(theme){
            case "Girl":
                factory= GirlThemeFactory.instance();       break;
            case "Granny":
                factory= GrannyThemeFactory.instance();    break;
            case "Guy":
                factory= GuyThemeFactory.instance();       break;
            default:
                Toast.makeText(context,"!!No valid theme find in primefaces file!!", Toast.LENGTH_LONG).show();break;
        }

    }


    public static String getDate(long milliSeconds, String dateFormat)
    {
        // Create a DateFormatter object for displaying date in specified format.
        SimpleDateFormat formatter = new SimpleDateFormat(dateFormat/*"yyyy/MM/dd HH:mm:ss"*/);

        // Create a calendar object that will convert the date and time value in milliseconds to date.
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliSeconds);
        return formatter.format(calendar.getTime());
    }


}
