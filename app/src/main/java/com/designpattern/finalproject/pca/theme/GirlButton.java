package com.designpattern.finalproject.pca.theme;

import android.graphics.Color;
import android.widget.Button;

import com.designpattern.finalproject.pca.command.Command;

/**
 * Created by Asus-pc on 2018/01/19.
 */

public class GirlButton extends StylableButton {


    public GirlButton(Button but, Command com) {
        super(but, com);
    }

    @Override
    public void setTextColor() {
        textColor= Color.parseColor("#972a0b");
    }

    @Override
    public void setTextFontSize() {
        textFontSize=14;
    }

    @Override
    public void setBgColor() {
        bgColor=Color.parseColor("#f4acb8");
    }
}
