package edu.upc.dsa.services;

import edu.upc.dsa.ProductManager;
import edu.upc.dsa.ProductManagerImpl;
import edu.upc.dsa.models.Pedido;
import edu.upc.dsa.models.Product;
import edu.upc.dsa.models.ProductoCantidad;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Root resource (exposed at "myresource" path)
 */
@Api(value = "/products", description = "Endpoint to Text Service")
@Path("/products")
public class ProductService {
    private ProductManager pm;

    public ProductService() {

        this.pm = ProductManagerImpl.getInstance();
        String idProducto1;
        String idProducto2;
        String idProducto3;
        idProducto1=this.pm.añadirProducto("cafe", 1.25);
        idProducto2=this.pm.añadirProducto("cortado", 1.50);
        idProducto3=this.pm.añadirProducto("te", 1.00);
    }

    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */

    @GET
    @ApiOperation(value = "Retorna productos ordenados", notes = "asaf")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Product.class, responseContainer="List"),
    })
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProducts() {

        List<Product> products = this.pm.listarProductosPorPrecio();

        GenericEntity<List<Product>> entity = new GenericEntity<List<Product>>(products) {};

        return Response.status(201).entity(entity).build() ;

    }

   /* @Path("/{idUsuario}/{pro}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getUser(@PathParam("username") String userName) {
        return "Hello " + userName;
    }

    @Path("exception")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String throwIt() throws Exception {
        throw new Exception("My Exception");
    }

    @POST
    @ApiOperation(value = "realiza un nuevo pedido", notes = "")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response= Integer.class),
            @ApiResponse(code = 500, message = "Validation Error")

    })

    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response realizarPedido(int idUsuario, List<ProductoCantidad> p) {

        if (p==null || idUsuario == 0)
            return Response.status(500).entity(p).build();
        Integer idPedido = this.pm.realizarPedido(idUsuario, p);

        return Response.status(201).entity(idPedido).build();
    }
*/
}
