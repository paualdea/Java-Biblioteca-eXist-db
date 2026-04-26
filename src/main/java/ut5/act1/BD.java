package ut5.act1;

import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.Collection;
import org.xmldb.api.base.Database;
import org.xmldb.api.modules.CollectionManagementService;

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
            // Cargamos el driver
            Class<?> cl = Class.forName("org.exist.xmldb.DatabaseImpl");
            // Creamos un objeto que use el driver que acabamos de importar
            Database database = (Database) cl.getDeclaredConstructor().newInstance();
            // Registramos este driver para hacer conexiones a la BD XML
            DatabaseManager.registerDatabase(database);

            // Configuramos la conexión a la colección raíz
            Collection coleccion = DatabaseManager.getCollection(url, usuario, passwd);
            // Creamos un objeto que sirva para administrar la DB
            CollectionManagementService administrador = (CollectionManagementService) coleccion.getService("CollectionManagementService", "1.0");

            // Creamos la colección principal 'Biblioteca' (si existe no se sobreescribe)
            administrador.createCollection("Biblioteca");
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }


}
