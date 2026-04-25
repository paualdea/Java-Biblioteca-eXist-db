package ut5.act1;

public class Main {
    public static void main(String[] args) {
        // Creamos e incializamos la BD XML
        BD bd = new BD("xmldb:exist://localhost:8080/exist/xmlrpc/db", "admin", "");

    }
}