package edu.upc.dsa;

import edu.upc.dsa.models.Pedido;
import edu.upc.dsa.models.Product;

import java.util.*;

import edu.upc.dsa.models.ProductoCantidad;
import org.apache.log4j.Logger;

public class ProductManagerImpl implements ProductManager {
    private static ProductManagerImpl instance;
    protected List<Product> Products;
    protected Queue<Pedido> Pedidos;
    protected List<Pedido> PedidosVendidos;
    protected HashMap<String, ProductoCantidad> ProductosVentas;
    final static Logger logger = Logger.getLogger(ProductManagerImpl.class);

    private ProductManagerImpl() {
        this.Products = new ArrayList<>();
        this.Pedidos = new ArrayDeque<>();
        this.PedidosVendidos = new ArrayList<>();
        this.ProductosVentas = new HashMap<>();
        // ESTO NO SÉ SI ESTÁ BIEN
    }

    public static ProductManagerImpl getInstance() {
        if (instance==null) instance = new ProductManagerImpl();
        return instance;
    }

/*    public int size() {
        int ret = this.Products.size();
        logger.info("size " + ret);

        return ret;
    }*/
// AQUI FALTAN LA IMPLEMENTACION DE LAS FUNCIONES DE LA INTERFICIE
    public List<Product> listarProductosPorPrecio(){
        List<Product> productosOrdenados = Products;
        Collections.sort(productosOrdenados);
        return productosOrdenados;
    }
    public int realizarPedido(int idUsuario, List<ProductoCantidad> productos){
        int idPedido = Pedidos.size() + 1;
        Pedido pedido = new Pedido(idPedido, idUsuario, productos);
        Pedidos.add(pedido);
        return idPedido;
    }
    public int servirPedido(){
        int idPedido = 0;
        Pedido pedido = Pedidos.poll();
        if(pedido != null)
        {
            idPedido = pedido.getIdPedido();
            PedidosVendidos.add(pedido);
            for (int i = 0; i < pedido.getPedido().size(); i++) {
                ProductoCantidad productoCantidadPedido = pedido.getPedido().get(i);
                String key = productoCantidadPedido.getIdProducto();
                ProductoCantidad productoCantidadAcumulado = ProductosVentas.get(key);
                if (productoCantidadAcumulado == null) {
                    ProductosVentas.put(key, productoCantidadPedido);
                } else {
                    int ventasProducto = productoCantidadAcumulado.getCantidad()
                            + productoCantidadPedido.getCantidad();
                    productoCantidadAcumulado.setCantidad(ventasProducto);
                }
            }
        }
        return idPedido;
    }
    public List<Pedido> pedidosRealizados(int idUsuario){
        List<Pedido> pedidosUsuario = new ArrayList<Pedido>();
        for (int i=0; i<PedidosVendidos.size(); i++)
        {
            Pedido pedido = PedidosVendidos.get(i);
            if (pedido.getIdUsuario() == idUsuario)
                pedidosUsuario.add(pedido);
        }
        return pedidosUsuario;
    }
    public List<ProductoCantidad> listarProductosPorVentas() {
        //TENGO QUE USAR EL HASHMAP
        List<Product> productosPorVenta = new ArrayList<Product>();
/*        for (int i=0; i<PedidosVendidos.size(); i++)
        {
            Pedido pedido = PedidosVendidos.get(i);
            for (int j=0; j<pedido.getPedido().size(); j++)
            {
                ProductoCantidad productoCantidadPedido = pedido.getPedido().get(j);
                String key = productoCantidadPedido.getIdProducto();
                ProductoCantidad productoCantidadAcumulado = ProductosVentas.get(key);
                if(productoCantidadAcumulado == null)
                {
                    ProductosVentas.put(key, productoCantidadPedido);
                }
                else{
                    int ventasProducto = productoCantidadAcumulado.getCantidad()
                                       + productoCantidadPedido.getCantidad() ;
                    productoCantidadAcumulado.setCantidad(ventasProducto);
                }
            }
        } */

        List<ProductoCantidad> pvOrdenados = new ArrayList<>(ProductosVentas.values());
        Collections.sort(pvOrdenados);

        return pvOrdenados;
    }

    @Override
    public void liberarPedidos() {
        this.Products.clear();
        this.ProductosVentas.clear();
        this.Pedidos.clear();
        this.PedidosVendidos.clear();
    }

    @Override
    public String añadirProducto(String nombre, double precio) {
        logger.info("nuevo producto " + nombre);
        Product p = new Product(nombre, precio);
        this.Products.add(p);
        logger.info("añadido nuevo producto " + p.getId());
        return p.getId();
    }

}