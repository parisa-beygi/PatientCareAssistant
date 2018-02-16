package com.designpattern.finalproject.pca.command;

import android.content.Context;
import android.content.Intent;

/**
 * Created by Asus-pc on 2018/01/21.
 */

public class GoToNextPageCommand extends Command {

    private final Context sourcePage ;
    private final Class<?>  destinationPage;


    public GoToNextPageCommand(Context sourcePage, Class<?> destinationPage) {
        this.sourcePage = sourcePage;
        this.destinationPage = destinationPage;
    }


    @Override
    public void execute(){
         Intent intent = new Intent(sourcePage,destinationPage);
         sourcePage.startActivity(intent);
    }


}
