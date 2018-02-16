package com.designpattern.finalproject.pca.pages;

import android.app.ListActivity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CheckedTextView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.designpattern.finalproject.pca.Patient;
import com.designpattern.finalproject.pca.R;
import com.designpattern.finalproject.pca.command.AddPatientCommand;
import com.designpattern.finalproject.pca.command.Command;
import com.designpattern.finalproject.pca.command.GoToNextPageCommand;
import com.designpattern.finalproject.pca.command.NullCommand;
import com.designpattern.finalproject.pca.databaseManager.DatabasePatientManager;
import com.designpattern.finalproject.pca.theme.StylablePage;
import com.designpattern.finalproject.pca.theme.StylableWidget;
import com.designpattern.finalproject.pca.themeFactory.ThemeFactory;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Asus-pc on 2018/01/21.
 */

public class AddPatientPage extends ListActivity {

    private String[] diseas= Patient.availableDiseas;
    private int[] selectedDiseas;

    EditText editTextName;
    EditText editTextAge;



    private boolean nameIsNull(){
        String name=getName();
        if ((name.equals(""))|| (name.trim().length() == 0)){
            Toast.makeText(this, " please enter name !", Toast.LENGTH_SHORT).show();
            return true;}

        return false;

    }

    private boolean ageIsNull(){
        String age=getAge();
        if ((age.equals(""))|| (age.trim().length() == 0)){
            Toast.makeText(this, " please enter age !", Toast.LENGTH_SHORT).show();
            return true;}

        return false;

    }

    public String getAge(){
        String age= String.valueOf(editTextAge.getText());
        return age;
    }

    public String getName(){
        String name= editTextName.getText().toString();
        return name;
    }

    public ArrayList<String> createDiseasesArrayList(){
        ArrayList<String> specialDiseasesList;
        specialDiseasesList=new ArrayList<String>();

        for(int i=0;i<diseas.length;i++){
            if(selectedDiseas[i]==1){
                specialDiseasesList.add(diseas[i]);
            }
        }

        return specialDiseasesList;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addpatient_page);

        //__________________page UI:___________________________
        ThemeFactory factory =ThemeFactory.instance();

        TextView textViewToolbar= (TextView) findViewById(R.id.AddPatientPage_textView_toolbar);
        TextView textViewName= (TextView) findViewById(R.id.AddPatientPage_textView_name);
        TextView textViewAge= (TextView) findViewById(R.id.AddPatientPage_textView_age);
        TextView textViewDiseas= (TextView) findViewById(R.id.AddPatientPage_textView_diseas);
        TextView textViewTitle= (TextView) findViewById(R.id.AddPatientPage_textView_title);
        ImageView imageViewWallpaper= (ImageView) findViewById(R.id.AddPatientPage_imageView_wallpaper);
        final Command nullCommand=new NullCommand();
        Button buttonAddPatient=(Button)findViewById(R.id.AddPatientPage_button_AddPatient);
        final Command goLoginpageCommand=new GoToNextPageCommand(AddPatientPage.this,LoginPage.class);
        Button buttonGoBack = (Button) findViewById(R.id.AddPatientPage_button_goBack);

        StylableWidget stylableTextViewToolbar = factory.createTextView(textViewToolbar);
        StylableWidget stylableTextViewName = factory.createTextView(textViewName);
        StylableWidget stylableTextViewAge = factory.createTextView(textViewAge);
        StylableWidget stylableTextViewDiseas = factory.createTextView(textViewDiseas);
        StylableWidget stylableTextViewTitle = factory.createTextView(textViewTitle);
        StylableWidget stylableImageViewWallpaper = factory.createImageView(imageViewWallpaper);
        StylableWidget StylableButtonAddPatient = factory.createButton(buttonAddPatient,nullCommand);
        StylableWidget StylableButtonGoBack = factory.createButton(buttonGoBack,goLoginpageCommand);

        StylablePage page=new StylablePage();
        page.addWidget(stylableTextViewToolbar);
        page.addWidget(stylableTextViewName);
        page.addWidget(stylableTextViewAge);
        page.addWidget(stylableTextViewDiseas);
        page.addWidget(stylableTextViewTitle);
        page.addWidget(stylableImageViewWallpaper);
        page.addWidget(StylableButtonAddPatient);
        page.addWidget(StylableButtonGoBack);
        page.setStyle();

        //reset logo icon : its hide when i use ListActivity
        ImageView imageViewLogo= (ImageView) findViewById(R.id.AddPatientPage_imageView_logo);
        imageViewLogo.setImageResource(R.drawable.logo);

        //set arrow icon for buttonGoBack
        buttonGoBack.setBackgroundResource(R.drawable.gobackarrow);

        //__________________end of page UI:___________________________


        // -- Display mode of the ListView

        ListView listview= getListView();
        //	listview.setChoiceMode(listview.CHOICE_MODE_NONE);
        //	listview.setChoiceMode(listview.CHOICE_MODE_SINGLE);
        listview.setChoiceMode(listview.CHOICE_MODE_MULTIPLE);
        setListAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_checked,diseas));






        selectedDiseas=new int[diseas.length];
        for(int i=0;i<diseas.length;i++){selectedDiseas[i]=0;}




        editTextAge= (EditText) findViewById(R.id.AddPatientPage_editText_patientAge);
        editTextName= (EditText) findViewById(R.id.AddPatientPage_editText_name);



        //Button buttonAddPatient=(Button)findViewById(R.id.AddPatientPage_button_AddPatient);
        buttonAddPatient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {

                if (!(nameIsNull()|| ageIsNull())){
                    Patient patient=new Patient(getName(),Integer.parseInt(getAge()),createDiseasesArrayList());
                    SQLiteDatabase database=openOrCreateDatabase("pcaDatabase",MODE_PRIVATE,null);
                    AddPatientCommand command =new AddPatientCommand(patient,database,AddPatientPage.this);
                    command.execute();

                  /* //___________________test part___________________:

                    String query="select id,name,age from patient ;";
                    Cursor cursor = database.rawQuery(query, null);
                    int id;int age;String name;
                    while (cursor.moveToNext()) {
                        id=cursor.getInt(cursor.getColumnIndex("id"));
                        name=cursor.getString(cursor.getColumnIndex("name"));
                        age=cursor.getInt(cursor.getColumnIndex("age"));
                        Toast.makeText(AddPatientPage.this,id+name+age, Toast.LENGTH_SHORT).show();

                    }



                    query="select pid,name from diseases ;";
                    cursor = database.rawQuery(query, null);

                    while (cursor.moveToNext()) {
                        id=cursor.getInt(cursor.getColumnIndex("pid"));
                        name=cursor.getString(cursor.getColumnIndex("name"));
                        Toast.makeText(AddPatientPage.this,"**"+id+name+"**", Toast.LENGTH_SHORT).show();

                    }

                    */


                    database.close();


                }

            }
        });






    }

    public void onListItemClick(ListView parent, View v, int position, long id){
        CheckedTextView item = (CheckedTextView) v;
        Toast.makeText(this, diseas[position] + " checked : " +
                item.isChecked(), Toast.LENGTH_SHORT).show();
                selectedDiseas[position]=(item.isChecked())?1:0;
    }

    @Override
    public void onBackPressed() {
        // Do Here what ever you want do on back press;
    }

    }
