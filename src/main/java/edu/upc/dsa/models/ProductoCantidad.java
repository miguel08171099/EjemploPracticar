package edu.upc.dsa.models;

public class ProductoCantidad implements  Comparable<ProductoCantidad>{
    String idProducto;
    int cantidad;

    public ProductoCantidad(String idProducto, int cantidad){
        this.idProducto = idProducto;
        this.cantidad = cantidad;
    }

    public String getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(String idProducto) {
        this.idProducto = idProducto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public int compareTo(ProductoCantidad o) {
        return (int)(o.getCantidad()-this.getCantidad());
    }
}
