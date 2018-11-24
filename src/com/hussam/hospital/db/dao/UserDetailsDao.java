
package com.hussam.hospital.db.dao;

import com.hussam.hospital.db.type.UsersType;
import com.hussam.hospital.db.vo.UserDetailsVo;
import com.hussam.hospital.db.vo.UsersVo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author HA
 */
public class UserDetailsDao extends Dao implements DaoList<UserDetailsVo> {
    
    private static UserDetailsDao udd;
    
    private UserDetailsDao(){};
    public static UserDetailsDao getInstance(){
        if(udd == null ) {
            udd = new UserDetailsDao();
        }
        return udd;
    }

    @Override
    public ArrayList<UserDetailsVo> loadAll() throws Exception {
        Connection con = null;
        PreparedStatement stmt ;
        ResultSet rs;
        UserDetailsVo udv ;
        UsersVo       uv  ;
        ArrayList<UserDetailsVo> list = new ArrayList<>();
        try{
            con =  getConnection();
            String sql ="SELECT users.ID AS UD , users.USER_NAME , users.PASSWORD , users.USER_TYPE , users_details.* FROM users INNER JOIN users_details ON users_details.USER_ID = users.ID  ";
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();
            while(rs.next()){
                uv = new UsersVo();
                uv.setId(rs.getInt("UD"));
                uv.setUserName(rs.getString("USER_NAME"));
                uv.setPassword(rs.getString("PASSWORD"));
                UsersType ut = UsersType.getUserTypeById(rs.getInt("USER_TYPE")); 
                uv.setUserType(ut);

                udv = new UserDetailsVo();
                udv.setUservo(uv);
                udv.setId(rs.getInt("ID"));
                udv.setFirstName(rs.getString("FIRST_NAME"));
                udv.setSecondName(rs.getString("SECOND_NAME"));
                udv.setMobile(rs.getString("MOBILE"));
                
                list.add(udv);
            }
            rs.close();
            stmt.close();
        }catch(Exception ex){
        }finally{
            closeConnection(con);
        }
        return list;
    }    

    @Override
    public int insert(UserDetailsVo udvFrmScreen) throws Exception {
        Connection con = null;
        PreparedStatement stmt = null;
        int count = 0;
        try{
           con = getConnection();
           con.setAutoCommit(false);
           String UserSql = "INSERT INTO users(ID,USER_NAME,PASSWORD,USER_TYPE) VALUES(?,?,?,?)";
           stmt = con.prepareStatement(UserSql);
           stmt.setInt(1,   udvFrmScreen.getUservo().getId());
           stmt.setString(2,udvFrmScreen.getUservo().getUserName());
           stmt.setString(3,udvFrmScreen.getUservo().getPassword());
           stmt.setInt(4,   udvFrmScreen.getUservo().getUserType().getId());
           stmt.executeUpdate();
           
           String uDsql = "INSERT INTO users_details(USER_ID,FIRST_NAME,SECOND_NAME,MOBILE) VALUES(?,?,?,?)";
           stmt = con.prepareStatement(uDsql);
           stmt.setInt(   1, udvFrmScreen.getUservo().getId());
           stmt.setString(2, udvFrmScreen.getFirstName());
           stmt.setString(3, udvFrmScreen.getSecondName());
           stmt.setString(4, udvFrmScreen.getMobile());
           stmt.executeUpdate();
           con.commit();
           count = 1;
        }catch(Exception ex) {
            con.rollback();
        }finally {
            stmt.close();
            closeConnection(con);
        }
        return count;
    }

    @Override
    public int update(UserDetailsVo udvFrmScreen) throws Exception {
        Connection con = null;
        PreparedStatement stmt = null;
        int count = 0;
        try{
           con = getConnection();
           con.setAutoCommit(false);
           String UserSql = "UPDATE users SET USER_NAME = ?,PASSWORD = ?,USER_TYPE = ? WHERE ID = ?";
           stmt = con.prepareStatement(UserSql);
           stmt.setString(1, udvFrmScreen.getUservo().getUserName());
           stmt.setString(2, udvFrmScreen.getUservo().getPassword());
           stmt.setInt(   3, udvFrmScreen.getUservo().getUserType().getId());
           stmt.setInt(   4, udvFrmScreen.getUservo().getId());
           stmt.executeUpdate();
           
           String uDsql = "UPDATE users_details SET FIRST_NAME = ?,SECOND_NAME = ?,MOBILE = ? WHERE USER_ID = ?";
           stmt = con.prepareStatement(uDsql);
           stmt.setString(1, udvFrmScreen.getFirstName());
           stmt.setString(2, udvFrmScreen.getSecondName());
           stmt.setString(3, udvFrmScreen.getMobile());
           stmt.setInt(   4, udvFrmScreen.getUservo().getId());
           stmt.executeUpdate();
           con.commit();
           count = 1;
        }catch(Exception ex) {
            con.rollback();
        }finally {
            stmt.close();
            closeConnection(con);
        }
        return count;
    }

    @Override
    public int delete(UserDetailsVo t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public UserDetailsVo getData(UserDetailsVo t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public UserDetailsVo getDataById(int id) throws Exception {
        Connection con = null;
        PreparedStatement stmt;
        ResultSet rs;
        UserDetailsVo udv = null;
        UsersVo       uv  ;
        try{
            con =  getConnection();
            String sql ="SELECT users.ID AS UD , users.USER_NAME , users.PASSWORD , users.USER_TYPE , users_details.* FROM users INNER JOIN users_details ON users_details.USER_ID = users.ID  where users.ID = ? ";
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            while(rs.next()){
                uv = new UsersVo();
                uv.setId(rs.getInt("UD"));
                uv.setUserName(rs.getString("USER_NAME"));
                uv.setPassword(rs.getString("PASSWORD"));
                UsersType ut = UsersType.getUserTypeById(rs.getInt("USER_TYPE")); 
                uv.setUserType(ut);
                
                udv = new UserDetailsVo();
                udv.setUservo(uv);
                udv.setFirstName(rs.getString("FIRST_NAME"));
                udv.setSecondName(rs.getString("SECOND_NAME"));
                udv.setMobile(rs.getString("MOBILE"));
            }
            rs.close();
            stmt.close();
        }catch(Exception ex){
        }finally{
            closeConnection(con);
        }
        return udv;
    }

    @Override
    public int rowsCount(String where) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
