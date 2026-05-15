package com.catalogo.model;

public class ProductoCatalogo {

    private String id;
    private String nombre;
    private String descripcion;
    private double precio;
    private String categoria;

    public ProductoCatalogo() {
    }

    public ProductoCatalogo(String id, String nombre, String descripcion, double precio, String categoria) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.categoria = categoria;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getInfo() {
        return String.format("[%s] %s | %s | $%.2f | Categoria: %s",
                id, nombre, descripcion, precio, categoria);
    }

    @Override
    public String toString() {
        return getInfo();
    }
}
