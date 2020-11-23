package edu.upc.dsa;

import edu.upc.dsa.models.Product;
import edu.upc.dsa.models.ProductoCantidad;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;


public class ManagerTest {
    ProductManagerImpl productManager;
    String idProducto1;
    String idProducto2;
    String idProducto3;
    int Usuario1 = 1;
    int Usuario2 = 2;
    int idPedido = 0;
    List<ProductoCantidad> pedido1 = new ArrayList<ProductoCantidad>();
    List<ProductoCantidad> pedido2 = new ArrayList<ProductoCantidad>();
    @Before
    public void setUp() throws Exception{
        this.productManager = ProductManagerImpl.getInstance();
        //Crear productos
        idProducto1=this.productManager.añadirProducto("cafe", 1.25);
        idProducto2=this.productManager.añadirProducto("cortado", 1.50);
        idProducto3=this.productManager.añadirProducto("te", 1.00);
    }
    @After
    public void tearDown(){
        this.productManager.liberarPedidos();
    }
    /*public void tearDown(){
        this.productManager.getInstance().clear();
    }*/
    @Test
    public void realizarPedido() throws Exception {

        pedido1.add(new ProductoCantidad(idProducto1, 2 ));
        pedido1.add(new ProductoCantidad(idProducto3, 1 ));
        idPedido = this.productManager.realizarPedido(Usuario1,pedido1);
        Assert.assertEquals(1, idPedido);

        pedido2.add(new ProductoCantidad(idProducto2, 3 ));
        pedido2.add(new ProductoCantidad(idProducto1, 2 ));
        idPedido = this.productManager.realizarPedido(Usuario1,pedido2);
        Assert.assertEquals(2, idPedido);
    }

    @Test
    public void servirPedido() throws Exception {
        pedido1.add(new ProductoCantidad(idProducto1, 2 ));
        pedido1.add(new ProductoCantidad(idProducto3, 1 ));
        idPedido = this.productManager.realizarPedido(Usuario1,pedido1);

        pedido2.add(new ProductoCantidad(idProducto2, 3 ));
        pedido2.add(new ProductoCantidad(idProducto1, 2 ));
        idPedido = this.productManager.realizarPedido(Usuario1,pedido2);

        idPedido = this.productManager.servirPedido();
        Assert.assertEquals(1, idPedido);
        idPedido = this.productManager.servirPedido();
        Assert.assertEquals(2, idPedido);
        idPedido = this.productManager.servirPedido();
        Assert.assertEquals(0, idPedido);
    }

}