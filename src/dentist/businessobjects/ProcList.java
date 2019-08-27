/****************
 * ProcList defines a list of Procedures.
 ****************/
package dentist.businessobjects;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ProcList {
    public ArrayList<Procedure> plist = new ArrayList();
    public String ucanaccess = "jdbc:ucanaccess://portfolio/database/dentist/DentistOfficeMDB.mdb";
    
    /******************
     * Constructor fills an ArrayList using the procedure codes
     * obtained from the DentistOfficeMDB database.
     ******************/
    public ProcList(){
        try{
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            Connection con = DriverManager.getConnection(ucanaccess);
            Statement stmt = con.createStatement();

            String sql;
            sql =    "select procCode "
                    +"from Procedures;";
            System.out.println(sql);
            ResultSet rs;
            rs = stmt.executeQuery(sql);

            Procedure p;
            String code;

            while (rs.next()){
                code = rs.getString(1);
                p = new Procedure();
                p.SelectDB(code);
                plist.add(p);
            }

            con.close();
        }
        catch (ClassNotFoundException | SQLException e){
            System.out.println(e);
        }
    }
    
    /*************
     * Adds a Procedure object to the ProcList.
     * @param proc 
     *************/
    public void addProc(Procedure proc){
        plist.add(proc);
    }
    
    /*************
     * Returns a Procedure object from the ProcList at index i.
     * @param i 
     *************/
    public Procedure getProc(int i){
        return plist.get(i);
    }
    
    /*************
     * Returns a the size of the ProcList as an integer.
     *************/
    public int getSize(){
        return plist.size();
    }
    
    /*************
     * Display each Procedure object in the ProcList using a for loop.
     *************/
    public void display(){
        Procedure p;
        for (int i = 0 ; i < plist.size() ; i++){
            p = plist.get(i);
            p.display();
            System.out.println("---------------------------");
        }
    }
}
