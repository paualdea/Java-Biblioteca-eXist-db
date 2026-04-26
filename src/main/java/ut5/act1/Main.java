package ut5.act1;

public class Main {
    public static void main(String[] args) {
        // Constantes para los valores a la conexión de la DB
        final String url = "xmldb:exist://localhost:8080/exist/xmlrpc/db";
        final String usuario = "admin", passwd = "";

        // Creamos e incializamos la BD XML
        BD bd = new BD(url, usuario, passwd);


    }
}