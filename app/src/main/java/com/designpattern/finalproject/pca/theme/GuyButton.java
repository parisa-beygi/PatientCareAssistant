package com.designpattern.finalproject.pca.theme;

import android.graphics.Color;
import android.widget.Button;

import com.designpattern.finalproject.pca.command.Command;

/**
 * Created by Asus-pc on 2018/01/19.
 */

public class GuyButton extends StylableButton {

    public GuyButton(Button but, Command com) {
        super(but, com);
    }

    @Override
    public void setTextColor() {
        textColor= Color.parseColor("#000060");
    }

    @Override
    public void setTextFontSize() {
        textFontSize=14;
    }

    @Override
    public void setBgColor() {
        bgColor=Color.parseColor("#adbac3");
    }

}
