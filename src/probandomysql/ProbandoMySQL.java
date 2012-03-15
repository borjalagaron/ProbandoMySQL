/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package probandomysql;


/**
 *
 * @author Borja Lagaron 
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.ResultSet;

public class ProbandoMySQL {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception{
        
        BufferedReader dato=new BufferedReader(new InputStreamReader(System.in));
        int opcion=0;
        imp("Conectando...");
        conexion con=new conexion();
        imp(con.estado);
    
        
        do{
            imp("Escoja opción:");
            imp("1 -Agregar user:");
            imp("2 -Buscar User");
            imp("3 -Modificar ");
            imp("4 -Eliminar");
            imp("5 -Salir");
            opcion = Integer.parseInt(dato.readLine());
            
            switch(opcion){
                case 1: imp("Username...");
                        String user = dato.readLine();
                        imp("email...");
                        String email = dato.readLine();
                        con.AddUser(user, email);
                        imp(con.estado);
                    break;
                    
                case 2: imp("User a buscar...");
                    String searchuser= dato.readLine();
                    ResultSet resultado = con.SearchUser(searchuser);
                    if(resultado.next()){
                        imp("username: "+resultado.getString("username"));
                        imp("Email: "+resultado.getString("email"));
                    }else imp("No encontrado");
                        imp(con.estado);
                    break;
                    
                case 3: imp("User a modificar...");
                    String moduser= dato.readLine();
                    ResultSet res = con.SearchUser(moduser);
                    if(res.next()){
                        imp("Ingrese nuevo mail...");
                        String mail=dato.readLine();
                        con.ModifyUser(moduser, mail);
                    }else imp("No encontrado");
                    imp(con.estado);
                    break;
                    
                case 4: imp("User a eliminar...");
                    String deluser= dato.readLine();
                    ResultSet resu=con.SearchUser(deluser);
                    if(resu.next()){
                        con.DeleteUser(deluser);
                    }else imp("User no existe");
                    imp(con.estado);
                    break;
               
            }
        }while(opcion!=5);
    }
    
    public static void imp(String x){
        System.out.println(x);
    }
}
