# Acceso a Datos: Gestión de Colecciones XML con eXist-db

Este proyecto ha sido desarrollado como parte de la **Actividad 1** de la UT5: "Bases de datos nativas XML".

La aplicación consiste en un sistema en Java que interactúa con el motor de base de datos **eXist-db** para gestionar colecciones y documentos XML de forma organizada.

## Características Principales

* **Uso de la API XML:DB**: Implementación de un _driver_ que maneja la API para manipular la DB XML desde Java.
* **Gestión de Colecciones**: Creación de una colección (`Biblioteca`) dentro del servidor.
* **Creción de ficheros XML**: Creación de archivos XML estructurados (`libros.xml`) dentro de la colección `Biblioteca`.
* **Ejecución de consultas XQuery**: Uso de consultas XQuery para consultar y actualizar el contenido de `libros.xml`.
* **Validación**: El programa muestra por consola todas las operaciones que se realizan sobre la DB.

## Funcionamiento

El programa sigue el siguiente flujo de trabajo:

1. **Conexión**: Se realiza la conexión con `eXist-db` usando el protocolo `xmlrpc` en el puerto `8080`.
2. **Creación de Colección**: Se crea la colección `/db/Biblioteca` si no existe previamente en el servidor.
3. **Inserción**: Se inserta el contenido XML estructurado a traves de la función `insertarLibros()`.
4. **Actualización**: Se ejecuta la función `modificarLibro()` para ejecutar una consulta XQuery que modifica un nodo `libro`.
5. **Consulta**: Se muestran todos los títulos de los libros usando una sentencia XQuery.
6. **Limpieza**: Se elimina el documento `libros.xml` de la colección.
7. **Cierre de conexión**: Se cierra la conexión con la DB usando el método `cerrarConexion()`.


## Instrucciones de Uso

Para ejecutar este programa, es necesario tener instalado y en ejecución el servidor de **eXist-db**. 
Se puede utilizar el fichero `.jar` o el código fuente disponibles en las [_releases_]() del proyecto.

---
Este proyecto sirve como evidencia del aprendizaje sobre la gestión de información jerárquica y semiestructurada, esencial para el desarrollo de sistemas en la asignatura de **Acceso a Datos**.