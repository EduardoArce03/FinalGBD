/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.Artista;
import Model.Cancion;
import Repository.CancionRepository;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CancionController {

    private final CancionRepository cancionRepository;

    public CancionController(CancionRepository cancionRepository) {
        this.cancionRepository = cancionRepository;
    }

    public void insertarCancion(String nombre, List<String> generos, Date fechaEstreno,
                                List<String> premios, int ventas, String nombreArtista,
                                String paisArtista, Date fechaNacimientoArtista, String generoArtista) {
        Artista artista = new Artista(nombreArtista, paisArtista, fechaNacimientoArtista, generoArtista);
        Cancion cancion = new Cancion(nombre, generos, fechaEstreno, premios, ventas, artista);
        cancionRepository.insertarCancion(cancion);
    }

    public List<Cancion> consultarTodasCanciones() {
        return cancionRepository.consultarTodasCanciones();
    }

    public List<Cancion> consultarCancionesPorNombre(String nombre) {
        try {
            return cancionRepository.consultarCancionesPorNombre(nombre);
        } catch (ParseException ex) {
            Logger.getLogger(CancionController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void actualizarCancion(String nombre, Cancion nuevaCancion) {
        cancionRepository.actualizarCancion(nombre, nuevaCancion);
    }

    public void eliminarCancion(String nombre) {
        cancionRepository.eliminarCancion(nombre);
    }
}