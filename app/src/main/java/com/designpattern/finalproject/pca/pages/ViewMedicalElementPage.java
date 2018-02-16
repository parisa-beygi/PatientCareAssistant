package com.designpattern.finalproject.pca.pages;

import android.app.ListActivity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Pair;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.designpattern.finalproject.pca.R;
import com.designpattern.finalproject.pca.Schedule;
import com.designpattern.finalproject.pca.command.AddBloodPressureCommand;
import com.designpattern.finalproject.pca.command.AddHeartBeatCommand;
import com.designpattern.finalproject.pca.command.Command;
import com.designpattern.finalproject.pca.command.FindPatientIDCommand;
import com.designpattern.finalproject.pca.command.NullCommand;
import com.designpattern.finalproject.pca.command.ViewBloodPressureCommand;
import com.designpattern.finalproject.pca.command.ViewHeartBeatCommand;
import com.designpattern.finalproject.pca.medical.BloodPressureMedicalElement;
import com.designpattern.finalproject.pca.medical.BloodPressureMedicalRecord;
import com.designpattern.finalproject.pca.medical.DiagramMedicalDrawing;
import com.designpattern.finalproject.pca.medical.HearBeatMedicalRecord;
import com.designpattern.finalproject.pca.medical.HeartBeatMedicalElement;
import com.designpattern.finalproject.pca.medical.MedicalDrawing;
import com.designpattern.finalproject.pca.medical.MedicalElement;
import com.designpattern.finalproject.pca.medical.MedicalRecord;
import com.designpattern.finalproject.pca.medical.TableMedicalDrawing;
import com.designpattern.finalproject.pca.theme.StylablePage;
import com.designpattern.finalproject.pca.theme.StylableWidget;
import com.designpattern.finalproject.pca.themeFactory.ThemeFactory;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;

/**
 * Created by Parisa on 2/14/2018.
 */

public class ViewMedicalElementPage extends ListActivity {


    private boolean type; // type == true : heartbeat
    private boolean model; // model== true : table
    private int patientId;

    private CheckBox checkBoxMedicalElementTypeHeartBeat, checkBoxMedicalElementTypeBloodPresure,
            checkBoxMedicalElementModelTable, checkBoxMedicalElementModelDiagram;


    private ArrayList<String> getHeartBeatMedicalElementsString(ArrayList<HeartBeatMedicalElement> medicalElements){
        ArrayList<String> medicalElementsString = new ArrayList<String>();
        for (Iterator<HeartBeatMedicalElement> it = medicalElements.iterator(); it.hasNext(); ) {
            medicalElementsString.add(it.next().toString());
        }
        return medicalElementsString;
    }

    private ArrayList<String> getBloodpressureMedicalElementsString(ArrayList<BloodPressureMedicalElement> medicalElements){
        ArrayList<String> medicalElementsString = new ArrayList<String>();
        for (Iterator<BloodPressureMedicalElement> it = medicalElements.iterator(); it.hasNext(); ) {
            medicalElementsString.add(it.next().toString());
        }
        return medicalElementsString;
    }



    public void setType(boolean type) {
        this.type = type;
    }

    public void setModel(boolean model) {
        this.model = model;
    }


    public void viewMedicalTypeCheckboxClicked(View view) {
        // Is the view now checked?
        boolean checked = ((CheckBox) view).isChecked();

        // Check which checkbox was clicked
        switch(view.getId()) {

            case R.id.ViewMedicalElementPage_checkBox_medicalType_heartbeat:
                if (checked) {
                    checkBoxMedicalElementTypeBloodPresure.setChecked(false);
                    setType(true);
                }
                else{
                    setType(false);
                    checkBoxMedicalElementTypeBloodPresure.setChecked(true);
                }
                break;

            case R.id.ViewMedicalElementPage_checkBox_medicalType_bloodpressure:
                if (checked) {
                    checkBoxMedicalElementTypeHeartBeat.setChecked(false);
                    setType(false);
                }
                else {
                    setType(true);
                    checkBoxMedicalElementTypeHeartBeat.setChecked(true);
                }
                break;

        }


    }

    public void viewMedicalModelCheckboxClicked(View view) {
        // Is the view now checked?
        boolean checked = ((CheckBox) view).isChecked();

        // Check which checkbox was clicked
        switch(view.getId()) {

            case R.id.ViewMedicalElementPage_checkBox_medicalModel_Table:
                if (checked) {
                    checkBoxMedicalElementModelDiagram.setChecked(false);
                    setModel(true);
                }
                else{
                    setModel(false);
                    checkBoxMedicalElementModelDiagram.setChecked(true);
                }
                break;

            case R.id.ViewMedicalElementPage_checkBox_medicalModel_Diagram:
                if (checked) {
                    checkBoxMedicalElementModelTable.setChecked(false);
                    setModel(false);
                }
                else {
                    setModel(true);
                    checkBoxMedicalElementModelTable.setChecked(true);
                }
                break;

        }


    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewmedicalelement_page);


        //__________________page UI:___________________________
        ThemeFactory factory = ThemeFactory.instance();

        TextView textViewToolbar = (TextView) findViewById(R.id.ViewMedicalElementPage_textView_toolbar);
        ImageView imageViewWallpaper = (ImageView) findViewById(R.id.ViewMedicalElementPage_imageView_wallpaper);

        TextView textViewType = (TextView) findViewById(R.id.ViewMedicalElementPage_textView_medicalType);
        TextView textViewModel = (TextView) findViewById(R.id.ViewMedicalElementPage_textView_medicalModel);

        final Command nullCommand = new NullCommand();
        Button buttonViewMedicalElement = (Button) findViewById(R.id.ViewMedicalElementPage_button_viewMedcalElement);


        StylableWidget stylableTextViewToolbar = factory.createTextView(textViewToolbar);
        StylableWidget stylableimageViewWallpaper = factory.createImageView(imageViewWallpaper);
        StylableWidget stylableTextViewType = factory.createTextView(textViewType);
        StylableWidget stylableTextViewModel = factory.createTextView(textViewModel);
        StylableWidget StylableButtonViewMedicalElement = factory.createButton(buttonViewMedicalElement, nullCommand);

        StylablePage page = new StylablePage();
        page.addWidget(stylableTextViewToolbar);
        page.addWidget(stylableimageViewWallpaper);
        page.addWidget(stylableTextViewType);
        page.addWidget(stylableTextViewModel);
        page.addWidget(StylableButtonViewMedicalElement);

        page.setStyle();


        checkBoxMedicalElementTypeHeartBeat = (CheckBox) findViewById(R.id.ViewMedicalElementPage_checkBox_medicalType_heartbeat);
        checkBoxMedicalElementTypeBloodPresure = (CheckBox) findViewById(R.id.ViewMedicalElementPage_checkBox_medicalType_bloodpressure);

        checkBoxMedicalElementModelTable = (CheckBox) findViewById(R.id.ViewMedicalElementPage_checkBox_medicalModel_Table);
        checkBoxMedicalElementModelDiagram = (CheckBox) findViewById(R.id.ViewMedicalElementPage_checkBox_medicalModel_Diagram);


        //__________________end of page UI:___________________________

        setType(true);
        checkBoxMedicalElementTypeHeartBeat.setChecked(true);

        setModel(true);
        checkBoxMedicalElementModelTable.setChecked(true);


        buttonViewMedicalElement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //___________view selected medical element model____________________



                Toast.makeText(ViewMedicalElementPage.this,"button clicked!", Toast.LENGTH_SHORT).show();

                SQLiteDatabase database=openOrCreateDatabase("pcaDatabase",MODE_PRIVATE,null);
                FindPatientIDCommand findPatientIDCommand=new FindPatientIDCommand(LoginPage.getSelectedPatientName(),database,ViewMedicalElementPage.this);
                findPatientIDCommand.execute();

                patientId=findPatientIDCommand.getPatientID();

//                MedicalRecord medicalRecord;
//                MedicalDrawing medicalDrawing;

//                medicalDrawing = (model == true) ? new TableMedicalDrawing() : new DiagramMedicalDrawing();

                if(type==true){
                    //view heartbeat
                    ViewHeartBeatCommand command = new ViewHeartBeatCommand(patientId, database, ViewMedicalElementPage.this);
                    command.execute();
                    ArrayList<HeartBeatMedicalElement> heartBeatMedicalElements = new ArrayList<>();
                    heartBeatMedicalElements = command.getHeartBeatMedicalElements();

                    if(model==true) {
                        ListView listview = getListView();
                        listview.setChoiceMode(listview.CHOICE_MODE_NONE);
                        //listview.setChoiceMode(listview.CHOICE_MODE_SINGLE);
                        //listview.setChoiceMode(listview.CHOICE_MODE_MULTIPLE);
                        setListAdapter(new ArrayAdapter<String>(ViewMedicalElementPage.this,
                                android.R.layout.simple_list_item_1, getHeartBeatMedicalElementsString(heartBeatMedicalElements)));
                    }
                    /*
                    //___________________test part___________________:

                    for (Iterator<HeartBeatMedicalElement> it = heartBeatMedicalElements.iterator(); it.hasNext(); ) {
                        Toast.makeText(ViewMedicalElementPage.this, it.next().toString() + "" , Toast.LENGTH_SHORT).show();
                    }
                    //___________________end of test part___________________:
                     */
                }else{
                    //view blood pressure
                    ViewBloodPressureCommand command = new ViewBloodPressureCommand(patientId, database, ViewMedicalElementPage.this);
                    command.execute();
                    ArrayList<BloodPressureMedicalElement> bloodPressureMedicalElements = command.getBloodPressureMedicalElements();

                    if(model==true) {
                        ListView listview = getListView();
                        listview.setChoiceMode(listview.CHOICE_MODE_NONE);
                        //listview.setChoiceMode(listview.CHOICE_MODE_SINGLE);
                        //listview.setChoiceMode(listview.CHOICE_MODE_MULTIPLE);
                        setListAdapter(new ArrayAdapter<String>(ViewMedicalElementPage.this,
                                android.R.layout.simple_list_item_1, getBloodpressureMedicalElementsString(bloodPressureMedicalElements)));
                    }

                }

//                medicalRecord.draw();

                database.close();









            }
        });

    }
}
