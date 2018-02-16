package com.designpattern.finalproject.pca.theme;

import android.widget.TextView;

/**
 * Created by Asus-pc on 2018/01/19.
 */

public abstract class StylableTextView implements StylableWidget {

    private TextView textView;
    protected int textColor;
    protected int textFontSize;
    protected int bgColor;

    public StylableTextView(TextView textView) {
        this.textView = textView;
    }

    public abstract void setTextColor();
    public abstract void setTextFontSize();
    public abstract void setBgColor();

    private void setTextColor(int color){
        textView.setTextColor(color);
    }

    private void setTextSize(float size){

        textView.setTextSize(size);
    }

    private void setBackgroundColor(int color){

        textView.setBackgroundColor(color);
    }

    public void setStyle(){
        setTextColor();
        setTextFontSize();
        setBgColor();
        setTextColor(textColor);
        setTextSize(textFontSize);
        setBackgroundColor(bgColor);

    }

}
