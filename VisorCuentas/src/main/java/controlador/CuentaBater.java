package controlador;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import modelo.Cuenta;
import net.sf.jasperreports.engine.*;


import java.io.IOException;
import java.net.URL;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;


import java.util.ResourceBundle;




public class CuentaBater extends Conexion implements Initializable {


    // Componentes de javaFX
    public Label labelTitulo;
    public Label labelNom;
    public Label labelTit;
    public Label labelFecAp;
    public Label labelSald;
    public TextField fieldNum;
    public TextField fieldTit;
    public TextField fieldFecAp;
    public TextField fieldSald;
    public Button botonCancela;
    public Button botonAcepta;
    public Button botonBack;
    public Button botonNext;
    public Label labelTitulo1;
    public Button botonNuevo;
    public Pane panelPrincipal;
    public TextField fieldNac;

    //Variables
    ArrayList<Cuenta> inicializacion = new ArrayList();
    int i=0;
    Boolean error1=false;
    Boolean error2=false;
    Boolean error3=false;
    String numero;
    String titulo;
    String fecha;
    String salar;
    String nacionalidad;
    private Statement statement;

    private ResultSet resulSet;


    ModificaCuentas mod= new ModificaCuentas();   //Llama a la clase ModificaCuentas

    public ArrayList<Cuenta> init(){ //Carga los datos en el arraylist

        ArrayList<Cuenta> cuentas= new ArrayList();

        this.conectarBBDD();
        String sql = "Select * From cuentas";


        try {

            statement= this.conexion.createStatement();

            resulSet = statement.executeQuery(sql);

            while(resulSet.next()){
                Cuenta c = new Cuenta();

                c.setNumero(resulSet.getInt("Numero"));
                c.setTitular(resulSet.getString("Titular"));
                c.setNacionalidad(resulSet.getString("Nacionalidad"));
                c.setFecha(resulSet.getString("Fecha"));
                c.setSaldo(resulSet.getDouble("Saldo"));

                cuentas.add(c);

            }
            resulSet.close();
            statement.close();


        } catch (SQLException ex) {
            System.out.println("Excepci??n Sql");
        } finally{

            this.cerrarConexion();
        }

        return cuentas;
    }



    public void initialize(URL url, ResourceBundle resourceBundle) { //Inicializa el programa
        inicializacion = init();
        fieldNum.setEditable(false);
        fieldNum.setText(String.valueOf(inicializacion.get(0).getNumero()));
        fieldFecAp.setEditable(false);
        fieldNac.setEditable(false);
        fieldSald.setEditable(false);
        fieldTit.setEditable(false);
        fieldTit.setText(inicializacion.get(0).getTitular());
        fieldFecAp.setText(String.valueOf(inicializacion.get(0).getFecha()));
        fieldNac.setText(String.valueOf(inicializacion.get(0).getNacionalidad()));
        fieldSald.setText(String.valueOf(inicializacion.get(0).getSaldo()));
        botonBack.setVisible(false);
        botonAcepta.setVisible(false);
    }

    public void accionback() { //acci??n cuando pulsamos el bot??n volver "<<"

        fieldNum.getStylesheets().clear();
        fieldSald.getStylesheets().clear();
        botonAcepta.setVisible(false);
        botonNuevo.setVisible(false);
        botonNext.setVisible(true);
        i--;
        if (i==0||i<0){ //Cuando est?? en el primero de la tabla desactiva el bot??n volver
            i=0;
            fieldNum.setText(String.valueOf(inicializacion.get(i).getNumero()));
            fieldTit.setText(inicializacion.get(i).getTitular());
            fieldFecAp.setText(String.valueOf(inicializacion.get(i).getFecha()));
            fieldNac.setText(String.valueOf(inicializacion.get(i).getNacionalidad()));
            fieldSald.setText(String.valueOf(inicializacion.get(i).getSaldo()));
            botonBack.setVisible(false);
        }else{ //Vuelve uno atr??s y aparece el bot??n volver

            fieldNum.setText(String.valueOf(inicializacion.get(i).getNumero()));
            fieldTit.setText(inicializacion.get(i).getTitular());
            fieldFecAp.setText(String.valueOf(inicializacion.get(i).getFecha()));
            fieldNac.setText(String.valueOf(inicializacion.get(i).getNacionalidad()));
            fieldSald.setText(String.valueOf(inicializacion.get(i).getSaldo()));
        }
    }

    public void accionnext() { //Acci??n cuando pulsamos el bot??n siguiente ">>"

        botonBack.setVisible(true);

        if (i < inicializacion.size() - 1) { //Coge le tama??o y al ser m??s peque??o que el tama??o total entra
            i++; //avanza uno
            fieldNum.setText(String.valueOf(inicializacion.get(i).getNumero()));
            fieldTit.setText(inicializacion.get(i).getTitular());
            fieldFecAp.setText(String.valueOf(inicializacion.get(i).getFecha()));
            fieldNac.setText(String.valueOf(inicializacion.get(i).getNacionalidad()));
            fieldSald.setText(String.valueOf(inicializacion.get(i).getSaldo()));
            if(i == inicializacion.size() - 1){
                botonNuevo.setVisible(true);
                botonNext.setVisible(false);
            }
        }
    }

    public void accionC() { //Acci??n cuando pulsamos el bot??n cancelar
        labelTitulo1.setVisible(false);
        labelTitulo.setVisible(true);
        botonCancela.setVisible(false);
        botonAcepta.setVisible(false);
        botonNext.setVisible(true);
        botonBack.setVisible(true);

        fieldNum.getStylesheets().clear(); //Limpia los datos de los textfield para que aparezcan en blanco
        fieldSald.getStylesheets().clear();
        fieldFecAp.getStylesheets().clear();
        fieldNum.setText(String.valueOf(inicializacion.get(i).getNumero())); //Recupera los datos de lo ??ltimo que haya en el arraylist
        fieldTit.setText(inicializacion.get(i).getTitular());
        fieldFecAp.setText(String.valueOf(inicializacion.get(i).getFecha()));
        fieldNac.setText(String.valueOf(inicializacion.get(i).getNacionalidad()));
        fieldSald.setText(String.valueOf(inicializacion.get(i).getSaldo()));

        botonNext.setVisible(false);
        botonNuevo.setVisible(true);
    }

    public void accionA() { //Acci??n cuando pulsamos el bot??n aceptar

        String estilo = "miestilo.css";

        error1 = false;
        error2 = false;
        error3 = false;

        botonCancela.setVisible(false);
        botonNext.setVisible(false);
        botonBack.setVisible(true);
        botonNuevo.setVisible(true);


        try {
            numero = fieldNum.getText(); //Acepta los n??meros introducidos
            Integer.parseInt(numero);
            fieldNum.getStylesheets().clear();
        } catch (NumberFormatException e) { //Si no es un n??mero
            error1 = true; //control del campo n??mero
            fieldNum.getStylesheets().add(estilo); //Aparecer??n los campos textfield en rojo
            botonAcepta.setVisible(true);
            botonNuevo.setVisible(false);
            botonCancela.setVisible(true);
            botonBack.setVisible(false);
            botonNext.setVisible(false);
        }

        try {
            fecha = fieldFecAp.getText(); //Recoge los datos de fecha introducidos
            String[] v = fecha.split("-"); //los separa en un arrray de string y los separa por "/"


           error2=mod.fecha(v); //Devuelve el booleano que confirma si los campos son correctos (viene de la clase ModificaCuentas)


            fieldFecAp.getStylesheets().clear(); //Si reinserta datos correctos deja de aparecer el fondo rojo

            if (error2) { //Control del campo fecha. Si no es correcto
                fieldFecAp.getStylesheets().add(estilo); //Muestra texfield en rojo
                botonAcepta.setVisible(true);
                botonNuevo.setVisible(false);
                botonCancela.setVisible(true);
                botonBack.setVisible(false);
                botonNext.setVisible(false);
            }
        } catch (NumberFormatException e) { //Si no es un n??mero
            fieldFecAp.getStylesheets().add(estilo); //Muestra textfield en rojo
            error2 = true;
            botonAcepta.setVisible(true);
            botonNuevo.setVisible(false);
            botonCancela.setVisible(true);
            botonBack.setVisible(false);
            botonNext.setVisible(false);
        }

        try {
            nacionalidad=fieldNac.getText();
            titulo = fieldTit.getText();
            fecha = fieldFecAp.getText();
            salar = fieldSald.getText();

            Double.parseDouble(salar);
            fieldSald.getStylesheets().clear();
        } catch (NumberFormatException e) {
            error3 = true; //Controla el saldo
            fieldSald.getStylesheets().add(estilo);
            botonAcepta.setVisible(true);
            botonNuevo.setVisible(false);
            botonCancela.setVisible(true);
            botonBack.setVisible(false);
            botonNext.setVisible(false);
        }

        if (!error1 && !error2 && !error3) { //Si no hay error
            Cuenta c = new Cuenta();
            c.setNumero(Integer.parseInt(numero));
            c.setTitular(titulo);
            c.setNacionalidad(nacionalidad);
            c.setFecha(fecha);
            c.setSaldo(Double.parseDouble(salar));
            //mod.actualiza(inicializacion,numero,titulo,nacionalidad,fecha,salar); //Llama a actualiza (en ModificaCuentas) y a??ade la nueva cuenta.
            mod.insertar(c);
            botonAcepta.setVisible(false);
            botonCancela.setVisible(false);
            labelTitulo1.setVisible(false);
            labelTitulo.setVisible(true);
            fieldTit.setEditable(false);
            fieldNac.setEditable(false);
            fieldFecAp.setEditable(false);
            fieldSald.setEditable(false);
            fieldNum.setEditable(false);
            inicializacion=init();
            i++;
        }

    }



    public void accionNuevo() { //Acci??n si pulsamos sobre bot??n Nuevo
        labelTitulo.setVisible(false);
        labelTitulo1.setVisible(true);
        botonNext.setVisible(false);
        fieldNum.setText("");
        fieldTit.setText("");
        fieldFecAp.setText("");
        fieldNac.setText("");
        fieldSald.setText("");
        botonBack.setVisible(false);
        botonNext.setVisible(false);
        botonNuevo.setVisible(false);
        botonAcepta.setVisible(true);
        botonCancela.setVisible(true);
        fieldNum.setEditable(true);
        fieldNac.setEditable(true);
        fieldSald.setEditable(true);
        fieldFecAp.setEditable(true);
        fieldTit.setEditable(true);
    }

    public void accPDF() {

        JasperReport archivo;


        String path = "ReporteVisor2.jrxml";
       // String path2 = "C:\\Users\\User-01\\JaspersoftWorkspace\\MyReports\\ReporteVisor.jasper";

        try
        {
          archivo = JasperCompileManager.compileReport(path);

            JasperPrint prin = JasperFillManager.fillReport(archivo,null,this.conectarBBDD());

            JasperExportManager.exportReportToPdfFile(prin,"reporte.pdf");
            Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + "reporte.pdf");
        }
        catch (JRException | IOException e)
        {
            e.printStackTrace();
        }
    }

    public void accHTML() {

        JasperReport archivo;

        String path = "ReporteVisor2.jrxml";


        try
        {
            archivo = JasperCompileManager.compileReport(path);

            JasperPrint prin = JasperFillManager.fillReport(archivo,null,this.conectarBBDD());



            JasperExportManager.exportReportToHtmlFile(prin,"reporte.html");

            Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + "reporte.html");
        }
        catch (JRException | IOException e)
        {
            e.printStackTrace();
        }
    }
}

