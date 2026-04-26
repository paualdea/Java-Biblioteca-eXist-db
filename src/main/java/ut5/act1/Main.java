package ut5.act1;

public class Main {
    public static void main(String[] args) {
        // Constantes para los valores a la conexión de la DB
        final String url = "xmldb:exist://localhost:8080/exist/xmlrpc/db";
        final String usuario = "admin", passwd = "";

        // Creamos e inicializamos la BD XML
        BD bd = new BD(url, usuario, passwd);

        // Creamos el XML estructurado para meter en libros.xml
        String estructuraXML =
                "<biblioteca>" +
                "<libro>" +
                "<titulo>Acceso a Datos</titulo>" +
                "<autor>María López</autor>" +
                "<precio>29.95</precio>" +
                "</libro>" +
                "</biblioteca>";

        // Creamos libros.xml con el contenido de 'estructuraXML'
        bd.insertarLibros(estructuraXML);
    }
}