package com.designpattern.finalproject.pca.theme;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Asus-pc on 2018/01/19.
 */

public class StylablePage implements StylableWidget {
    private ArrayList<StylableWidget> widgetList;

    public StylablePage(){widgetList= new ArrayList<StylableWidget>();}

    @Override
    public void setStyle() {
        for (Iterator<StylableWidget> it = widgetList.iterator(); it.hasNext();) {
            it.next().setStyle();
        }
    }


    public void addWidget(StylableWidget widget){
        widgetList.add(widget);
    }
}
