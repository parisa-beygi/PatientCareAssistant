package com.designpattern.finalproject.pca.medical;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Parisa on 2/16/2018.
 */

public class BloodPressureMedicalValidator extends MedicalValidator {

    public BloodPressureMedicalValidator(Context context) {
        super(context);
    }

    @Override
    public boolean validate(int value) {
        if(value > 22 || value < 7) {
            Toast.makeText(context, "Not a valid blood pressure value!", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}
