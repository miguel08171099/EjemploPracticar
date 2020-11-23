package edu.upc.dsa;

import edu.upc.dsa.models.Pedido;
import edu.upc.dsa.models.Product;
import edu.upc.dsa.models.ProductoCantidad;

import java.util.List;

public interface ProductManager {

    public List<Product> listarProductosPorPrecio();
    public int realizarPedido(int idUsuario, List<ProductoCantidad> productos);
    public int servirPedido();
    public List<Pedido> pedidosRealizados(int idUsuario);
    public List<ProductoCantidad> listarProductosPorVentas();
    public void liberarPedidos();
    public String añadirProducto(String nombre, double precio);

}
