 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hussam.hospital.db.dao;

import java.sql.Connection;
import java.sql.DriverManager;
/**
 *
 * @author HA
 */
public class Dao {
    public  Connection getConnection() throws Exception{
        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/hospital", "root", "");
        if (con != null) {
            return con;
        }
        return null;
    }
    public void closeConnection (Connection con) throws Exception {
        if (con != null){
            con.close();
        }
    }
}
