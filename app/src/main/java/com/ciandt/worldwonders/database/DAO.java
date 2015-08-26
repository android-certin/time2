package com.ciandt.worldwonders.database;

import java.util.List;

/**
 * Created by wgomes on 24/08/15.
 */
public interface DAO<T>{

    public List<T> getAll();

    public T getById(Long id);

    public List<T> search(String word);

    public boolean update(T t);

    public boolean delete(T t);

    public void close();

    public boolean insert(T t);


}
