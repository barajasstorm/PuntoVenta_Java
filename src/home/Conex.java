/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package home;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Conex {
    private String url;
    private String server;
    private String puerto;
    private String bd;
    private String user;
    private String pwd;
    private String driver;
    private String insSql;
    private static Conex instancia;
    private static Connection con;
    
    public static Conex getInstance(){
        
        if(instancia==null){
            instancia = new Conex();
        }
        return instancia;
    }
    private Conex(){
        server = "localhost";
        insSql = "n";
        puerto="5432";
        bd="postgres";
        user="postgres";
        pwd = "password";
        driver="org.postgresql.Driver";
    
        //"jdbc:mysql://localhost:3306/dulceria
        
            url ="jdbc:postgresql://" +server +":" +puerto +"/"+bd;
        
     
        System.out.println(url);
        try {
            Class.forName(driver);
             con = DriverManager.getConnection(url,user,pwd);
        } catch (ClassNotFoundException ex) {
            System.out.println("Error a: " + ex.getMessage());
        } catch (SQLException ex) {
            System.out.println("Error b: " + ex.getMessage());
        }
         
    }
    public Connection getConnection(){
            return con;
        }   
}