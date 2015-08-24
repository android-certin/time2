package com.ciandt.worldwonders;

import android.app.Application;

import com.ciandt.worldwonders.database.WonderDAO;
import com.ciandt.worldwonders.database.WondersSQLiteHelper;

/**
 * Created by wgomes on 24/08/15.
 */
public class WorldWondersApp  extends Application{

    @Override
    public void onCreate() {
        super.onCreate();

        if (!WondersSQLiteHelper.verificaBancoCriado()){
            WondersSQLiteHelper.copiaBanco(getApplicationContext());
        }


        new WonderDAO(getApplicationContext()).getAll();
    }
}
