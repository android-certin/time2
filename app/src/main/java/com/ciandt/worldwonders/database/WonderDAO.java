package com.ciandt.worldwonders.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

import com.ciandt.worldwonders.model.Wonder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wgomes on 24/08/15.
 */
public class WonderDAO implements DAO<Wonder> {

    private final String NOME_TABELA_WONDERS = "wonders";
    private final String NOME_TABELA_BOOKMARKS = "bookmarks";

    private Context context;
    private WondersSQLiteHelper wondersSQLiteHelper;
    private SQLiteDatabase sqLiteDatabase;

    public WonderDAO(Context context) {
        this.context = context;

        wondersSQLiteHelper = new WondersSQLiteHelper(context);
        sqLiteDatabase = wondersSQLiteHelper.getWritableDatabase();
    }

    @Override
    public List<Wonder> getAll() {

        List<Wonder> lista = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.query(NOME_TABELA_WONDERS, null, null, null, null, null, null);

        if (cursor.moveToFirst()) {

            do {

                Wonder wonder = getWonder(cursor);

                lista.add(wonder);

            } while (cursor.moveToNext());
        }

        close();

        return lista;
    }

    @NonNull
    private Wonder getWonder(Cursor cursor) {
        Wonder wonder = new Wonder();
        wonder.setDescription(cursor.getString(cursor.getColumnIndex("description")));
        wonder.setId(cursor.getLong(cursor.getColumnIndex("id")));
        wonder.setLatitude(cursor.getDouble(cursor.getColumnIndex("latitude")));
        wonder.setLongitude(cursor.getDouble(cursor.getColumnIndex("longitude")));
        wonder.setName(cursor.getString(cursor.getColumnIndex("name")));
        wonder.setPhoto(cursor.getString(cursor.getColumnIndex("photo")));
        wonder.setUrl(cursor.getString(cursor.getColumnIndex("url")));
        return wonder;
    }

    @Override
    public Wonder getById(Long id) {

        Cursor cursor = sqLiteDatabase.query(NOME_TABELA_WONDERS, null, "id", new String[]{id.toString()}, null, null, null);

        Wonder wonder = new Wonder();

        if (cursor.moveToFirst()) {
            wonder = getWonder(cursor);
        }

        close();

        return wonder;
    }

    @Override
    public List<Wonder> search(String word) {

        List<Wonder> lista = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.query(NOME_TABELA_WONDERS, null, "name", new String[]{"%"+word+"%"}, null, null, null);

        if (cursor.moveToFirst()) {

            do {

                Wonder wonder = getWonder(cursor);

                lista.add(wonder);

            } while (cursor.moveToNext());
        }

        close();

        return lista;
    }

    @Override
    public void close() {
        sqLiteDatabase.close();
    }

    @Override
    public boolean update(Wonder wonder) {

        ContentValues values = getContentValues(wonder);

        int retorno = sqLiteDatabase.update(NOME_TABELA_WONDERS, values, "id", new String[]{wonder.getId().toString()});

        close();

        return retorno > 0;
    }

    @NonNull
    private ContentValues getContentValues(Wonder wonder) {
        ContentValues values = new ContentValues();
        values.put("name", wonder.getName());
        values.put("description", wonder.getDescription());
        values.put("photo", wonder.getPhoto());
        values.put("url", wonder.getUrl());
        values.put("latitude", wonder.getLatitude());
        values.put("longitude", wonder.getLongitude());
        return values;
    }

    @Override
    public boolean delete(Wonder wonder) {

        int retorno = sqLiteDatabase.delete(NOME_TABELA_WONDERS, "id", new String[]{wonder.getId().toString()});
        close();
        return retorno > 0;
    }

    @Override
    public boolean insert(Wonder wonder) {

        ContentValues values = getContentValues(wonder);

        long retorno = sqLiteDatabase.insert(NOME_TABELA_WONDERS, null, values);

        close();

        return retorno > 0;
    }

    public boolean insertBookMark(Wonder wonder){

        ContentValues values = new ContentValues();
        values.put("idWonders", wonder.getId());

        long retorno = sqLiteDatabase.insert(NOME_TABELA_BOOKMARKS,null,values);

        close();

        return retorno > 0;

    }


    public boolean deleteBookMark(Wonder wonder){

        int retorno = sqLiteDatabase.delete(NOME_TABELA_BOOKMARKS, "idWonders=?", new String[]{wonder.getId().toString()});
        close();
        return retorno > 0;

    }

    public boolean getBookMarkById(Long id) {

        long retorno=0;
        Cursor cursor = sqLiteDatabase.rawQuery("select count(id) from bookmarks where idWonders = " + id + ";", null);
        if (cursor.moveToFirst()){
            retorno = cursor.getLong(cursor.getColumnIndex("count(id)"));
        }
        close();
        return retorno > 0;
    }


}
