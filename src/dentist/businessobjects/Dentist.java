/****************
 * This business object is for defining Dentists and their behaviors. Has 
 * methods for retrieving info from and altering data in the DentisOfficeMDB 
 * database.
 ****************/
package dentist.businessobjects;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Dentist {
    
    //Properties
    private String id, pass, fname, lname, email, office;
    public String ucanaccess = "jdbc:ucanaccess://portfolio/database/dentist/DentistOfficeMDB.mdb";
    AppointmentList alist = new AppointmentList();
    
    /**************
     * Constructor for building Dentists from scratch.
     * @param id
     * @param pw
     * @param fn
     * @param ln
     * @param email
     * @param off
     **************/
    public Dentist(String id, String pw, String fn, String ln, String email, String off){
        this.id = id;
        pass = pw;
        fname = fn;
        lname = ln;
        this.email = email;
        office = off;
    }
    public Dentist(){
        id = "";
        pass = "";
        fname = "";
        lname = "";
        email = "";
        office = "";
    }
    
    // <editor-fold defaultstate="collapsed" desc="Getters and setters. Click on the + sign on the left to edit the code.">
    public void setId(String id){this.id = id;}
    public String getId(){return id;}
    
    public void setPass(String pw){pass = pw;}
    public String getPass(){return pass;}
    
    public void setFname(String fn){fname = fn;}
    public String getFname(){return fname;}
    
    public void setLname(String ln){lname = ln;}
    public String getLname(){return lname;}
    
    public void setEmail(String email){this.email = email;}
    public String getEmail(){return email;}
    
    public void setOffice(String off){office = off;}
    public String getOffice(){return office;}
    
    public AppointmentList getList(){return alist;}
    //</editor-fold>
    
    /*****************
     * Displays attributes to the server log. Also displays list of Appointments
     * using alist.
     *****************/
    public void display(){
        System.out.println(getId() + " | " + getPass() +" | " + getFname() + " | " + getLname() + " | " + getEmail() + " | " + getOffice());
        alist.display();
    }
    
    /************************
     * SelectDB() grabs a record from Dentists table in the DentistOfficeMDB 
     * database with the ID provided and fills out the properties using the
     * setter methods.
     * @param id 
     ************************/
    public void SelectDB(String id){
        
        try{
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            Connection con = DriverManager.getConnection(ucanaccess);
            Statement stmt = con.createStatement();
            
            String sql;
            sql =    "select * "
                    +"from Dentists "
                    +"where id = '" + id + "';";
            System.out.println(sql);
            ResultSet rs;
            rs = stmt.executeQuery(sql);
            
                rs.next();
                setId(rs.getString(1));
                setPass(rs.getString(2));
                setFname(rs.getString(3));
                setLname(rs.getString(4));
                setEmail(rs.getString(5));
                setOffice(rs.getString(6));
            
            getAppts();
            con.close();
        }
        catch (ClassNotFoundException | SQLException e){
            System.out.println(e);
        }
        
    }
    
    /****************************
     * Method used for inserting new Dentists into the Dentist table of the 
     * DentistOfficeMDB database.
     * @param id
     * @param pass
     * @param fname
     * @param lname
     * @param email
     * @param office 
     ***************************/
    public void InsertDB(String id, String pass, String fname, String lname, String email, String office){
        
         try{
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            Connection con = DriverManager.getConnection(ucanaccess);
            Statement stmt = con.createStatement();
            
            String sql;
            sql = "insert into Dentists " +
                  "values('" +
                  id + "', '" +
                  pass + "', '" +
                  fname + "', '" +
                  lname + "', '" +
                  email + "', '" +
                  office + "');";
            System.out.println(sql);
            int n = stmt.executeUpdate(sql);
            if (n > 0){System.out.println(n + " record(s) have been inserted.");}
            else {System.out.println("No records have been inserted.");}
            
                setId(id);
                setPass(pass);
                setFname(fname);
                setLname(lname);
                setEmail(email);
                setOffice(office);
            
            con.close();
        }
        catch (ClassNotFoundException | SQLException e){
            System.out.println(e);
        }
        
    }
    
    /*******************
     * Database call to Update the Dentist record using the instance's
     * attributes.
     *******************/
    public void UpdateDB(){
        
        try{
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            Connection con = DriverManager.getConnection(ucanaccess);
            Statement stmt = con.createStatement();
            
            String sql;
            sql =   "update Dentists " +
                    "set ID = '" + id + "', " +
                    "passwd = '" + pass + "', " +
                    "FirstName = '" + fname + "', " +
                    "LastName = '" + lname + "', " +
                    "Email = '" + email + "', " +
                    "Office = '" + office + "' " +
                    "where ID = '" + id + "';";
            System.out.println(sql);
            int n = stmt.executeUpdate(sql);
            if (n > 0){System.out.println(n + " record(s) have been updated.");}
            else {System.out.println("No records have been updated.");}
            
            con.close();
        }
        catch (ClassNotFoundException | SQLException e){
            System.out.println(e);
        }
    }
    
    /*******************
     * Database call to delete the record with the Dentist instance's id.
     *******************/
    public void DeleteDB(){
        
        try{
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            Connection con = DriverManager.getConnection(ucanaccess);
            Statement stmt = con.createStatement();
            
            String sql;
            sql = "delete from Dentists " +
                  "where ID = '" + id + "';";
            System.out.println(sql);
            int n = stmt.executeUpdate(sql);
            if (n > 0){System.out.println(n + " record(s) have been deleted.");}
            else {System.out.println("No records have been deleted.");}
            
            con.close();
        }
        catch (ClassNotFoundException | SQLException e){
            System.out.println(e);
        }
        
    }
    
    /****************************
     * getAppts() populates alist with Appointment objects using data from the 
     * Appointments table from the DentistOfficeMDB database. This effectively
     * creates a list of appointments for the Dentist using the getId() method.
     ****************************/
    public void getAppts(){
        try{
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            Connection con = DriverManager.getConnection(ucanaccess);
            Statement stmt = con.createStatement();
            
            String sql;
            sql =    "select apptDateTime "
                    +"from Appointments "
                    +"where dentId = '" + getId() + "';";
            System.out.println(sql);
            ResultSet rs;
            rs = stmt.executeQuery(sql);
            
            Appointment appt;
            String time;
            
            while (rs.next()){
                time = rs.getString(1);
                appt = new Appointment();
                appt.SelectDB(time);
                alist.addAppt(appt);
            }
                
            con.close();
        }
        catch (ClassNotFoundException | SQLException e){
            System.out.println(e);
        }
    }
}