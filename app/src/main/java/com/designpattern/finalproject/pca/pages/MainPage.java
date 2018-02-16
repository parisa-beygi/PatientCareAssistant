package com.designpattern.finalproject.pca.pages;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.designpattern.finalproject.pca.R;
import com.designpattern.finalproject.pca.command.Command;
import com.designpattern.finalproject.pca.command.GoToNextPageCommand;
import com.designpattern.finalproject.pca.theme.StylableImageView;
import com.designpattern.finalproject.pca.theme.StylablePage;
import com.designpattern.finalproject.pca.theme.StylableTextView;
import com.designpattern.finalproject.pca.theme.StylableWidget;
import com.designpattern.finalproject.pca.themeFactory.ThemeFactory;

/**
 * Created by Asus-pc on 2018/01/20.
 */

public class MainPage extends AppCompatActivity {
    private String theme ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        //__________________page UI:___________________________
        ThemeFactory factory =ThemeFactory.instance();

        TextView textViewToolbar= (TextView) findViewById(R.id.MainPage_textView_toolbar);
        ImageView imageViewWallpaper= (ImageView) findViewById(R.id.MainPage_imageView_wallpaper);
        final Command goAddSchedulePageCommand=new GoToNextPageCommand(MainPage.this,AddScedulePage.class);
        Button buttonGoAddSchedulePage = (Button) findViewById(R.id.MainPage_button_AddSchedule);
        final Command goViewSchedulePageCommand=new GoToNextPageCommand(MainPage.this,ViewSchedulePage.class);
        Button buttonGoViewSchedulePage = (Button) findViewById(R.id.MainPage_button_ViewSchedule);
        final Command goAddMedicalElementPageCommand=new GoToNextPageCommand(MainPage.this,AddMedicalElementPage.class);
        Button buttonGoAddMedicalElementPage = (Button) findViewById(R.id.MainPage_button_AddMedicalElement);
        final Command goViewMedicalElementPageCommand=new GoToNextPageCommand(MainPage.this,ViewMedicalElementPage.class);
        Button buttonGoViewMedicalElementPage = (Button) findViewById(R.id.MainPage_button_ViewMedicalElement);

        StylableWidget stylableTextViewToolbar = factory.createTextView(textViewToolbar);
        StylableWidget stylableimageViewWallpaper = factory.createImageView(imageViewWallpaper);
        StylableWidget StylableButtonGoAddSchedulePage = factory.createButton(buttonGoAddSchedulePage,goAddSchedulePageCommand);
        StylableWidget StylableButtonGoViewSchedulePage = factory.createButton(buttonGoViewSchedulePage,goViewSchedulePageCommand);
        StylableWidget StylableButtonGoAddMedicalElementPage = factory.createButton(buttonGoAddMedicalElementPage,goAddMedicalElementPageCommand);
        StylableWidget StylableButtonGoViewMedicalElementPage = factory.createButton(buttonGoViewMedicalElementPage,goViewMedicalElementPageCommand);

        StylablePage page=new StylablePage();
        page.addWidget(stylableTextViewToolbar);
        page.addWidget(stylableimageViewWallpaper);
        page.addWidget(StylableButtonGoAddSchedulePage);
        page.addWidget(StylableButtonGoViewSchedulePage);
        page.addWidget(StylableButtonGoAddMedicalElementPage);
        page.addWidget(StylableButtonGoViewMedicalElementPage);

        page.setStyle();
        //__________________end of page UI:___________________________




    }


    }
