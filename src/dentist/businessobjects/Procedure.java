/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dentist.businessobjects;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Procedure {
    
    private String code, name, desc;
    private Double cost;
    public String ucanaccess = "jdbc:ucanaccess://portfolio/database/dentist/DentistOfficeMDB.mdb";
    
    /**************
     * Constructor for building Procedures from scratch.
     * @param code
     * @param name
     * @param desc
     * @param cost
     **************/
    public Procedure(String code, String name, String desc, Double cost){
        this.code = code;
        this.name = name;
        this.desc = desc;
        this.cost = cost;
    }
    public Procedure(){
        code = "";
        name = "";
        desc = "";
        cost = 0.0;
    }
    
    // <editor-fold defaultstate="collapsed" desc="Getters and setters. Click on the + sign on the left to edit the code.">
    public void setCode(String code){this.code = code;}
    public String getCode(){return code;}
    
    public void setName(String name){this.name = name;}
    public String getName(){return name;}
    
    public void setDesc(String desc){this.desc = desc;}
    public String getDesc(){return desc;}
    
    public void setCost(Double cost){this.cost = cost;}
    public Double getCost(){return cost;}
    //</editor-fold>
    
    /*****************
     * Displays attributes to the server log.
     *****************/
    public void display(){
        System.out.println(getCode() + " | " + getName() +" | " + getDesc() + " | " + getCost());
    }
    
    /************************
     * SelectDB() grabs a record from Procedures table in the DentistOfficeMDB 
     * database with the procCode provided and fills out the properties using the
     * setter methods.
     * @param code
     ************************/
    public void SelectDB(String code){
        
        try{
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            Connection con = DriverManager.getConnection(ucanaccess);
            Statement stmt = con.createStatement();
            
            String sql;
            sql =    "select * "
                    +"from Procedures "
                    +"where procCode = '" + code + "';";
            System.out.println(sql);
            ResultSet rs;
            rs = stmt.executeQuery(sql);
            rs.next();
            
                setCode(rs.getString(1));
                setName(rs.getString(2));
                setDesc(rs.getString(3));
                setCost(rs.getDouble(4));
            
            con.close();
        }
        catch (ClassNotFoundException | SQLException e){
            System.out.println(e);
        }
        
    }
    
    /****************************
     * Method used for inserting new Patients into the Procedures table of the 
     * DentistOfficeMDB database.
     * @param code
     * @param name
     * @param desc
     * @param cost
     ***************************/
    public void InsertDB(String code, String name, String desc, Double cost){
        
         try{
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            Connection con = DriverManager.getConnection(ucanaccess);
            Statement stmt = con.createStatement();
            
            String sql;
            sql = "insert into Procedures " +
                  "values('" +
                  code + "', '" +
                  name + "', '" +
                  desc + "', " +
                  cost + ");";
            System.out.println(sql);
            int n = stmt.executeUpdate(sql);
            if (n > 0){System.out.println(n + " record(s) have been inserted.");}
            else {System.out.println("No records have been inserted.");}
            
                setCode(code);
                setName(name);
                setDesc(desc);
                setCost(cost);
            
            con.close();
        }
        catch (ClassNotFoundException | SQLException e){
            System.out.println(e);
        }
        
    }
    
    /*******************
     * Database call to Update the Procedure record using the instance's
     * attributes.
     *******************/
    public void UpdateDB(){
        
        try{
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            Connection con = DriverManager.getConnection(ucanaccess);
            Statement stmt = con.createStatement();
            
            String sql;
            sql =   "update Procedures " +
                    "set procCode = '" + getCode() + "', " +
                    "procName = '" + getName() + "', " +
                    "procDesc = '" + getDesc() + "', " +
                    "cost = " + getCost() + ";";
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
     * Database call to delete the Procedure record with the instance's id.
     *******************/
    public void DeleteDB(){
        
        try{
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            Connection con = DriverManager.getConnection(ucanaccess);
            Statement stmt = con.createStatement();
            
            String sql;
            sql = "delete from Procedures " +
                  "where procCode = '" + code + "';";
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
