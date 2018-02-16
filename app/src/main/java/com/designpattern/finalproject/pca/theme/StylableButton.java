package com.designpattern.finalproject.pca.theme;

import android.view.View;
import android.widget.Button;

import com.designpattern.finalproject.pca.command.Command;

/**
 * Created by Asus-pc on 2018/01/19.
 */

public abstract class StylableButton implements StylableWidget {
    private Button button;
    private Command command;
    protected float textFontSize;
    protected int textColor;
    protected int bgColor;

    public StylableButton(Button but , final Command com){
        button=but;
        command=com;
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                command.execute();
            }

        });

    }

    public abstract void setTextColor();
    public abstract void setTextFontSize();
    public abstract void setBgColor();

    private void setTextColor(int color){
        button.setTextColor(color);
    }

    private void setTextSize(float size){
        button.setTextSize(size);
    }

    private void setBackgroundColor(int color){
        button.setBackgroundColor(color);
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
