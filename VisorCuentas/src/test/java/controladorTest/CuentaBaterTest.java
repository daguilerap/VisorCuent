package controladorTest;


import controlador.CuentaBater;
import controlador.ModificaCuentas;
import modelo.Cuenta;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.Assert.assertEquals;

public class CuentaBaterTest {
    //private CuentaBater cb;
    private ModificaCuentas mf;
    private Cuenta cu;

    private static String[][] valores() {
        return new String[][] {
                {"10", "Pablo","Español", "2020-10-10","500.98"}, {"11","prueba","Español", "2020-11-10","890.98"},
                {"12", "Bernardo","Español", "2020-10-10","1500.98"}
        };
    }
    @ParameterizedTest(name = "{index}: Prueba inserción")
    @MethodSource("valores")
    public void testAdd(String numero,String nombre,String nacionalidad,String fecha, String saldo) {
        //cb = new CuentaBater();
        mf =new ModificaCuentas();
        cu = new Cuenta((Integer.parseInt(numero)),nombre,nacionalidad,fecha,Double.parseDouble(saldo));
        assertEquals(true, mf.insertar(cu));

    }
}
