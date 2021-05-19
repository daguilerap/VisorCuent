package modelo;

public class Cuenta { //Atributos
    private Integer numero;
    private String titular;
    private String fecha;
    private String nacionalidad;
    private Double saldo;

    public Cuenta (Integer numero,String titular,String nacionalidad, String fecha ,Double saldo){ //Constructor
        this.numero=numero;
        this.titular=titular;
        this.nacionalidad =nacionalidad;
        this.fecha = fecha;
        this.saldo = saldo;
    }

    public Cuenta() {

    }

    //Getter y Setter


    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getTitular() {
        return titular;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }
}