
package com.hussam.hospital.db.dao;

import com.hussam.hospital.db.type.UsersType;
import com.hussam.hospital.db.vo.UsersVo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author HA
 */
public class UsersDao extends Dao implements DaoList<UsersVo> {
    private static UsersDao usersDao;
    
    private UsersDao(){};
    public static UsersDao getInstance(){
        if(usersDao == null ) {
            usersDao = new UsersDao();
        }
        return usersDao;
    }

    @Override
    public ArrayList<UsersVo> loadAll() throws Exception {
        Connection con = null;
        PreparedStatement ps ;
        ResultSet rs ;
        ArrayList<UsersVo> list = new ArrayList<>();
        UsersVo uv ;
        try{
            con = getConnection();
            String sql = "SELECT * FROM users";
            ps = con.prepareCall(sql);
            rs = ps.executeQuery();
            
            while(rs.next()){
               
               uv = new UsersVo();
               uv.setId(rs.getInt("id"));
               uv.setUserName(rs.getString("USER_NAME"));
               uv.setPassword(rs.getString("PASSWORD"));
               UsersType ut = UsersType.getUserTypeById(rs.getInt("USER_TYPE"));
               uv.setUserType(ut);
               list.add(uv);
            }
            ps.close();
            rs.close();
        }catch(Exception ex) {
        }finally {
            closeConnection(con);
        }
        return list;
    }

    @Override
    public int insert(UsersVo uvFrmScreen) throws Exception {
        Connection con = null;
        PreparedStatement stmt;
        int count = 0;
        try{
           con = getConnection();
           String sql = "INSERT INTO users(ID,USER_NAME,PASSWORD,USER_TYPE) VALUES(?,?,?,?)";
           stmt = con.prepareStatement(sql);
           stmt.setInt(1,   uvFrmScreen.getId());
           stmt.setString(2,uvFrmScreen.getUserName());
           stmt.setString(3,uvFrmScreen.getPassword());
           stmt.setInt(4,   uvFrmScreen.getUserType().getId());
           count = stmt.executeUpdate();
           stmt.close();
        }catch(Exception ex) {
        }finally {
            closeConnection(con);
        }
        return count;
    }

    @Override
    public int update(UsersVo uv) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int delete(UsersVo uvFrmScreen) throws Exception {
        Connection con = null;
        PreparedStatement stmt ;
        int count = 0;
        try{
           con = getConnection();
           String sql = "DELETE FROM users WHERE ID = ?";
           stmt = con.prepareStatement(sql);
           stmt.setInt(1,   uvFrmScreen.getId());
           count = stmt.executeUpdate();
           stmt.close();
        }catch(Exception ex) {
        }finally {
            closeConnection(con);
        }
        return count;
    }

    @Override
    public UsersVo getData(UsersVo uvFrmScreen) throws Exception {
        Connection con = null;
        PreparedStatement ps;
        ResultSet rs;
        UsersVo usersvo = null;
        try{
            con = getConnection();
            String sql = "SELECT * FROM users WHERE USER_NAME = '" + uvFrmScreen.getUserName() + "' AND PASSWORD = '" + uvFrmScreen.getPassword() + "'";
            ps = con.prepareCall(sql);
            rs = ps.executeQuery();
            
            while(rs.next()){
               usersvo = new UsersVo();
               usersvo.setId(rs.getInt("id"));
               usersvo.setUserName(rs.getString("USER_NAME"));
               usersvo.setPassword(rs.getString("PASSWORD"));
               UsersType ut = UsersType.getUserTypeById(rs.getInt("USER_TYPE"));
               usersvo.setUserType(ut);
            }
            ps.close();
            rs.close();
        }catch(Exception ex) {
        }finally {
            closeConnection(con);
        }
        return usersvo;
    }

    @Override
    public UsersVo getDataById(int id) throws Exception {
        Connection con = null;
        PreparedStatement ps;
        ResultSet rs;
        UsersVo usersvo = null;
        try{
            con = getConnection();
            String sql = "SELECT * FROM users WHERE ID = ?";
            ps = con.prepareCall(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            
            while(rs.next()){
               usersvo = new UsersVo();
               usersvo.setId(rs.getInt("id"));
               usersvo.setUserName(rs.getString("USER_NAME"));
               usersvo.setPassword(rs.getString("PASSWORD"));
               UsersType ut = UsersType.getUserTypeById(rs.getInt("USER_TYPE"));
               usersvo.setUserType(ut);
            }
            ps.close();
            rs.close();
        }catch(Exception ex) {
        }finally {
            closeConnection(con);
        }
        return usersvo;
    }

    @Override
    public int rowsCount(String where) throws Exception {
        Connection con = null;
        PreparedStatement stmt;
        int count = 0;
        try{
           con = getConnection();
           String sql = "SELECT COUNT(ID) AS total FROM users"+ where;
           stmt = con.prepareStatement(sql);
           ResultSet rs = stmt.executeQuery();
           while(rs.next()){
               count = rs.getInt("total");
           }
           stmt.close();
        }catch(Exception ex) {
        }finally {
            closeConnection(con);
        }
        return count;
    }
}
