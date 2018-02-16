package com.designpattern.finalproject.pca.pages;

import android.app.ListActivity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.designpattern.finalproject.pca.R;
import com.designpattern.finalproject.pca.Schedule;
import com.designpattern.finalproject.pca.command.Command;
import com.designpattern.finalproject.pca.command.FindPatientIDCommand;
import com.designpattern.finalproject.pca.command.GoToNextPageCommand;
import com.designpattern.finalproject.pca.command.NullCommand;
import com.designpattern.finalproject.pca.databaseManager.DatabaseScheduleManager;
import com.designpattern.finalproject.pca.theme.StylablePage;
import com.designpattern.finalproject.pca.theme.StylableWidget;
import com.designpattern.finalproject.pca.themeFactory.ThemeFactory;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Asus-pc on 2018/02/12.
 */

public class ViewSchedulePage extends ListActivity {
    private ArrayList<Schedule> patientScheduleList;
    private ArrayList<String> patientScheduleListString;

    private void setPatientScheduleList(){
        SQLiteDatabase database=openOrCreateDatabase("pcaDatabase",MODE_PRIVATE,null);
        FindPatientIDCommand findPatientIDCommand=new FindPatientIDCommand(LoginPage.getSelectedPatientName(),database,ViewSchedulePage.this);
        findPatientIDCommand.execute();
        int patientId=findPatientIDCommand.getPatientID();

        DatabaseScheduleManager dbScheduleManager =new DatabaseScheduleManager();
        dbScheduleManager.createScheduleTable(database);
        patientScheduleList=dbScheduleManager.getPatientScheduleList(database,patientId);
        database.close();
    }

    private void setPatientScheduleListString(){
        patientScheduleListString=new ArrayList<String>();
        for (Iterator<Schedule> it = patientScheduleList.iterator(); it.hasNext(); ) {
            patientScheduleListString.add(it.next().toString());
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewschedule_page);


        //__________________page UI:___________________________
        ThemeFactory factory =ThemeFactory.instance();

        TextView textViewToolbar= (TextView) findViewById(R.id.ViewSchedulePage_textView_toolbar);
        TextView textViewTitle= (TextView) findViewById(R.id.ViewSchedulePage_textView_title);
        ImageView imageViewWallpaper= (ImageView) findViewById(R.id.ViewSchedulePage_imageView_wallpaper);
        final Command goMainpageCommand=new GoToNextPageCommand(ViewSchedulePage.this,MainPage.class);
        Button buttonGoBack = (Button) findViewById(R.id.ViewSchedulePage_button_goBack);


        StylableWidget stylableTextViewToolbar = factory.createTextView(textViewToolbar);
        StylableWidget stylableTextViewTitle = factory.createTextView(textViewTitle);
        StylableWidget stylableImageViewWallpaper = factory.createImageView(imageViewWallpaper);
        StylableWidget StylableButtonGoBack = factory.createButton(buttonGoBack,goMainpageCommand);

        StylablePage page=new StylablePage();
        page.addWidget(stylableTextViewToolbar);
        page.addWidget(stylableTextViewTitle);
        page.addWidget(stylableImageViewWallpaper);
        page.addWidget(StylableButtonGoBack);
        page.setStyle();

        //reset logo icon : its hide when i use ListActivity
        ImageView imageViewLogo= (ImageView) findViewById(R.id.ViewSchedulePage_iamgeView_logo);
        imageViewLogo.setImageResource(R.drawable.logo);

        //set arrow icon for buttonGoBack
        buttonGoBack.setBackgroundResource(R.drawable.gobackarrow);
        //__________________end of page UI:___________________________




        //patientScheduleListString=new ArrayList<String>();
       // patientScheduleListString.add("1");
       // patientScheduleListString.add("2");

        setPatientScheduleList();
        Log.e(patientScheduleList.size()+"","___yeah");
        setPatientScheduleListString();


        // -- Display mode of the ListView

        ListView listview= getListView();
        listview.setChoiceMode(listview.CHOICE_MODE_NONE);
        //listview.setChoiceMode(listview.CHOICE_MODE_SINGLE);
        //listview.setChoiceMode(listview.CHOICE_MODE_MULTIPLE);
        setListAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,patientScheduleListString));



    }




    public void onListItemClick(ListView parent, View v, int position, long id){
   }


    @Override
    public void onBackPressed() {
        // Do Here what ever you want do on back press;
    }



}
