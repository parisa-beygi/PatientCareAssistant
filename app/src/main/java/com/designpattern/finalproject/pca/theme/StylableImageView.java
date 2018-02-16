package com.designpattern.finalproject.pca.theme;

import android.widget.ImageView;

/**
 * Created by Asus-pc on 2018/01/19.
 */

public abstract class StylableImageView implements StylableWidget {
    private ImageView imageView ;

    protected int imagePath;

    public StylableImageView(ImageView imageView) {
        this.imageView = imageView;
    }

   public abstract void setImagePath();

    private void setImageResource(int resId){
        imageView.setImageResource(resId);
    }
    public void setStyle(){
       setImagePath();
       setImageResource(imagePath);
    }

}
