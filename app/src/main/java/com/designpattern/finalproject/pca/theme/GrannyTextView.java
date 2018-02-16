package com.designpattern.finalproject.pca.theme;

import android.graphics.Color;
import android.widget.TextView;

/**
 * Created by Asus-pc on 2018/01/19.
 */

public class GrannyTextView extends StylableTextView {
    public GrannyTextView(TextView textView) {
        super(textView);
    }

    @Override
    public void setTextColor() {
        textColor= Color.parseColor("#972a0b");
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
