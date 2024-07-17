/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.proyectogb;

import Controller.CancionController;
import Model.Artista;
import Model.Cancion;
import Repository.CancionRepository;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class ProyectoGB {

    public static void main(String[] args) {
        // URI de conexión a MongoDB
        String connectionString = "mongodb://localhost:27017";

        // Crear un cliente de MongoDB
        try (MongoClient mongoClient = MongoClients.create(connectionString)) {
            // Seleccionar la base de datos
            MongoDatabase database = mongoClient.getDatabase("musica");

            // Crear instancias de controladores y repositorios
            CancionRepository cancionRepository = new CancionRepository(database);
            CancionController cancionController = new CancionController(cancionRepository);

            // Ejemplo de inserción de una canción
            cancionController.insertarCancion("TE AMO VRG",
                    Arrays.asList("Pop"),
                    new Date(),
                    Arrays.asList("Grammy Mejor Canción"),
                    1500000,
                    "Ed Sheeran",
                    "Reino Unido",
                    new Date(),
                    "Hombre");

            // Ejemplo de consulta de todas las canciones
            /*List<Cancion> canciones = cancionController.consultarTodasCanciones();
            for (Cancion cancion : canciones) {
                System.out.println(cancion.toString());
            }*/
            // Ejemplo de consulta de canciones por nombre
            List<Cancion> cancionesPorNombre = cancionController.consultarCancionesPorNombre("Shape of You");
            for (Cancion cancion : cancionesPorNombre) {
                System.out.println(cancion.toString());
                if (cancion.getArtista() != null) {
                    System.out.println(cancion.getArtista().toString());
                } else {
                    System.out.println("Artista no disponible");
                }
            }

            // Ejemplo de actualización de una canción
            /*cancionController.actualizarCancion("Shape of You",
                    new Cancion("Shape of You",
                            Arrays.asList("Pop", "R&B"),
                            new Date(),
                            Arrays.asList("Grammy Mejor Canción", "Billboard Top 100"),
                            2000000,
                            new Artista("Ed Sheeran",
                                    "Reino Unido",
                                    new Date(),
                                    "Hombre")));
             */
            // Ejemplo de eliminación de una canción
            cancionController.eliminarCancion("Shape of You");

        } catch (Exception e) {
            System.err.println("Error al conectar a MongoDB: " + e.getMessage());
        }
    }
}
