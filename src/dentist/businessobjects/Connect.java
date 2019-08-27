/******************************
 * This data access object is for establishing a connection with a Microsoft
 * Access database. Instantiate this object in your business objects that call
 * SQL methods to do the database interactions for them.
 ******************************/
package dentist.businessobjects;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Connect {
    
    private String path;
    Connection con;
    Statement stmt;
    
    /***********
     * Constructor takes a String path which will be the path to the desired
     * database.
     * @param maccess
     * @param path
     ***********/
    public Connect(Boolean maccess, String path){
        this.path = path;
        try{
            if (maccess == true){
                Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            }
            con = DriverManager.getConnection(path);
            stmt = con.createStatement();
        }
        catch (Exception e){
            System.out.println(e);
        }
    }
    
    /*******
     *  Begin getters and setters.
     *******/
    public void setPath(String path){
        this.path = path;
    }
    public String getPath(){
        return path;
    }
    //End getters and setters.
    
    
}
