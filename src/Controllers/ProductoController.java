/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.Producto;
import java.awt.Window;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import models.*;

/**
 *
 * @author juanba
 */
public class ProductoController extends Producto {

    Postgres postgres = new Postgres();
    Connection connection = postgres.connect();
    ResultSet resultSet = null;
    Statement statement = null;

    public boolean agregarProducto(String nombre, double precioCompra, double precioVenta, int existencias, int stockMinimo) throws SQLException {

        //search for product
        int encontrado = buscarProducto(nombre);
        
        //insert product into database
        if(encontrado != -1) {
            System.out.print("Producto no insertado");
            return true;
        } else {
            //Insert product into database after validation
            String insertSQL = "insert into productos (nombre,preciocompra,precioventa,existencias,stockminimo,activo) values ('" + nombre + "','" + precioCompra + ",'" + precioVenta + "','" + existencias + "','" + stockMinimo + "',1);";
            PreparedStatement preparedStatement =  connection.prepareStatement(insertSQL);
            preparedStatement.executeUpdate();
            return false;
        }
    }
    
    public void borrarProducto(String nombreProd) throws SQLException {
        //search for product
        int encontrado = buscarProducto(nombreProd);
        
        //delete if found
        if(encontrado > 0) {
                    String insertSQL = "DELETE FROM productos WHERE pk_productoid = " + encontrado;
                    PreparedStatement preparedStatement =  connection.prepareStatement(insertSQL);
                    preparedStatement.executeUpdate();
        }    
    }
       
    public int buscarProducto(String nombreProd) throws SQLException {
        String selectSQL = "SELECT * FROM productos";
        statement = connection.createStatement();
        resultSet = statement.executeQuery(selectSQL);
        
        while (resultSet.next()) {
            if(resultSet.getString("nombre").equals(nombreProd)) {
                this.nombre = resultSet.getString("nombre");
                this.precioCompra = resultSet.getDouble("preciocompra");
                this.precioVenta = resultSet.getDouble("precioventa");
                this.existencias = resultSet.getInt("existencias");
                this.stockMin = resultSet.getInt("stockminimo");
                this.activo = resultSet.getInt("activo");              
                return resultSet.getInt("pk_productoid");
            } 
        }
        return -1;
    }
    
    public void modificarProducto(String nombre, double precioCompra, double precioVenta, int existencias, int stockMinimo) throws SQLException {
        int encontrado = buscarProducto(nombre);
        
        //delete if found
        if(encontrado > 0) {
                    System.out.print(encontrado);
                    String insertSQL = "UPDATE productos SET nombre = '"+nombre+"', preciocompra = '"+precioCompra+"', precioventa = '"+precioVenta+"', existencias = '"+existencias+"', stockminimo = '"+stockMinimo+"' WHERE pk_productoid = " + encontrado;
                    PreparedStatement preparedStatement =  connection.prepareStatement(insertSQL);
                    preparedStatement.executeUpdate();
        }
    }
    
    
      
    
    
}
    
