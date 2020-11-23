package edu.upc.dsa.models;

import edu.upc.dsa.util.RandomUtils;

public class Product implements Comparable<Product>{

    String id;
    String nombre;
    double precio;

    public Product() {
        this.id = RandomUtils.getId();
    }

    public Product(String nombre, double precio) {
        this();
        this.setPrecio(precio);
        this.setnombre(nombre);
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id=id;
    }


    public String getnombre() {
        return nombre;
    }

    public void setnombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "Product [id="+id+", nombre=" + nombre + ", precio=" + precio +"]";
    }

    @Override
    public int compareTo(Product o) {
        return (int)(this.getPrecio()-o.getPrecio());
    }
}