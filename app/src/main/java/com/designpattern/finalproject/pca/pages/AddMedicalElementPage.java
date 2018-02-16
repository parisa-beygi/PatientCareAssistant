package com.designpattern.finalproject.pca.pages;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.designpattern.finalproject.pca.R;
import com.designpattern.finalproject.pca.command.AddBloodPressureCommand;
import com.designpattern.finalproject.pca.command.AddHeartBeatCommand;
import com.designpattern.finalproject.pca.command.Command;
import com.designpattern.finalproject.pca.command.FindPatientIDCommand;
import com.designpattern.finalproject.pca.command.NullCommand;
import com.designpattern.finalproject.pca.medical.BloodPressureMedicalElement;
import com.designpattern.finalproject.pca.medical.BloodPressureMedicalValidator;
import com.designpattern.finalproject.pca.medical.HeartBeatMedicalElement;
import com.designpattern.finalproject.pca.medical.HeartBeatMedicalValidator;
import com.designpattern.finalproject.pca.medical.MedicalElement;
import com.designpattern.finalproject.pca.medical.MedicalValidator;
import com.designpattern.finalproject.pca.medical.SpecializedMedicalElementVisitor;
import com.designpattern.finalproject.pca.theme.StylablePage;
import com.designpattern.finalproject.pca.theme.StylableWidget;
import com.designpattern.finalproject.pca.themeFactory.ThemeFactory;

import java.util.Calendar;

/**
 * Created by Asus-pc on 2018/02/13.
 */

public class AddMedicalElementPage extends AppCompatActivity {

    //HeartBeat=true BloodPresure=false
    private boolean type;
    private int value;
    private long time;
    private int patientId;

    EditText editTextValue;

    private MedicalValidator valueValidator;

    public String getValue(){
        String value= String.valueOf(editTextValue.getText());
        return value;}

    private boolean valueIsNull(){
        String value=getValue();
        if ((value.equals(""))|| (value.trim().length() == 0)){
            Toast.makeText(this, " please enter value !", Toast.LENGTH_SHORT).show();
            return true;}
        return false;
    }






    public void setType(boolean type) {
        this.type = type;
    }

    //MedicalElementType checkboxes
    private CheckBox checkBoxMedicalElementTypeHeartBeat, checkBoxMedicalElementTypeBloodPresure;

    public void medicalTypeCheckboxClicked(View view) {
        // Is the view now checked?
        boolean checked = ((CheckBox) view).isChecked();

        // Check which checkbox was clicked
        switch(view.getId()) {

            case R.id.AddMedicalElementPage_checkBox_medicalType_heartbeat:
                if (checked) {
                    checkBoxMedicalElementTypeBloodPresure.setChecked(false);
                    setType(true);
                    valueValidator = new HeartBeatMedicalValidator(AddMedicalElementPage.this);
                }
                else{
                    setType(false);
                    checkBoxMedicalElementTypeBloodPresure.setChecked(true);
                    valueValidator = new BloodPressureMedicalValidator(AddMedicalElementPage.this);
                }
                break;

            case R.id.AddMedicalElementPage_checkBox_medicalType_bloodpressure:
                if (checked) {
                    checkBoxMedicalElementTypeHeartBeat.setChecked(false);
                    setType(false);
                    valueValidator = new BloodPressureMedicalValidator(AddMedicalElementPage.this);
                }
                else {
                    setType(true);
                    checkBoxMedicalElementTypeHeartBeat.setChecked(true);
                    valueValidator = new HeartBeatMedicalValidator(AddMedicalElementPage.this);
                }
                break;

        }


    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addmedicalelement_page);



        //__________________page UI:___________________________
        ThemeFactory factory =ThemeFactory.instance();

        TextView textViewToolbar= (TextView) findViewById(R.id.AddMedicalElementPage_textView_toolbar);
        ImageView imageViewWallpaper= (ImageView) findViewById(R.id.ViewMedicalElementPage_imageView_wallpaper);
        TextView textViewType= (TextView) findViewById(R.id.AddMedicalElementPage_textView_medicalType);
        TextView textViewValue= (TextView) findViewById(R.id.AddMedicalElementPage_textView_medicalvalue);
        final Command nullCommand=new NullCommand();
        Button buttonAddMedicalElement = (Button) findViewById(R.id.AddMedicalElementPage_button_addMedcalElement);


        StylableWidget stylableTextViewToolbar = factory.createTextView(textViewToolbar);
        StylableWidget stylableimageViewWallpaper = factory.createImageView(imageViewWallpaper);
        StylableWidget stylableTextViewType = factory.createTextView(textViewType);
        StylableWidget stylableTextViewValue = factory.createTextView(textViewValue);
        StylableWidget StylableButtonAddMedicalElement = factory.createButton(buttonAddMedicalElement,nullCommand);

        StylablePage page=new StylablePage();
        page.addWidget(stylableTextViewToolbar);
        page.addWidget(stylableimageViewWallpaper);
        page.addWidget(stylableTextViewType);
        page.addWidget(stylableTextViewValue);
        page.addWidget(StylableButtonAddMedicalElement);

        page.setStyle();



        checkBoxMedicalElementTypeHeartBeat=(CheckBox)findViewById(R.id.AddMedicalElementPage_checkBox_medicalType_heartbeat);
        checkBoxMedicalElementTypeBloodPresure=(CheckBox)findViewById(R.id.AddMedicalElementPage_checkBox_medicalType_bloodpressure);

        editTextValue=(EditText)findViewById(R.id.AddMedicalElementPage_editText_medicalvalue);


        //__________________end of page UI:___________________________



        setType(true);
        checkBoxMedicalElementTypeHeartBeat.setChecked(true);
        valueValidator = new HeartBeatMedicalValidator(this);



        buttonAddMedicalElement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {




                //___________save alarm in the database____________________



                if (!(valueIsNull())) {

                    value=Integer.parseInt(getValue());
                    if(valueValidator.validate(value)) {

                        SQLiteDatabase database=openOrCreateDatabase("pcaDatabase",MODE_PRIVATE,null);
                        FindPatientIDCommand findPatientIDCommand=new FindPatientIDCommand(LoginPage.getSelectedPatientName(),database,AddMedicalElementPage.this);
                        findPatientIDCommand.execute();

                        patientId=findPatientIDCommand.getPatientID();

                        SpecializedMedicalElementVisitor specializedMedicalElementVisitor = new SpecializedMedicalElementVisitor(database, AddMedicalElementPage.this);

                        final Calendar calendar = Calendar.getInstance();
                        time=calendar.getTimeInMillis();


                        if(type==true){
                            //insert heartbeat
                            HeartBeatMedicalElement heartBeatMedicalElement=new HeartBeatMedicalElement(patientId,time,value);
                            AddHeartBeatCommand command = new AddHeartBeatCommand(heartBeatMedicalElement, database, AddMedicalElementPage.this);
                            command.execute();

                            heartBeatMedicalElement.accept(specializedMedicalElementVisitor);



                            /*
                            //___________________test part___________________:

                            String query="select pid,time,value from heartbeat ;";
                            Cursor cursor = database.rawQuery(query, null);

                            int patientId2;
                            long time2;
                            int value2;

                            String date;


                            while (cursor.moveToNext()) {
                                patientId2 = cursor.getInt(cursor.getColumnIndex("pid"));
                                time2 = cursor.getLong(cursor.getColumnIndex("time"));
                                value2 = cursor.getInt(cursor.getColumnIndex("value"));

                                date=Utility.getDate(time2, "yyyy/MM/dd HH:mm:ss");
                                Toast.makeText(AddMedicalElementPage.this,patientId2+"__"+value2+"__"+date    , Toast.LENGTH_SHORT).show();

                            }



                            //___________________end of test part___________________:
                             */

                        }else{
                            //insert blod pressure
                            //insert heartbeat
                            BloodPressureMedicalElement bloodPressureMedicalElement=new BloodPressureMedicalElement(patientId,time,value);
                            AddBloodPressureCommand command = new AddBloodPressureCommand(bloodPressureMedicalElement, database, AddMedicalElementPage.this);
                            command.execute();
                            bloodPressureMedicalElement.accept(specializedMedicalElementVisitor);
                        }



                        database.close();
                    }

                }else{

                }







            }
        });







    }


}
