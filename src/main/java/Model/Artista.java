/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.util.Date;
import org.bson.Document;

/**
 *
 * @author eduar
 */
public class Artista {
    
private String nombre;
    private String pais;
    private Date fechaNacimiento;
    private String genero;

    // Constructor, getters y setters

    public Artista(String nombre, String pais, Date fechaNacimiento, String genero) {
        this.nombre = nombre;
        this.pais = pais;
        this.fechaNacimiento = fechaNacimiento;
        this.genero = genero;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }
    
    
    
    public Document toDocument() {
        return new Document("nombre", nombre)
                .append("pais", pais)
                .append("fechaNacimiento", fechaNacimiento)
                .append("genero", genero);
    }

    @Override
    public String toString() {
        return "Artista{" + "nombre=" + nombre + ", pais=" + pais + ", fechaNacimiento=" + fechaNacimiento + ", genero=" + genero + '}';
    }
    
    
}
