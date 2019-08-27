package dentist.businessobjects;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/****************
 * This business object is for defining Patients and their behaviors. Has 
 * methods for retrieving info from and altering data in the DentisOfficeMDB 
 * database.
 ****************/
public class Patient {
    
    //Properties
    private String id, pass, fname, lname, address, email, insco;
    Appointment appt = new Appointment();
    public String ucanaccess = "jdbc:ucanaccess://portfolio/database/dentist/DentistOfficeMDB.mdb";
    
    
    /**************
     * Constructor for building Patients from scratch.
     * @param id
     * @param pw
     * @param fn
     * @param ln
     * @param address
     * @param email
     * @param ins
     **************/
    public Patient(String id, String pw, String fn, String ln, String address, String email, String ins){
        this.id = id;
        pass = pw;
        fname = fn;
        lname = ln;
        this.address = address;
        this.email = email;
        insco = ins;
    }
    public Patient(){
        id = "";
        pass = "";
        fname = "";
        lname = "";
        address = "";
        email = "";
        insco = "";
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
    
    public void setAddress(String addr){address = addr;}
    public String getAddress(){return address;}
    
    public void setEmail(String email){this.email = email;}
    public String getEmail(){return email;}
    
    public void setInsurance(String ins){insco = ins;}
    public String getInsurance(){return insco;}
    
    public Appointment getAppt(){return appt;}
    //</editor-fold>
    
    /*****************
     * Displays attributes to the server log.
     *****************/
    public void display(){
        System.out.println(getId() + " | " + getPass() +" | " + getFname() + " | " + getLname() + " | " + getAddress() + " | " + getEmail() + " | " + getInsurance());
    }
    
    /************************
     * SelectDB() grabs a record from Patients table in the DentistOfficeMDB 
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
                    +"from Patients "
                    +"where patId = '" + id + "';";
            System.out.println(sql);
            ResultSet rs;
            rs = stmt.executeQuery(sql);
            rs.next();
            
                setId(rs.getString(1));
                setPass(rs.getString(2));
                setFname(rs.getString(3));
                setLname(rs.getString(4));
                setAddress(rs.getString(5));
                setEmail(rs.getString(6));
                setInsurance(rs.getString(7));
            
            setAppt();
            con.close();
        }
        catch (ClassNotFoundException | SQLException e){
            System.out.println(e);
        }
        
    }
    
    /****************************
     * Method used for inserting new Patients into the Dentist table of the 
     * DentistOfficeMDB database.
     * @param id
     * @param pass
     * @param fname
     * @param lname
     * @param address
     * @param email
     * @param ins
     ***************************/
    public void InsertDB(String id, String pass, String fname, String lname, String address, String email, String ins){
        
         try{
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            Connection con = DriverManager.getConnection(ucanaccess);
            Statement stmt = con.createStatement();
            
            String sql;
            sql = "insert into Patients " +
                  "values('" +
                  id + "', '" +
                  pass + "', '" +
                  fname + "', '" +
                  lname + "', '" +
                  address + "', '" +
                  email + "', '" +
                  ins + "');";
            System.out.println(sql);
            int n = stmt.executeUpdate(sql);
            if (n > 0){System.out.println(n + " record(s) have been inserted.");}
            else {System.out.println("No records have been inserted.");}
            
                setId(id);
                setPass(pass);
                setFname(fname);
                setLname(lname);
                setAddress(address);
                setEmail(email);
                setInsurance(ins);
            
            con.close();
        }
        catch (ClassNotFoundException | SQLException e){
            System.out.println(e);
        }
        
    }
    
    /*******************
     * Database call to Update the Patient record using the instance's
     * attributes.
     *******************/
    public void UpdateDB(){
        
        try{
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            Connection con = DriverManager.getConnection(ucanaccess);
            Statement stmt = con.createStatement();
            
            String sql;
            sql =   "update Patients " +
                    "set patID = '" + getId() + "', " +
                    "passwd = '" + getPass() + "', " +
                    "firstName = '" + getFname() + "', " +
                    "lastName = '" + getLname() + "', " +
                    "addr = '" + getAddress() + "', " +
                    "email = '" + getEmail() + "', " +
                    "insCo = '" + getInsurance() + "' " +
                    "where patID = '" + getId() + "';";
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
     * Database call to delete the Patient record with the instance's id.
     *******************/
    public void DeleteDB(){
        
        try{
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            Connection con = DriverManager.getConnection(ucanaccess);
            Statement stmt = con.createStatement();
            
            String sql;
            sql = "delete from Patients " +
                  "where patID = '" + id + "';";
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
    
    /****************
     * Assign an Appointment object using the Patient instance's patId.
     ****************/
    public void setAppt(){
        try{
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            Connection con = DriverManager.getConnection(ucanaccess);
            Statement stmt = con.createStatement();
            
            String sql;
            sql =    "select apptDateTime "
                    +"from Appointments "
                    +"where patId = '" + getId() + "';";
            System.out.println(sql);
            ResultSet rs;
            rs = stmt.executeQuery(sql);
            
            rs.next();
            String time;
            time = rs.getString(1);
            appt = new Appointment();
            appt.SelectDB(time);
                
            con.close();
        }
        catch (ClassNotFoundException | SQLException e){
            System.out.println(e);
        }
    }
}