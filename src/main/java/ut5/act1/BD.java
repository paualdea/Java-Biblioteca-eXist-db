package ut5.act1;

public class BD {
    // Variables para la conexión a la BD
    private String url;
    private String usuario;
    private String passwd;

    /**
     * Constructor de la clase BD.

     * @param url
     * Recibe la URL de la BD eXist-db
     * @param usuario
     * Recibe el usuario administrador de la BD
     * @param passwd
     * Recibe la passwd (normalmente vacía)
     */
    public BD (String url, String usuario, String passwd) {
        this.url = url;
        this.usuario = usuario;
        this.passwd = passwd;

        try {

        } catch (Exception e) {

        }
    }


}
