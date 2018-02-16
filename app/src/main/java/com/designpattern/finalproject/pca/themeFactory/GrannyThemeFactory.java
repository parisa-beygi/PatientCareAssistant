package com.designpattern.finalproject.pca.themeFactory;

import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.designpattern.finalproject.pca.command.Command;
import com.designpattern.finalproject.pca.theme.GrannyButton;
import com.designpattern.finalproject.pca.theme.GrannyImageView;
import com.designpattern.finalproject.pca.theme.GrannyTextView;
import com.designpattern.finalproject.pca.theme.StylableButton;
import com.designpattern.finalproject.pca.theme.StylableImageView;
import com.designpattern.finalproject.pca.theme.StylableTextView;

/**
 * Created by Asus-pc on 2018/01/19.
 */

public class GrannyThemeFactory extends ThemeFactory {
    private GrannyThemeFactory() {super();}

    public static ThemeFactory instance(){
        if (uniqueInstance==null) {
            uniqueInstance=new GrannyThemeFactory();}
        return uniqueInstance;
    }

    @Override
    public StylableImageView createImageView(ImageView imageView) {
        return new GrannyImageView(imageView);
    }

    @Override
    public StylableTextView createTextView(TextView textView) {
        return new GrannyTextView(textView);
    }

    @Override
    public StylableButton createButton(Button button, Command command) {
        return new GrannyButton(button, command);
    }
}
