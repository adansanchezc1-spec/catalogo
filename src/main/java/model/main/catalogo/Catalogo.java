package model.main.catalogo;

import java.util.List;
import java.util.Scanner;

import model.main.controller.CatalogoController;
import model.main.menu.Gerente;
import model.main.menu.ProductoCatalogo;
import model.main.menu.ProveedorComercial;

/**
 *
 * @author ADAN
 */
public class Catalogo {

    public static void main(String[] args) {
        CatalogoController controller = new CatalogoController();
        // 1. El Gerente le da Nombre y id al cliente y al proveedor
        try (Scanner scanner = new Scanner(System.in)) {
            // 1. El Gerente le da Nombre y id al cliente y al proveedor
            Gerente gerente = new Gerente("1", "Adan");
            System.out.println("=== Configuración inicial ===");
            gerente.setCliente();  // Da nombre e id al cliente
            gerente.setProveedorComercial();  // Da nombre e id al proveedor
            
            // 2. Después añade algunos productos del proveedor
            System.out.println("\n=== Añadiendo productos ===");
            gerente.anadirProducto(controller);  // Añade productos usando input
            
            // 3. Revisa la info respecto a los productos guardados
            System.out.println("\n=== Revisando información de productos ===");
            List<ProductoCatalogo> productos = controller.obtenerProductos();
            if (productos.isEmpty()) {
                System.out.println("No hay productos guardados.");
            } else {
                productos.forEach(producto -> System.out.println(producto.getInfo()));
            }
            
            // 4. Menú para controlar generar nuevos productos, actualizarlos, eliminarlos o cambiar proveedor
            boolean continuar = true;
            while (continuar) {
                System.out.println("\n=== Menú de Gestión de Catálogo ===");
                System.out.println("1. Generar nuevo producto");
                System.out.println("2. Actualizar producto");
                System.out.println("3. Eliminar producto");
                System.out.println("4. Cambiar proveedor de un producto");
                System.out.println("5. Ver todos los productos");
                System.out.println("6. Salir");
                System.out.print("Seleccione una opción: ");
                int opcion = scanner.nextInt();
                scanner.nextLine();  // Consumir newline
                
                switch (opcion) {
                    case 1 -> {
                        System.out.print("ID del producto: ");
                        String id = scanner.nextLine();
                        System.out.print("Nombre: ");
                        String nombre = scanner.nextLine();
                        System.out.print("Descripción: ");
                        String descripcion = scanner.nextLine();
                        System.out.print("Precio: ");
                        double precio = scanner.nextDouble();
                        scanner.nextLine();  // Consumir newline
                        System.out.print("ID del proveedor: ");
                        String provId = scanner.nextLine();
                        System.out.print("Nombre del proveedor: ");
                        String provNombre = scanner.nextLine();
                        ProveedorComercial proveedor = new ProveedorComercial(provId, provNombre);
                        ProductoCatalogo nuevoProducto = new ProductoCatalogo(id, nombre, descripcion, precio, proveedor);
                        controller.crearProducto(nuevoProducto);
                        System.out.println("Producto creado.");
                    }
                    case 2 -> {
                        System.out.print("ID del producto a actualizar: ");
                        String updateId = scanner.nextLine();
                        ProductoCatalogo existente = controller.obtenerProductoPorId(updateId);
                        if (existente != null) {
                            System.out.print("Nuevo nombre: ");
                            String newNombre = scanner.nextLine();
                            System.out.print("Nueva descripción: ");
                            String newDesc = scanner.nextLine();
                            System.out.print("Nuevo precio: ");
                            double newPrecio = scanner.nextDouble();
                            scanner.nextLine();
                            ProductoCatalogo actualizado = new ProductoCatalogo(updateId, newNombre, newDesc, newPrecio, existente.getProveedor());
                            controller.actualizarProducto(actualizado);
                            System.out.println("Producto actualizado.");
                        } else {
                            System.out.println("Producto no encontrado.");
                        }
                    }
                    case 3 -> {
                        System.out.print("ID del producto a eliminar: ");
                        String deleteId = scanner.nextLine();
                        controller.eliminarProducto(deleteId);
                        System.out.println("Producto eliminado.");
                    }
                    case 4 -> {
                        System.out.print("ID del producto: ");
                        String prodId = scanner.nextLine();
                        System.out.print("Nuevo ID del proveedor: ");
                        String newProvId = scanner.nextLine();
                        System.out.print("Nuevo nombre del proveedor: ");
                        String newProvNombre = scanner.nextLine();
                        ProveedorComercial newProveedor = new ProveedorComercial(newProvId, newProvNombre);
                        controller.cambiarProveedor(prodId, newProveedor);
                        System.out.println("Proveedor cambiado.");
                    }
                    case 5 -> {
                        System.out.println("=== Productos ===");
                        controller.obtenerProductos().forEach(producto -> System.out.println(producto.getInfo()));
                    }
                    case 6 -> continuar = false;
                    default -> System.out.println("Opción inválida.");
                }
            }
        }
    }
}
