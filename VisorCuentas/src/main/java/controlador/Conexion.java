package controlador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author David
 */
public class Conexion {
    protected Connection conexion =null;
    private final String URL_CONEXION = "jdbc:mariadb://localhost:3306/visor";
    private final String USER_BD = "root";
    private final String PASSWORD_BD ="";
    private final String DRIVER = "org.mariadb.jdbc.Driver";

    public Connection conectarBBDD(){
        try{
            Class.forName(DRIVER);
            conexion = DriverManager.getConnection(URL_CONEXION,USER_BD,PASSWORD_BD);


        }catch(SQLException ex){
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE,null,ex);

        }catch (ClassNotFoundException ex) {
            System.out.println("Error al cargar el driver");
            ex.printStackTrace();
        }
        return conexion;
    }
    public void cerrarConexion(){
        try {
            if (!conexion.isClosed()){
                conexion.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


}
