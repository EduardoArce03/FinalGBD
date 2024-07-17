/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import org.bson.Document;
import java.util.Date;
import java.util.List;

public class Cancion {
    private String nombre;
    private List<String> generos;
    private Date fechaEstreno;
    private List<String> premios;
    private int ventas;
    private Artista artista;

    // Constructor, getters y setters

    public Cancion(String nombre, List<String> generos, Date fechaEstreno, List<String> premios, int ventas, Artista artista) {
        this.nombre = nombre;
        this.generos = generos;
        this.fechaEstreno = fechaEstreno;
        this.premios = premios;
        this.ventas = ventas;
        this.artista = artista;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<String> getGeneros() {
        return generos;
    }

    public void setGeneros(List<String> generos) {
        this.generos = generos;
    }

    public Date getFechaEstreno() {
        return fechaEstreno;
    }

    public void setFechaEstreno(Date fechaEstreno) {
        this.fechaEstreno = fechaEstreno;
    }

    public List<String> getPremios() {
        return premios;
    }

    public void setPremios(List<String> premios) {
        this.premios = premios;
    }

    public int getVentas() {
        return ventas;
    }

    public void setVentas(int ventas) {
        this.ventas = ventas;
    }

    public Artista getArtista() {
        return artista;
    }

    public void setArtista(Artista artista) {
        this.artista = artista;
    }

    
    public Document toDocument() {
        return new Document("nombre", nombre)
                .append("generos", generos)
                .append("fechaEstreno", fechaEstreno)
                .append("premios", premios)
                .append("ventas", ventas)
                .append("artista", artista.toDocument());
    }
    
     @Override
    public String toString() {
        return "Cancion{" +
                "nombre='" + nombre + '\'' +
                ", generos=" + generos +
                ", fechaEstreno=" + fechaEstreno +
                ", premios=" + premios +
                ", ventas=" + ventas +
                ", artista=" + artista +
                '}';
    }
    
}