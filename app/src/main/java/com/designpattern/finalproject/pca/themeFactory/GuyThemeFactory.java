package com.designpattern.finalproject.pca.themeFactory;

import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.designpattern.finalproject.pca.command.Command;
import com.designpattern.finalproject.pca.theme.GuyButton;
import com.designpattern.finalproject.pca.theme.GuyImageView;
import com.designpattern.finalproject.pca.theme.GuyTextView;
import com.designpattern.finalproject.pca.theme.StylableButton;
import com.designpattern.finalproject.pca.theme.StylableImageView;
import com.designpattern.finalproject.pca.theme.StylableTextView;

/**
 * Created by Asus-pc on 2018/01/19.
 */

public class GuyThemeFactory extends ThemeFactory {

    private GuyThemeFactory() {super();}

    public static ThemeFactory instance(){
        if (uniqueInstance==null) {
            uniqueInstance=new GuyThemeFactory();}
        return uniqueInstance;
    }

    @Override
    public StylableImageView createImageView(ImageView imageView) {
        return new GuyImageView(imageView);
    }

    @Override
    public StylableTextView createTextView(TextView textView) {
        return new GuyTextView(textView);
    }

    @Override
    public StylableButton createButton(Button button, Command command) {
        return new GuyButton(button,command);
    }
}
