package com.designpattern.finalproject.pca.theme;

import android.widget.ImageView;

import com.designpattern.finalproject.pca.R;

/**
 * Created by Asus-pc on 2018/01/19.
 */

public class GuyImageView extends StylableImageView {
    public GuyImageView(ImageView imageView) {
        super(imageView);
    }

    @Override
    public void setImagePath() {
        imagePath= R.drawable.guywallpaper;
    }
}
