package com.designpattern.finalproject.pca.theme;

import android.graphics.Color;
import android.widget.TextView;

/**
 * Created by Asus-pc on 2018/01/19.
 */

public class GirlTextView extends StylableTextView {
    public GirlTextView(TextView textView) {
        super(textView);
    }

    @Override
    public void setTextColor() {
        textColor= Color.parseColor("#d06285");
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
