package com.ciandt.worldwonders.repository;

import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.ciandt.worldwonders.database.DAO;
import com.ciandt.worldwonders.database.WonderDAO;
import com.ciandt.worldwonders.model.Wonder;

import java.util.List;

/**
 * Created by wgomes on 25/08/15.
 */
public class WondersRepository {

    private Context context;
    private List<AsyncTask> tasks;

    @NonNull
    public void getAll(final WonderAllListener wonderAllListener){
        AsyncTask<Void,Void, List<Wonder>> asyncTask = new AsyncTask<Void, Void, List<Wonder>>() {
            @Override
            protected List<Wonder> doInBackground(Void... params) {
                DAO<Wonder> dao = new WonderDAO(context);
                List<Wonder> result = dao.getAll();
                dao.close();
                return result;
            }

            @Override
            protected void onPostExecute(List<Wonder> wonders) {
                super.onPostExecute(wonders);
                wonderAllListener.onWonderAll(null, wonders);
                tasks.remove(this);
            }
        };
        tasks.add(asyncTask);
        asyncTask.execute();
    }

    public interface WonderAllListener{
        void onWonderAll(Exception e, List<Wonder> wonders);
    }

    public void cancel(){
        for(AsyncTask asyncTask : tasks){
            if (!asyncTask.isCancelled()){
                asyncTask.cancel(true);
            }
        }
    }
}
