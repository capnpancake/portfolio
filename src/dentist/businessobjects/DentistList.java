/****************
 * DentistList defines a list of Dentists.
 ****************/
package dentist.businessobjects;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class DentistList {
    public ArrayList<Dentist> dlist = new ArrayList();
    public String ucanaccess = "jdbc:ucanaccess://portfolio/database/dentist/DentistOfficeMDB.mdb";
    
    /******************
     * Constructor fills an ArrayList using the Dentists IDs
     * obtained from the DentistOfficeMDB database.
     ******************/
    public DentistList(){
        try{
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            Connection con = DriverManager.getConnection(ucanaccess);
            Statement stmt = con.createStatement();

            String sql;
            sql =    "select id "
                    +"from Dentists;";
            System.out.println(sql);
            ResultSet rs;
            rs = stmt.executeQuery(sql);

            Dentist d;
            String id;

            while (rs.next()){
                id = rs.getString(1);
                d = new Dentist();
                d.SelectDB(id);
                dlist.add(d);
            }

            con.close();
        }
        catch (ClassNotFoundException | SQLException e){
            System.out.println(e);
        }
    }
    
    /*************
     * Adds a Dentist object to the DentistList.
     * @param dent 
     *************/
    public void addDentist(Dentist dent){
        dlist.add(dent);
    }
    
    /*************
     * Returns a Dentist object from the DentistList at index i.
     * @param i 
     *************/
    public Dentist getDentist(int i){
        return dlist.get(i);
    }
    
    /*************
     * Returns a the size of the DentistList as an integer.
     *************/
    public int getSize(){
        return dlist.size();
    }
    
    /*************
     * Display each Dentist object in the DentistList using a for loop.
     *************/
    public void display(){
        Dentist d;
        for (int i = 0 ; i < dlist.size() ; i++){
            d = dlist.get(i);
            d.display();
            System.out.println("---------------------------");
        }
    }
}
