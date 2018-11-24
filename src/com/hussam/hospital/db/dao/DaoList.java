/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hussam.hospital.db.dao;


import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author HA
 * @param <T>
 */
public interface DaoList<T> {
    public ArrayList<T> loadAll() throws Exception;
    public int insert(T t) throws Exception ;
    public int update(T t) throws Exception;
    public int delete(T t) throws Exception;
    public T getData(T t) throws Exception;
    public T getDataById(int id) throws Exception;
    public int rowsCount(String where) throws Exception;
}
