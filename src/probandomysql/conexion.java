/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package probandomysql;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class conexion {
    MysqlDataSource datasource;
    Connection conexion;
    Statement st;
    String estado="Todo OK";
    
    public conexion(){
        datasource = new MysqlDataSource();
        datasource.setUser("root");             //Nombre user database
        datasource.setPassword("");             //Password user database
        datasource.setDatabaseName("projectb"); //Nombre database
        datasource.setServerName("localhost");  //Nombre Server
        try {
            conexion=datasource.getConnection();
            st=conexion.createStatement();
        } catch (SQLException ex) {
            estado=ex.getMessage();
        }
    }
    public void AddUser(String user, String email) throws SQLException{
            
        try{
            st.executeUpdate("INSERT INTO user (username,email) values('"+user+"','"+email+"')");
        }catch(SQLException ex){
            estado= ex.getMessage();
        }
        
    }
    public ResultSet SearchUser(String user){
        ResultSet resultado=null;
        try {
            resultado = st.executeQuery("SELECT * from user WHERE username='"+user+"'");
        } catch (SQLException ex) {
            estado=ex.getMessage();
        }
        return resultado;
    }
    public void ModifyUser(String user,String email){
        try {
            st.executeUpdate("UPDATE user SET email='"+email+"' WHERE username='"+user+"'");
        } catch (SQLException ex) {
            estado = ex.getMessage();
        }
    }
    public void DeleteUser(String user){
        try {
            st.executeUpdate("DELETE FROM user WHERE username ='"+user+"'");
        } catch (SQLException ex) {
            estado= ex.getMessage();
        }
    }
}
