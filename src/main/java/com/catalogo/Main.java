package com.catalogo;

import com.catalogo.controller.CatalogoController;
import com.catalogo.model.ProductoCatalogo;
import com.catalogo.repository.CatalogoRepositoryJSON;
import com.catalogo.service.CatalogoService;
import com.catalogo.view.CatalogoView;

public class Main {

    public static void main(String[] args) {
        CatalogoRepositoryJSON repo = new CatalogoRepositoryJSON("data/catalogo.json");
        CatalogoService service = new CatalogoService(repo);
        CatalogoView view = new CatalogoView();
        CatalogoController controller = new CatalogoController(service, view);

        System.out.println("========================================");
        System.out.println("   SISTEMA DE CATALOGO DE ROPA v1.0");
        System.out.println("========================================");

        System.out.println("\n[CREATE] Agregando productos...");
        controller.crearProducto(new ProductoCatalogo("P001", "Camiseta Basica",
                "Algodon 100%", 25000, "Tops"));
        controller.crearProducto(new ProductoCatalogo("P002", "Jeans Slim Fit",
                "Denim azul oscuro", 89000, "Pantalones"));
        controller.crearProducto(new ProductoCatalogo("P003", "Chaqueta Bomber",
                "Polyester premium", 150000, "Outerwear"));
        controller.crearProducto(new ProductoCatalogo("P004", "Vestido Floral",
                "Tela ligera estampada", 75000, "Vestidos"));
        controller.crearProducto(new ProductoCatalogo("P005", "Zapatillas Canvas",
                "Tela con suela de goma", 65000, "Calzado"));

        // ── READ ──────────────────────────────────────────────────────────
        System.out.println("\n📋  [READ] Catálogo completo:");
        controller.obtenerProductos();

        // ── UPDATE ────────────────────────────────────────────────────────
        System.out.println("\n✏️   [UPDATE] Actualizando Jeans...");
        controller.actualizarProducto(new ProductoCatalogo("P002",
                "Jeans Slim Fit Premium", "Denim stretch 4-way", 99000, "Pantalones"));

        // ── READ (post-update) ─────────────────────────────────────────────
        System.out.println("\n📋  [READ] Catálogo después de actualización:");
        controller.obtenerProductos();

        // ── DELETE ────────────────────────────────────────────────────────
        System.out.println("\n🗑️   [DELETE] Eliminando Zapatillas Canvas (P005)...");
        controller.eliminarProducto("P005");

        // ── READ (post-delete) ─────────────────────────────────────────────
        System.out.println("\n📋  [READ] Catálogo final:");
        controller.obtenerProductos();

        System.out.println("Demo completado. Datos persistidos en: data/catalogo.json");
    }
}
