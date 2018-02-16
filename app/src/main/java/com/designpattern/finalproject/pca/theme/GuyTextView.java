package com.designpattern.finalproject.pca.theme;

import android.graphics.Color;
import android.widget.TextView;

/**
 * Created by Asus-pc on 2018/01/19.
 */

public class GuyTextView extends StylableTextView {
    public GuyTextView(TextView textView) {
        super(textView);
    }

    @Override
    public void setTextColor() {
        textColor=Color.parseColor("#000060");
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
