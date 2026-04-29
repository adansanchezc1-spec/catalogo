package model.main.catalogo;

import java.util.List;
import java.util.Scanner;

import model.main.controller.CatalogoController;
import model.main.menu.Gerente;
import model.main.menu.ProductoCatalogo;
import model.main.menu.ProveedorComercial;

public class Catalogo {

    public static void main(String[] args) {
        CatalogoController controller = new CatalogoController();

        try (Scanner scanner = new Scanner(System.in)) {
            Gerente gerente = new Gerente("1", "Adan", scanner);

            System.out.println("=== Configuracion inicial ===");
            gerente.setCliente();
            gerente.setProveedorComercial();

            System.out.println("\n=== Anadiendo productos ===");
            gerente.anadirProducto(controller);

            System.out.println("\n=== Revisando informacion de productos ===");
            mostrarProductos(controller.obtenerProductos());

            ejecutarMenu(scanner, controller);
        }
    }

    private static void ejecutarMenu(Scanner scanner, CatalogoController controller) {
        boolean continuar = true;
        while (continuar) {
            mostrarMenu();
            int opcion = leerEntero(scanner, "Seleccione una opcion: ");

            switch (opcion) {
                case 1 -> crearProducto(scanner, controller);
                case 2 -> actualizarProducto(scanner, controller);
                case 3 -> eliminarProducto(scanner, controller);
                case 4 -> cambiarProveedor(scanner, controller);
                case 5 -> mostrarProductos(controller.obtenerProductos());
                case 6 -> continuar = false;
                default -> System.out.println("Opcion invalida.");
            }
        }
    }

    private static void mostrarMenu() {
        System.out.println("\n=== Menu de Gestion de Catalogo ===");
        System.out.println("1. Generar nuevo producto");
        System.out.println("2. Actualizar producto");
        System.out.println("3. Eliminar producto");
        System.out.println("4. Cambiar proveedor de un producto");
        System.out.println("5. Ver todos los productos");
        System.out.println("6. Salir");
    }

    private static void crearProducto(Scanner scanner, CatalogoController controller) {
        controller.crearProducto(leerProducto(scanner, null));
        System.out.println("Producto creado.");
    }

    private static void actualizarProducto(Scanner scanner, CatalogoController controller) {
        System.out.print("ID del producto a actualizar: ");
        String id = scanner.nextLine();
        ProductoCatalogo existente = controller.obtenerProductoPorId(id);

        if (existente == null) {
            System.out.println("Producto no encontrado.");
            return;
        }

        controller.actualizarProducto(leerProducto(scanner, existente.getProveedor(), id));
        System.out.println("Producto actualizado.");
    }

    private static void eliminarProducto(Scanner scanner, CatalogoController controller) {
        System.out.print("ID del producto a eliminar: ");
        controller.eliminarProducto(scanner.nextLine());
        System.out.println("Producto eliminado.");
    }

    private static void cambiarProveedor(Scanner scanner, CatalogoController controller) {
        System.out.print("ID del producto: ");
        String productoId = scanner.nextLine();
        controller.cambiarProveedor(productoId, leerProveedor(scanner, "Nuevo "));
        System.out.println("Proveedor cambiado.");
    }

    private static ProductoCatalogo leerProducto(Scanner scanner, ProveedorComercial proveedor) {
        System.out.print("ID del producto: ");
        String id = scanner.nextLine();
        return leerProducto(scanner, proveedor, id);
    }

    private static ProductoCatalogo leerProducto(Scanner scanner, ProveedorComercial proveedor, String id) {
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Descripcion: ");
        String descripcion = scanner.nextLine();
        double precio = leerDecimal(scanner, "Precio: ");

        ProveedorComercial proveedorFinal = proveedor != null ? proveedor : leerProveedor(scanner, "");
        return new ProductoCatalogo(id, nombre, descripcion, precio, proveedorFinal);
    }

    private static ProveedorComercial leerProveedor(Scanner scanner, String prefijo) {
        System.out.print(prefijo + "ID del proveedor: ");
        String proveedorId = scanner.nextLine();
        System.out.print(prefijo + "Nombre del proveedor: ");
        String proveedorNombre = scanner.nextLine();
        return new ProveedorComercial(proveedorId, proveedorNombre);
    }

    private static int leerEntero(Scanner scanner, String mensaje) {
        while (true) {
            System.out.print(mensaje);
            if (scanner.hasNextInt()) {
                int valor = scanner.nextInt();
                scanner.nextLine();
                return valor;
            }
            System.out.println("Ingrese un numero valido.");
            scanner.nextLine();
        }
    }

    private static double leerDecimal(Scanner scanner, String mensaje) {
        while (true) {
            System.out.print(mensaje);
            if (scanner.hasNextDouble()) {
                double valor = scanner.nextDouble();
                scanner.nextLine();
                return valor;
            }
            System.out.println("Ingrese un precio valido.");
            scanner.nextLine();
        }
    }

    private static void mostrarProductos(List<ProductoCatalogo> productos) {
        if (productos.isEmpty()) {
            System.out.println("No hay productos guardados.");
            return;
        }
        productos.forEach(producto -> System.out.println(producto.getInfo()));
    }
}
