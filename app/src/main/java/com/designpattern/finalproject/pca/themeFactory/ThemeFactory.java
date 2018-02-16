package com.designpattern.finalproject.pca.themeFactory;


import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.designpattern.finalproject.pca.command.Command;
import com.designpattern.finalproject.pca.theme.StylableButton;
import com.designpattern.finalproject.pca.theme.StylableImageView;
import com.designpattern.finalproject.pca.theme.StylableTextView;

/**
 * Created by Asus-pc on 2018/01/19.
 */

public abstract class ThemeFactory {
    protected static ThemeFactory uniqueInstance = null;

    protected ThemeFactory(){}

    public static ThemeFactory instance(){
        return uniqueInstance;
    }
    public static void setUnigueInstanceToNull(){uniqueInstance = null;}

    abstract public StylableImageView createImageView(ImageView imageView);
    abstract public StylableTextView createTextView(TextView textView);
    abstract public StylableButton createButton(Button button, Command command);



}
