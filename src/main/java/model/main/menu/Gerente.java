package model.main.menu;

import java.util.Objects;
import java.util.Scanner;

import model.main.controller.CatalogoController;

public class Gerente {

    private final String id;
    private final String nombre;
    private final Scanner scanner;
    private ProveedorComercial proveedor;

    public Gerente(String id, String nombre) {
        this(id, nombre, new Scanner(System.in));
    }

    public Gerente(String id, String nombre, Scanner scanner) {
        this.id = Objects.requireNonNull(id, "El ID no puede ser nulo");
        this.nombre = Objects.requireNonNull(nombre, "El nombre no puede ser nulo");
        this.scanner = Objects.requireNonNull(scanner, "El scanner no puede ser nulo");
    }

    public void setCliente() {
        System.out.println("Introduzca el nombre del cliente");
        String nombreCliente = scanner.nextLine();
        System.out.println("Introduzca el id del cliente");
        String idCliente = scanner.nextLine();
        new Cliente(nombreCliente, idCliente);
    }

    public Cliente realizarReunion(Cliente cliente) {
        System.out.println("Reunion con el cliente: " + cliente.getNombre());
        setCliente();
        return cliente;
    }

    public void setProveedorComercial() {
        System.out.println("Introduzca el nombre del proveedor comercial");
        String nombreProveedor = scanner.nextLine();
        System.out.println("Introduzca el id del proveedor comercial");
        String idProveedor = scanner.nextLine();
        this.proveedor = new ProveedorComercial(idProveedor, nombreProveedor);
    }

    public void proveerInformacion() {
        System.out.println("Introduzca la informacion a revisar");
        String info = scanner.nextLine();
        System.out.println("Revisando la informacion: " + info);
    }

    public void anadirProducto(CatalogoController controller) {
        System.out.println("Introduzca el id del producto");
        String idProducto = scanner.nextLine();
        System.out.println("Introduzca el nombre del producto");
        String nombreProducto = scanner.nextLine();
        System.out.println("Introduzca la descripcion del producto");
        String descripcionProducto = scanner.nextLine();
        System.out.println("Introduzca el precio del producto");
        double precioProducto = scanner.nextDouble();
        scanner.nextLine();

        ProductoCatalogo nuevoProducto = new ProductoCatalogo(
                idProducto,
                nombreProducto,
                descripcionProducto,
                precioProducto,
                proveedor);
        controller.crearProducto(nuevoProducto);
        System.out.println("Producto anadido: " + nuevoProducto.getInfo());
    }

    public void solicitarCatalogo(CatalogoController controller) {
        System.out.println("El gerente solicita el catalogo al proveedor comercial...");
        ProveedorComercial proveedorEjemplo = new ProveedorComercial("Prov001", "Proveedor Ejemplo");
        ProductoCatalogo producto = proveedorEjemplo.crearProductoEjemplo();
        controller.crearProducto(producto);
        System.out.println("Producto agregado al catalogo por el proveedor: " + producto.getInfo());
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }
}
