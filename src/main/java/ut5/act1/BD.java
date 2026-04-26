package ut5.act1;

import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.Collection;
import org.xmldb.api.base.Database;
import org.xmldb.api.base.ResourceIterator;
import org.xmldb.api.base.ResourceSet;
import org.xmldb.api.modules.CollectionManagementService;
import org.xmldb.api.modules.XMLResource;
import org.xmldb.api.modules.XQueryService;

public class BD {
    private Collection coleccion;

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
        try {
            // Cargamos el driver
            Class<?> cl = Class.forName("org.exist.xmldb.DatabaseImpl");
            // Creamos un objeto que use el driver que acabamos de importar
            Database database = (Database) cl.getDeclaredConstructor().newInstance();
            // Registramos este driver para hacer conexiones a la BD XML
            DatabaseManager.registerDatabase(database);

            // Configuramos la conexión a la colección raíz
            coleccion = DatabaseManager.getCollection(url, usuario, passwd);
            System.out.println("Conexión a la DB realizada.");

            // Creamos un objeto que sirva para administrar la DB
            CollectionManagementService administrador = (CollectionManagementService) coleccion.getService("CollectionManagementService", "1.0");

            // Creamos la colección principal 'Biblioteca' (si existe no se sobreescribe)
            administrador.createCollection("Biblioteca");
            System.out.println("Colección 'Biblioteca' creada.");
            // Cambiamos la colección actual a 'Biblioteca'
            coleccion = DatabaseManager.getCollection(url + "/Biblioteca",usuario,passwd);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Función para insertar libros.xml en 'Biblioteca'.

     * @param xml
     * Recibe cómo parámetro un String que contiene xml formateado para insertar.
     */
    public void insertarLibros (String xml) {
        try {
            // Creamos un recurso en la colección llamado 'libros.xml'
            XMLResource recurso = (XMLResource) coleccion.createResource("libros.xml", "XMLResource");
            // Establecemos el contenido de este recurso usando la información recibida por parámetro
            recurso.setContent(xml);
            // Almacenamos este nuevo recurso en el servidor de DB XML
            coleccion.storeResource(recurso);
            System.out.println("Creación 'libros.txt' completada.");
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Función para modificar el contenido de un nodo 'libro' de 'libros.xml'.

     * @param sentencia
     * Recibe cómo parámetro la sentencia XQuery para modificar el nodo 'libro'.
     */
    public void modificarLibro (String sentencia) {
        try {
            // Creamos un servicio de tipo XQuery para ejecutar la sentencia sobre el servidor.
            XQueryService servicioActualizacion = (XQueryService) coleccion.getService("XQueryService", "1.0");

            // Ejecutamos la sentencia que recibimos cómo parámetro.
            servicioActualizacion.query(sentencia);
            System.out.println("Modificación nodo 'libro' completada.");
        } catch (Exception e) {
            e.getMessage();
        }
    }

    /**
     * Función que muestra todos los titulos de los nodos 'libro' de 'libros.xml'.
     */
    public void consultarTitulosLibros() {
        try {
            // Creamos un servicio de tipo XQuery para ejecutar la sentencia sobre el servidor.
            XQueryService servicioConsulta = (XQueryService) coleccion.getService("XQueryService", "1.0");

            // Creamos una consulta XQuery para consultar todos los títulos de los nodos 'titulo' dentro de los nodos 'libro'.
            String consulta = "for $libro in /biblioteca/libro return $libro/titulo/text()";
            // Ejecutamos la consulta y la almacenamos, luego creamos un iterador para recorrer todos los recursos.
            ResourceSet resultados = servicioConsulta.query(consulta);
            ResourceIterator iterador = resultados.getIterator();

            System.out.println("\n\t.:TÍTULOS LIBROS:.");
            // Recorremos el iterador con un while para mostrar el contenido (título) de cada nodo.
            int i = 1;
            while (iterador.hasMoreResources()) {
                System.out.println(i + ". " + iterador.nextResource().getContent() + "\n");
                i++;
            }
        } catch (Exception e) {
            e.getMessage();
        }
    }

    /**
     * Función que elimina 'libros.txt' de la colección Biblioteca.
     */
    public void borrarLibros() {
        try {
            // Borramos el recurso 'libros.xml' de la colección (Biblioteca).
            coleccion.removeResource(coleccion.getResource("libros.xml"));
            System.out.println("Borrado fichero 'libros.txt' completado.");
        } catch (Exception e) {
            e.getMessage();
        }
    }

    /**
     * Función para cerrar la conexión con la base de datos XML
     */
    public void cerrarConexion() {
        try {
            // Cerramos la conexión de la coleccion de la DB.
            coleccion.close();
            System.out.println("Conexión con la DB cerrada.");
        } catch (Exception e) {
            e.getMessage();
        }
    }
}