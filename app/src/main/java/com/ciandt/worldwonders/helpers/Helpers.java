package com.ciandt.worldwonders.helpers;

import android.content.Context;

/**
 * Created by wgomes on 25/08/15.
 */
public class Helpers {

    public static int getRawResourceID(Context context, String rawResourceName) {
        return context.getResources().getIdentifier(rawResourceName, "raw", context.getPackageName());
    }

    public static int getDrawableResourceID(Context context, String drawableResourceName){
        return context.getResources().getIdentifier(drawableResourceName,"drawable",context.getPackageName());
    }
}
