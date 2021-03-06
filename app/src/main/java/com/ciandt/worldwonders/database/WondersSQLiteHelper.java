package com.ciandt.worldwonders.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by wgomes on 24/08/15.
 */
public class WondersSQLiteHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME="wonders.db";
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_DIRECTORY = "data/data/com.ciandt.worldwonders/databases/";
    private static final String DATABASE_PATH = DATABASE_DIRECTORY + DATABASE_NAME;


    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public WondersSQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static boolean verificaBancoCriado(){

        File filedb = new File(DATABASE_PATH);

        if (filedb.exists()){
            return true;
        }

        return false;
    }

    public static boolean copiaBanco(Context context){

        try {
            InputStream filedb = context.getAssets().open ("database/"+DATABASE_NAME);
            File diretorio = new File(DATABASE_DIRECTORY);
            if (!diretorio.exists()){
                diretorio.mkdirs();
            }

            OutputStream out = new FileOutputStream(new File(DATABASE_PATH));
            byte[] buf = new byte[1024];
            int len;
            while((len=filedb.read(buf))>0){
                out.write(buf,0,len);
            }
            out.close();
            filedb.close();


        } catch (IOException e) {
            e.printStackTrace();
        }


        return false;
    }
}
