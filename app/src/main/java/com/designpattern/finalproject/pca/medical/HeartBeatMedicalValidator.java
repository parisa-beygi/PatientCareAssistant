package com.designpattern.finalproject.pca.medical;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Parisa on 2/16/2018.
 */

public class HeartBeatMedicalValidator extends MedicalValidator {

    public HeartBeatMedicalValidator(Context context) {
        super(context);
    }

    @Override
    public boolean validate(int value) {
        if(value > 200 || value < 40) {
            Toast.makeText(context, "Not a valid heartbeat value!", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}
