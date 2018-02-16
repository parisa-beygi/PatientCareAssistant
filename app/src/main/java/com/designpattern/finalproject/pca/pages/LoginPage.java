package com.designpattern.finalproject.pca.pages;

import android.app.ListActivity;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.designpattern.finalproject.pca.R;
import com.designpattern.finalproject.pca.Utility;
import com.designpattern.finalproject.pca.command.Command;
import com.designpattern.finalproject.pca.command.GoToNextPageCommand;
import com.designpattern.finalproject.pca.command.NullCommand;
import com.designpattern.finalproject.pca.databaseManager.DatabasePatientManager;
import com.designpattern.finalproject.pca.theme.StylablePage;
import com.designpattern.finalproject.pca.theme.StylableWidget;
import com.designpattern.finalproject.pca.themeFactory.ThemeFactory;

import java.util.ArrayList;

public class LoginPage extends ListActivity implements AdapterView.OnItemSelectedListener {

    //get the spinner from the xml.
    private Spinner selectThemeSpinner;
    //create a list of items for the spinner.
    private static final String[] items = new String[]{"Theme:","Girl", "Granny", "Guy"};

    private ArrayList<String> patientsNameList;
    private static String selectedPatientName;
    Boolean patientIsSelected=false;

    //**we dont use style for buttonGoHomePage**
    Button buttonGoHomePage;
    Button buttonGoAddPatientPage;
    Button buttonGoDeletePatientPage;
    TextView textViewToolbar;
    ImageView imageViewWallpaper;
    TextView  textViewSelectPatient;



    public static String getSelectedPatientName() {
        return selectedPatientName;
    }

    private void setStyle(){

        Context sourcePage = LoginPage.this;
        Class<?> addPatientPage = AddPatientPage.class;
        Class<?> deletePatientPage = DeletePatientPage.class;
        final Command goAddPatientPageCommand=new GoToNextPageCommand(sourcePage,addPatientPage);
        final Command goDeletePatientPageCommand=new GoToNextPageCommand(sourcePage,deletePatientPage);
        final Command nullCommand=new NullCommand();


        ThemeFactory factory =ThemeFactory.instance();

        StylableWidget stylableTextViewToolbar = factory.createTextView(textViewToolbar);
        StylableWidget stylableImageViewWallpaper = factory.createImageView(imageViewWallpaper);
        StylableWidget stylableTextViewSelectPatient=factory.createTextView(textViewSelectPatient);
        StylableWidget stylableButtonGoAddPatientPage=factory.createButton(buttonGoAddPatientPage,goAddPatientPageCommand);
        StylableWidget StylableButtonGoDeletePatientPage=factory.createButton(buttonGoDeletePatientPage,goDeletePatientPageCommand);
        StylableWidget StylableButtonGoHomePage=factory.createButton(buttonGoHomePage,nullCommand);


        StylablePage page=new StylablePage();
        page.addWidget(stylableTextViewToolbar);
        page.addWidget(stylableImageViewWallpaper);
        page.addWidget(stylableTextViewSelectPatient);
        page.addWidget(stylableButtonGoAddPatientPage);
        page.addWidget(StylableButtonGoDeletePatientPage);
        page.addWidget(StylableButtonGoHomePage);
        page.setStyle();

        //set arrow icon for buttonGoHomePage
        buttonGoHomePage.setBackgroundResource(R.drawable.gohomebutton);
        buttonGoHomePage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                if(patientsNameList.size()>0) {
                    if (patientIsSelected) {
                        Command command=new GoToNextPageCommand(LoginPage.this,MainPage.class);
                        command.execute();
                    } else {
                        Toast.makeText(LoginPage.this, "** please select a patient **", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(LoginPage.this, "** there is no patient exists **", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


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
        setContentView(R.layout.activity_login_page);


        //__________________page UI:___________________________
        Utility.setFactory(this);
        textViewToolbar= (TextView) findViewById(R.id.LoginPage_textView_toolbar);
        imageViewWallpaper= (ImageView) findViewById(R.id.LoginPage_imageView_wallpaper);
        textViewSelectPatient= (TextView) findViewById(R.id.LoginPage_textView_selectPatient);
        buttonGoAddPatientPage=(Button) findViewById(R.id.LoginPage_button_GoAddPatient);
        buttonGoDeletePatientPage=(Button) findViewById(R.id.LoginPage_button_GoDeletePatient);
        buttonGoHomePage = (Button) findViewById(R.id.LoginPage_button_GoHome);

        setStyle();


        //reset logo icon : its hide when i use ListActivity
        ImageView imageViewLogo= (ImageView) findViewById(R.id.LoginPage_iamgeView_logo);
        imageViewLogo.setImageResource(R.drawable.logo);

        //reset setting icon : its hide when i use ListActivity
        ImageView imageViewSettingIcon= (ImageView) findViewById(R.id.LoginPage_iamgeView_settingIcon);
        imageViewSettingIcon.setImageResource(R.drawable.settingicon);

        //__________________end of page UI:___________________________



        //____________________theme Spinner_____________________________
        //get the spinner from the xml.
        selectThemeSpinner=(Spinner) findViewById(R.id.LoginPage_spinner_selectTheme);
        //create an adapter to describe how the items are displayed, adapters are used in several places in android.
        //There are multiple variations of this, but this is the basic variant.
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        //set the spinners adapter to the previously created one.
        selectThemeSpinner.setAdapter(adapter);
        selectThemeSpinner.setOnItemSelectedListener( this);
        //____________________end of theme Spinner_____________________________


       // Toast.makeText(LoginPage.this, "** hiiiii **", Toast.LENGTH_SHORT).show();


        setPatientsNameList();

        // -- Display mode of the ListView

        ListView listview= getListView();
        //	listview.setChoiceMode(listview.CHOICE_MODE_NONE);
        listview.setChoiceMode(listview.CHOICE_MODE_SINGLE);
        //listview.setChoiceMode(listview.CHOICE_MODE_MULTIPLE);
        setListAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_checked,patientsNameList));



    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (parent.getItemAtPosition(position).equals("Theme:"))
        {}
        else {
            //find the selected theme name:
            String item = parent.getItemAtPosition(position).toString();

            //saved celected theme to the primefaces file:
            Utility.saveTheme(item,this);

            //set Factory
            Utility.setFactory(this);
            setStyle();

            //toast the selected theme name to the user:
            Toast.makeText(parent.getContext(), item+" Theme Selected" , Toast.LENGTH_LONG).show();

           // Command command=new GoToNextPageCommand(LoginPage.this,LoginPage.class);
           // command.execute();
        }
    }



    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        Toast.makeText(parent.getContext(), "no theme selected !", Toast.LENGTH_LONG).show();

    }



    public void onListItemClick(ListView parent, View v, int position, long id){
        CheckedTextView item = (CheckedTextView) v;

        Toast.makeText(this,"patient "+ patientsNameList.get(position) + " selected " , Toast.LENGTH_SHORT).show();

        patientIsSelected=true ;
        selectedPatientName=patientsNameList.get(position);


    }


}
