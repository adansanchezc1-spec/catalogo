/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.main.menu;
import java.util.Scanner;
public class Gerente {
    
    private String id;
    private String nombre;
    private String idCliente;
    private String nombreCliente;
    private String info;
    private ProveedorComercial proveedor;
    Scanner sc= new Scanner(System.in);

public void setCliente(){
        System.out.println("Introduzca el nombre del cliente");
        nombreCliente=sc.nextLine();
        System.out.println("Introduzca el id del cliente");
        idCliente=sc.nextLine();
        Cliente miCliente= new Cliente(nombreCliente,idCliente);

    }
public Cliente realizarReunion(Cliente c){
     System.out.println("Reunión con el cliente: " + c.getNombre());
    setCliente();
        // lógica de negocio aquí

    return c;
}
public void setProveedorComercial(){
    System.out.println("Introduzca el nombre del proveedor comercial");
    String nombreProveedor=sc.nextLine();
    System.out.println("Introduzca el id del proveedor comercial");
    String idProveedor=sc.nextLine();
    this.proveedor = new ProveedorComercial(idProveedor,nombreProveedor);
}
public void proveerInformacion(){
    System.out.println("Introduzca la información a revisar");
    info=sc.nextLine();
    System.out.println("Revisando la información: " + info);
}
public void anadirProducto(model.main.controller.CatalogoController controller){
    System.out.println("Introduzca el id del producto");
    String idProducto=sc.nextLine();
    System.out.println("Introduzca el nombre del producto");
    String nombreProducto=sc.nextLine();
    System.out.println("Introduzca la descripción del producto");
    String descripcionProducto=sc.nextLine();
    System.out.println("Introduzca el precio del producto");
    double precioProducto=sc.nextDouble();
    sc.nextLine(); // Consumir newline
    ProductoCatalogo nuevoProducto= new ProductoCatalogo(idProducto,nombreProducto,descripcionProducto,precioProducto, this.proveedor);
    controller.crearProducto(nuevoProducto);
    System.out.println("Producto añadido: " + nuevoProducto.getInfo());
}
public void solicitarCatalogo(model.main.controller.CatalogoController controller) {
    System.out.println("El gerente solicita el catálogo al proveedor comercial...");
    // Simular que el proveedor crea productos
    ProveedorComercial proveedor = new ProveedorComercial("Prov001", "Proveedor Ejemplo");
    ProductoCatalogo producto = proveedor.crearProductoEjemplo();
    controller.crearProducto(producto);
    System.out.println("Producto agregado al catálogo por el proveedor: " + producto.getInfo());
}
public Gerente(String id, String nombre) {
    this.id = id;
    this.nombre = nombre;
}
public String getNombre() {
    return nombre;
}
}
