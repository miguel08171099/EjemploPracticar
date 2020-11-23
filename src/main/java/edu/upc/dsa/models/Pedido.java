package edu.upc.dsa.models;

import edu.upc.dsa.util.RandomUtils;

import java.util.List;

public class Pedido {
    int idPedido;
    int idUsuario;
    List<ProductoCantidad> pedido;


    public Pedido(int idPedido, int idUsuario, List<ProductoCantidad> pedido) {
        this.setIdPedido(idPedido);
        this.setPedido(pedido);
        this.setIdUsuario(idUsuario);
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public List<ProductoCantidad> getPedido() {
        return pedido;
    }

    public void setPedido(List<ProductoCantidad> pedido) {
        this.pedido = pedido;
    }
}
