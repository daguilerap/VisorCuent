package controlador;

import modelo.Cuenta;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ModificaCuentas extends Conexion {

    private PreparedStatement preparedStatment;




    public Boolean fecha(String[] v) { //Control de errores para el campo fecha

         Boolean error2 = false;

        if (v.length != 3) {
            error2 = true;
        } else {
            if (Integer.parseInt(v[2]) > 31 && Integer.parseInt(v[0]) > 1) { //Controla que el día no sea menor que 1 ni mayor que 31
                error2 = true;
            }
            if (Integer.parseInt(v[1]) > 12 && Integer.parseInt(v[1]) > 1) { //Controla que el mes no sea menor que 1 ni mayor que 12
                error2 = true;
            }
            if (v[0].length() != 4) { //Controla que el año no sea distinto de 4 dígitos.
                error2 = true;
            }
        }


    return error2;

    }
    public void actualiza(ArrayList<Cuenta> prueba,String numero,String titulo,String nacionalidad,String fecha,String salar){ //Añade una nueva cuenta a la aplicación.
        prueba.add(new Cuenta(Integer.parseInt(numero), titulo,nacionalidad,fecha, Double.parseDouble(salar)));


    }

    public boolean insertar(Cuenta cuenta) {
        boolean comprueba;

        try {
            this.conectarBBDD();
            String sql="INSERT INTO cuentas (Numero,Titular,Nacionalidad,Fecha,Saldo) VALUES(?,?,?,?,?)";
            preparedStatment =this.conexion.prepareStatement(sql);

            preparedStatment.setInt(1, cuenta.getNumero());
            preparedStatment.setString(2, cuenta.getTitular());
            preparedStatment.setString(3, cuenta.getNacionalidad());
            preparedStatment.setString(4, cuenta.getFecha());
            preparedStatment.setDouble(5, cuenta.getSaldo());

            preparedStatment.executeUpdate();

            preparedStatment.close();


            comprueba=true;

        } catch (SQLException ex) {
            System.out.println("Numero repetido");
            comprueba=false;

        }finally{
            this.cerrarConexion();
        }


        return comprueba;
    }
}
