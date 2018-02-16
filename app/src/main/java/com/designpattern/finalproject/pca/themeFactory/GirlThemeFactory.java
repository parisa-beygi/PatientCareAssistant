package com.designpattern.finalproject.pca.themeFactory;

import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.designpattern.finalproject.pca.command.Command;
import com.designpattern.finalproject.pca.theme.GirlButton;
import com.designpattern.finalproject.pca.theme.GirlImageView;
import com.designpattern.finalproject.pca.theme.GirlTextView;
import com.designpattern.finalproject.pca.theme.StylableButton;
import com.designpattern.finalproject.pca.theme.StylableImageView;
import com.designpattern.finalproject.pca.theme.StylableTextView;

/**
 * Created by Asus-pc on 2018/01/19.
 */

public class GirlThemeFactory extends ThemeFactory {
    private GirlThemeFactory() {super();}

    public static ThemeFactory instance(){
        if (uniqueInstance==null) {
            uniqueInstance=new GirlThemeFactory();}
        return uniqueInstance;
    }

    @Override
    public StylableImageView createImageView(ImageView imageView) {
        return new GirlImageView(imageView);
    }

    @Override
    public StylableTextView createTextView(TextView textView) {
        return new GirlTextView(textView);
    }

    @Override
    public StylableButton createButton(Button button, Command command) {
        return new GirlButton(button ,command);
    }
}
