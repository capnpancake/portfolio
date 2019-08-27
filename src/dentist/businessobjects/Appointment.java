/****************
 * This business object is for defining Appointments. Has methods for retrieving
 * info from and altering data in the DentisOfficeMDB database.
 ****************/
package dentist.businessobjects;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Appointment {

    private String time, patId, pat, dentId, dent, procCode, procedure;
    public String ucanaccess = "jdbc:ucanaccess://portfolio/database/dentist/DentistOfficeMDB.mdb";

    // <editor-fold defaultstate="collapsed" desc="Getters and setters. Click on the + sign on the left to edit the code.">
    public void setTime(String date){time = date;}
    public String getTime(){return time;}

    public void setPatId(String id){patId = id;}
    public String getPatId(){return patId;}

    /********************
     * setPatId() provides the first name of the Patient with a specific id from
     * the Patients table in the DentistOfficeMDB database.
     * @param id
     ********************/
    public void setPat(String id){
        try{
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            Connection con = DriverManager.getConnection(ucanaccess);
            Statement stmt = con.createStatement();

            String sql;
            sql =    "select firstname, lastname "
                    +"from Patients "
                    +"where patId = '" + id + "';";
            System.out.println(sql);
            ResultSet rs;
            rs = stmt.executeQuery(sql);
            rs.next();
            pat = rs.getString(1) + " " + rs.getString(2);

            con.close();
        }
        catch (ClassNotFoundException | SQLException e){
            System.out.println(e);
        }
    }
    public String getPat(){return pat;}

    public void setDentId(String id){dentId = id;}
    public String getDentId(){return dentId;}

    /**************************
     * setDentId() provides the first name of the Dentist with a specific id
     * from the Dentists table in the DentistOfficeMDB database.
     * @param id
     **************************/
    public void setDent(String id){
        try{
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            Connection con = DriverManager.getConnection(ucanaccess);
            Statement stmt = con.createStatement();

            String sql;
            sql =    "select lastname "
                    +"from Dentists "
                    +"where id = '" + id + "';";
            System.out.println(sql);
            ResultSet rs;
            rs = stmt.executeQuery(sql);
            rs.next();
            dent = "Dr. " + rs.getString(1);

            con.close();
        }
        catch (ClassNotFoundException | SQLException e){
            System.out.println(e);
        }
    }
    public String getDent(){return dent;}

    public void setProcCode(String code){procCode = code;}
    public String getProcCode(){return procCode;}

    /***************
     * This set method makes a database call using the procedure code as a
     * parameter to retrieve the name and cost of the procedure instead of
     * simply providing the procedure code.
     * @param proc
     ***************/
    public void setProcedure(String proc){
        try{
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            Connection con = DriverManager.getConnection(ucanaccess);
            Statement stmt = con.createStatement();

            String sql;
            sql =    "select procName, cost "
                    +"from Procedures "
                    +"where ProcCode = '" + proc + "';";
            System.out.println(sql);
            ResultSet rs;
            rs = stmt.executeQuery(sql);
            rs.next();
            procedure = rs.getString(1) + " | " + rs.getString(2);

            con.close();
        }
        catch (ClassNotFoundException | SQLException e){
            System.out.println(e);
        }
    }
    public String getProcedure(){return procedure;}
    //</editor-fold>

    /*****************
     * Displays attributes to the server log.
     *****************/
    public void display(){
        System.out.println(getTime() + " | " + getPatId() + " | " + getDentId() + " | " + getProcedure());
    }

    /************************
     * SelectDB() grabs a record from Appointments table in the DentistOfficeMDB
     * database with the date provided and fills out the properties using the
     * setter methods.
     * @param date
     ************************/
    public void SelectDB(String date){

        try{
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            Connection con = DriverManager.getConnection(ucanaccess);
            Statement stmt = con.createStatement();

            String sql;
            sql =    "select * "
                    +"from Appointments "
                    +"where apptDateTime = '" + date + "';";
            System.out.println(sql);
            ResultSet rs;
            rs = stmt.executeQuery(sql);
            rs.next();

            setTime(rs.getString(1));
            setPatId(rs.getString(2));
            setPat(rs.getString(2));
            setDentId(rs.getString(3));
            setDent(rs.getString(3));
            setProcCode(rs.getString(4));
            setProcedure(rs.getString(4));

            con.close();
        }
        catch (ClassNotFoundException | SQLException e){
            System.out.println(e);
        }

    }

    /****************************
     * Method used for inserting new Appointments into the Appointments table of
     * the DentistOfficeMDB database.
     * @param date
     * @param pat
     * @param dent
     * @param proc
     ***************************/
    public void InsertDB(String date, String pat, String dent, String proc){

        try{
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            Connection con = DriverManager.getConnection(ucanaccess);
            Statement stmt = con.createStatement();

            String sql;
            sql = "insert into Appointments " +
                    "values('" +
                    date + "', '" +
                    pat + "', '" +
                    dent + "', '" +
                    proc + "');";
            System.out.println(sql);
            int n = stmt.executeUpdate(sql);
            if (n > 0){System.out.println(n + " record(s) have been inserted.");}
            else {System.out.println("No records have been inserted.");}

            setTime(date);
            setPatId(pat);
            setPat(pat);
            setDentId(dent);
            setDent(dent);
            setProcCode(proc);
            setProcedure(proc);

            con.close();
        }
        catch (ClassNotFoundException | SQLException e){
            System.out.println(e);
        }

    }

    /*******************
     * Database call to Update the Appointment record using the instance's
     * attributes.
     *******************/
    public void UpdateDB(){

        try{
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            Connection con = DriverManager.getConnection(ucanaccess);
            Statement stmt = con.createStatement();

            String sql;
            sql =   "update Appointments " +
                    "set apptDateTime = '" + getTime() + "', " +
                    "patID = '" + getPatId() + "', " +
                    "dentID = '" + getDentId() + "', " +
                    "procCode = '" + getProcCode() + "', " +
                    "where ApptDateTime = '" + getTime() + "';";
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
     * Database call to delete the Patient record with the instance's time.
     *******************/
    public void DeleteDB(){

        try{
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            Connection con = DriverManager.getConnection(ucanaccess);
            Statement stmt = con.createStatement();

            String sql;
            sql = "delete from Appointments " +
                    "where apptDateTime = '" + time + "' AND patId = '" + patId + "';";
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
}
