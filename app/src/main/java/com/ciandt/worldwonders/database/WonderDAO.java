package com.ciandt.worldwonders.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.ciandt.worldwonders.model.Wonder;

import java.util.List;

/**
 * Created by wgomes on 24/08/15.
 */
public class WonderDAO implements DAO<Wonder> {

    private final String NOME_TABELA = "wonders";

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
        String SQL = "select * from " + NOME_TABELA;

        sqLiteDatabase.query(NOME_TABELA,null,null,null,null,null,null);

        return null;
    }

    @Override
    public Wonder getById(Long id) {
        String SQL = "select * from " + NOME_TABELA + " where id = "+id;
        return null;
    }

    @Override
    public List<Wonder> search(String word) {
        String SQL = "select * from " + NOME_TABELA + " where name like '%" + word + "'%";
        return null;
    }

    @Override
    public void close() {

    }

    @Override
    public boolean update(Wonder wonder) {
        String SQL = "update " + NOME_TABELA +" set id = " +wonder.getId() + ", name =  "
                +wonder.getName()+ ", description = " +wonder.getDescription() + ", photo = "
                +wonder.getPhoto() + ", url = "+wonder.getUrl() + ", latitude = "+wonder.getLatitude()
                + ", longitude = "+wonder.getLongitude() + " where id = "+ wonder.getId();
        return false;
    }

    @Override
    public boolean delete(Wonder wonder) {
        String SQL = "delete " + NOME_TABELA + " where id = "+ wonder.getId();

        return false;
    }

    @Override
    public boolean insert(Wonder wonder) {
        String SQL = "insert into " + NOME_TABELA +" values ('"+wonder.getName() +"','"+
                wonder.getDescription()+"','"+wonder.getPhoto()+"','"+wonder.getUrl()+"','"+
                wonder.getLatitude()+","+wonder.getLongitude()+")";
        return false;
    }
}
