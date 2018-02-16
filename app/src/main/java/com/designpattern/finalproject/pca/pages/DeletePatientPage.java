package com.designpattern.finalproject.pca.pages;

import android.app.ListActivity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.designpattern.finalproject.pca.Patient;
import com.designpattern.finalproject.pca.R;
import com.designpattern.finalproject.pca.command.AddPatientCommand;
import com.designpattern.finalproject.pca.command.Command;
import com.designpattern.finalproject.pca.command.DeletePatientCommand;
import com.designpattern.finalproject.pca.command.GoToNextPageCommand;
import com.designpattern.finalproject.pca.command.NullCommand;
import com.designpattern.finalproject.pca.databaseManager.DatabasePatientManager;
import com.designpattern.finalproject.pca.theme.StylablePage;
import com.designpattern.finalproject.pca.theme.StylableWidget;
import com.designpattern.finalproject.pca.themeFactory.ThemeFactory;

import java.util.ArrayList;

/**
 * Created by Asus-pc on 2018/01/21.
 */

public class DeletePatientPage extends ListActivity {
    private ArrayList<String> patientsNameList;
    Boolean patientIsSelected=false;
    String selectedPatientName;

    private void setPatientsNameList(){
        SQLiteDatabase database=openOrCreateDatabase("pcaDatabase",MODE_PRIVATE,null);
        DatabasePatientManager dbPatientManager=new DatabasePatientManager();
        dbPatientManager.createPatientTable(database);
        dbPatientManager.createDiseasTable(database);
        patientsNameList=dbPatientManager.getPatientsNameList(database);
        database.close();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deletepatient_page);


        //__________________page UI:___________________________
        ThemeFactory factory =ThemeFactory.instance();

        TextView textViewToolbar= (TextView) findViewById(R.id.DeletePatientPage_textView_toolbar);
        TextView textViewSelectPatient= (TextView) findViewById(R.id.DeletePatientPage_textView_selectPatient);
        TextView textViewTitle= (TextView) findViewById(R.id.DeletePatientPage_textView_title);
        ImageView imageViewWallpaper= (ImageView) findViewById(R.id.DeletePatientPage_imageView_wallpaper);
        final Command nullCommand=new NullCommand();
        Button buttonDeletePatient=(Button)findViewById(R.id.DeletePatientPage_button_DeletePatient);
        final Command goLoginpageCommand=new GoToNextPageCommand(DeletePatientPage.this,LoginPage.class);
        Button buttonGoBack = (Button) findViewById(R.id.DeletePatientPage_button_goBack);


        StylableWidget stylableTextViewToolbar = factory.createTextView(textViewToolbar);
        StylableWidget stylabletextViewSelectPatient = factory.createTextView(textViewSelectPatient);
        StylableWidget stylableTextViewTitle = factory.createTextView(textViewTitle);
        StylableWidget stylableImageViewWallpaper = factory.createImageView(imageViewWallpaper);
        StylableWidget StylableButtonDeletePatient = factory.createButton(buttonDeletePatient,nullCommand);
        StylableWidget StylableButtonGoBack = factory.createButton(buttonGoBack,goLoginpageCommand);

        StylablePage page=new StylablePage();
        page.addWidget(stylableTextViewToolbar);
        page.addWidget(stylabletextViewSelectPatient);
        page.addWidget(stylableTextViewTitle);
        page.addWidget(stylableImageViewWallpaper);
        page.addWidget(StylableButtonDeletePatient);
        page.addWidget(StylableButtonGoBack);
        page.setStyle();

        //reset logo icon : its hide when i use ListActivity
        ImageView imageViewLogo= (ImageView) findViewById(R.id.DeletePatientPage_iamgeView_logo);
        imageViewLogo.setImageResource(R.drawable.logo);

        //set arrow icon for buttonGoBack
        buttonGoBack.setBackgroundResource(R.drawable.gobackarrow);
        //__________________end of page UI:___________________________


        setPatientsNameList();

        // -- Display mode of the ListView

        ListView listview= getListView();
        //	listview.setChoiceMode(listview.CHOICE_MODE_NONE);
        	listview.setChoiceMode(listview.CHOICE_MODE_SINGLE);
        //listview.setChoiceMode(listview.CHOICE_MODE_MULTIPLE);
        setListAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_checked,patientsNameList));




      buttonDeletePatient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
            if(patientsNameList.size()>0) {
                if (patientIsSelected) {
                    SQLiteDatabase database = openOrCreateDatabase("pcaDatabase", MODE_PRIVATE, null);
                    DeletePatientCommand command = new DeletePatientCommand(selectedPatientName, database, DeletePatientPage.this);
                    command.execute();
                    database.close();
                    patientsNameList.remove(selectedPatientName);
                    setListAdapter(new ArrayAdapter<String>(DeletePatientPage.this, android.R.layout.simple_list_item_checked, patientsNameList));

                } else {
                    Toast.makeText(DeletePatientPage.this, "** please select a patient **", Toast.LENGTH_SHORT).show();
                }
            }else{
                Toast.makeText(DeletePatientPage.this, "** there is no patient exists **", Toast.LENGTH_SHORT).show();
            }
            }
        });



    }


    public void onListItemClick(ListView parent, View v, int position, long id){
        CheckedTextView item = (CheckedTextView) v;

        Toast.makeText(this,"patient "+ patientsNameList.get(position) + " selected " , Toast.LENGTH_SHORT).show();

        patientIsSelected=true ;
        selectedPatientName=patientsNameList.get(position);

    }


    @Override
    public void onBackPressed() {
        // Do Here what ever you want do on back press;
    }

}
