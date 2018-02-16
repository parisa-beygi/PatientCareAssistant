package com.designpattern.finalproject.pca.theme;

import android.widget.ImageView;

import com.designpattern.finalproject.pca.R;

/**
 * Created by Asus-pc on 2018/01/19.
 */

public class GirlImageView extends StylableImageView {
    public GirlImageView(ImageView imageView) {
        super(imageView);
    }

    @Override
    public void setImagePath() {
    imagePath= R.drawable.girlwallpaper;

        //E:\my_androidStudio_projects\PCA\app\src\main\res\drawable\girlWallpaper.jpg
    }
}
