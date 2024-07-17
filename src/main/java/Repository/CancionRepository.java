/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository;

import Model.Artista;
import Model.Cancion;
import com.mongodb.client.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import org.bson.Document;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CancionRepository {

    private final MongoCollection<Document> collection;

    public CancionRepository(MongoDatabase database) {
        this.collection = database.getCollection("music");
    }

    public void insertarCancion(Cancion cancion) {
        Document doc = new Document("nombre", cancion.getNombre())
                .append("generos", cancion.getGeneros())
                .append("fechaEstreno", cancion.getFechaEstreno())
                .append("premios", cancion.getPremios())
                .append("ventas", cancion.getVentas())
                .append("artista", new Document("nombre", cancion.getArtista().getNombre())
                        .append("pais", cancion.getArtista().getPais())
                        .append("fechaNacimiento", cancion.getArtista().getFechaNacimiento())
                        .append("genero", cancion.getArtista().getGenero()));

        collection.insertOne(doc);
    }

    public List<Cancion> consultarTodasCanciones() {
        List<Cancion> canciones = new ArrayList<>();
        try (MongoCursor<Document> cursor = collection.find().iterator()) {
            while (cursor.hasNext()) {
                Document doc = cursor.next();
                Cancion cancion = documentToCancion(doc); // Puedes decidir cómo manejar el error aquí, como omitir esta canción o registrar el error.
                canciones.add(cancion);
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Maneja cualquier otra excepción que pueda surgir al interactuar con MongoDB.
        }
        return canciones;
    }

    public List<Cancion> consultarCancionesPorNombre(String nombre) throws ParseException {
        List<Cancion> canciones = new ArrayList<>();
        Document query = new Document("nombre", nombre);
        try (MongoCursor<Document> cursor = collection.find(query).iterator()) {
            while (cursor.hasNext()) {
                Document doc = cursor.next();
                Cancion cancion = documentToCancion(doc);
                canciones.add(cancion);
            }
        }
        return canciones;
    }

    public void actualizarCancion(String nombre, Cancion nuevaCancion) {
        Document filter = new Document("nombre", nombre);
        Document update = new Document("$set", new Document("nombre", nuevaCancion.getNombre())
                .append("generos", nuevaCancion.getGeneros())
                .append("fechaEstreno", nuevaCancion.getFechaEstreno())
                .append("premios", nuevaCancion.getPremios())
                .append("ventas", nuevaCancion.getVentas())
                .append("artista", new Document("nombre", nuevaCancion.getArtista().getNombre())
                        .append("pais", nuevaCancion.getArtista().getPais())
                        .append("fechaNacimiento", nuevaCancion.getArtista().getFechaNacimiento())
                        .append("genero", nuevaCancion.getArtista().getGenero())));

        collection.updateOne(filter, update);
    }

    public boolean eliminarCancion(String nombre) {
        try {
            Document query = new Document("nombre", nombre);
            collection.deleteOne(query);
            return true;
        } catch (Exception e) {
            System.out.println("Eror al eliminar " + e.getMessage());
        }
        return false;

    }

    private Cancion documentToCancion(Document doc) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

            Document artistaDoc = (Document) doc.get("artista");
            Artista artista = null;

            if (artistaDoc != null) {
                artista = new Artista(
                        artistaDoc.getString("nombre"),
                        artistaDoc.getString("pais"),
                        artistaDoc.getDate("fechaNacimiento"),
                        artistaDoc.getString("genero"));
            }

            Date fechaEstreno = doc.getDate("fechaEstreno");

            return new Cancion(
                    doc.getString("nombre"),
                    doc.getList("generos", String.class),
                    fechaEstreno,
                    doc.getList("premios", String.class),
                    doc.getInteger("ventas"),
                    artista);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private Date parseDate(String dateStr) {
        if (dateStr == null || dateStr.isEmpty()) {
            return null;
        }

        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            return dateFormat.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    //REPORTES
    public List<Cancion> consultarCancionesPorPeriodo(Date fechaInicio, Date fechaFin) {
        List<Cancion> canciones = new ArrayList<>();
        Document query = new Document("fechaEstreno", new Document("$gte", fechaInicio).append("$lte", fechaFin));
        try (MongoCursor<Document> cursor = collection.find(query).iterator()) {
            while (cursor.hasNext()) {
                Document doc = cursor.next();
                Cancion cancion = documentToCancion(doc);
                canciones.add(cancion);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return canciones;
    }

    public List<Cancion> consultarCancionesPorMayorRecaudacion(int limit) {

        List<Cancion> canciones = new ArrayList<>();
        Document sort = new Document("ventas", -1); // Ordenar por ventas descendente
        try (MongoCursor<Document> cursor = collection.find().sort(sort).limit(limit).iterator()) {
            while (cursor.hasNext()) {
                Document doc = cursor.next();
                Cancion cancion = documentToCancion(doc);
                canciones.add(cancion);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return canciones;
    }

    public List<Cancion> consultarCancionesPorArtista(String nombreArtista) {
        List<Cancion> canciones = new ArrayList<>();
        Document query = new Document("artista.nombre", nombreArtista);
        try (MongoCursor<Document> cursor = collection.find(query).iterator()) {
            while (cursor.hasNext()) {
                Document doc = cursor.next();
                Cancion cancion = documentToCancion(doc);
                canciones.add(cancion);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return canciones;
    }

    public List<Cancion> consultarCancionesPorGenero(String genero) {
        List<Cancion> canciones = new ArrayList<>();
        Document query = new Document("generos", genero);
        try (MongoCursor<Document> cursor = collection.find(query).iterator()) {
            while (cursor.hasNext()) {
                Document doc = cursor.next();
                Cancion cancion = documentToCancion(doc);
                canciones.add(cancion);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return canciones;
    }

}
