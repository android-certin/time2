package com.ciandt.worldwonders;

import android.app.Application;


import com.ciandt.worldwonders.database.WondersSQLiteHelper;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by wgomes on 24/08/15.
 */
public class WorldWondersApp  extends Application{

    @Override
    public void onCreate() {
        super.onCreate();

        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder().
                setDefaultFontPath("fonts/Roboto-Thin.ttf").
                setFontAttrId(R.attr.fontPath).build());



        if (!WondersSQLiteHelper.verificaBancoCriado()) {
            WondersSQLiteHelper.copiaBanco(getApplicationContext());
        }


    }
}
