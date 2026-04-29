/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.main.menu;

/**
 *
 * @author ADAN
 */
public class ProductoCatalogo {
    private String id;
    private String nombre;
    private String descripcion;
    private double precio;

    public ProductoCatalogo(String id, String nombre, String descripcion, double precio) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public String getInfo() {
        return "ID: " + id + ", Nombre: " + nombre + ", Descripción: " + descripcion + ", Precio: " + precio;
    }

    @Override
    public String toString() {
        return getInfo();
    }
}
