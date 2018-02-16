package com.designpattern.finalproject.pca.medical;

import android.content.Context;

/**
 * Created by Parisa on 2/16/2018.
 */

public abstract class MedicalValidator {
    protected Context context;

    public MedicalValidator(Context context) {
        this.context = context;
    }

    public abstract boolean validate(int value);
}
