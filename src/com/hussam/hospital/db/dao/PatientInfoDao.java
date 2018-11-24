/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hussam.hospital.db.dao;

import com.hussam.hospital.db.vo.PatientInfoVo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;

/**
 *
 * @author HA
 */
public class PatientInfoDao extends Dao implements DaoList<PatientInfoVo> {
    
    private static PatientInfoDao pid;
    
    private PatientInfoDao(){};
    public static PatientInfoDao getInstance(){
        if(pid == null ) {
            pid = new PatientInfoDao ();
        }
        return pid;
    }

    @Override
    public ArrayList<PatientInfoVo> loadAll() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int insert(PatientInfoVo pivFrmScreen) throws Exception {
        Connection con = null;
        PreparedStatement stmt;
        int count = 0;
        try{
           con = getConnection();
           String sql = "INSERT INTO patient_info(FIRST_NAME,SECOND_NAME,MOBILE,EMAIL) VALUES(?,?,?,?)";
           stmt = con.prepareStatement(sql);
           stmt.setString(1,pivFrmScreen.getFirstName());
           stmt.setString(2,pivFrmScreen.getSecondName());
           stmt.setString(3,pivFrmScreen.getMobile());
           stmt.setString(4,pivFrmScreen.getEmail());
           count = stmt.executeUpdate();
           stmt.close();
        }catch(Exception ex) {
        }finally {
            closeConnection(con);
        }
        return count;
    }

    @Override
    public int update(PatientInfoVo t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int delete(PatientInfoVo t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PatientInfoVo getData(PatientInfoVo t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PatientInfoVo getDataById(int id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int rowsCount(String where) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
