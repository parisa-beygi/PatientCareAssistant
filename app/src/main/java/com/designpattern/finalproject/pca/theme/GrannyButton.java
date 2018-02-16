package com.designpattern.finalproject.pca.theme;

import android.graphics.Color;
import android.widget.Button;

import com.designpattern.finalproject.pca.command.Command;

/**
 * Created by Asus-pc on 2018/01/19.
 */

public class GrannyButton extends StylableButton {

    public GrannyButton(Button but, Command com) {
        super(but, com);
    }

    @Override
    public void setTextColor() {
        textColor= Color.parseColor("#12561a");
    }

    @Override
    public void setTextFontSize() {
        textFontSize=16;
    }

    @Override
    public void setBgColor() {
        bgColor=Color.parseColor("#fce588");
    }

}
