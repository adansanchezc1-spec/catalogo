/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.main.controller;
import model.main.menu.Cliente;
import model.main.menu.Gerente;
import model.main.menu.ProductoCatalogo;
import model.main.repository.CatalogoRepositoryJSON;


/**
 *
 * @author ADAN
 */
public class CatalogoController {
    
    public void ejecutarGerente(Gerente c){
        CatalogoRepositoryJSON repository = new CatalogoRepositoryJSON();

        ProductoCatalogo producto1 = new ProductoCatalogo("P001", "Cámara", "Cámara digital", 2500.0);
        ProductoCatalogo producto2 = new ProductoCatalogo("P002", "Televisor", "TV 4K", 7800.0);
        if (repository.findDataById("P001") == null) {
            repository.saveData(producto1);
        }
        if (repository.findDataById("P002") == null) {
            repository.saveData(producto2);
        }

        System.out.println("Productos guardados en catalogo.json:");
        repository.findAll().forEach(producto -> System.out.println(producto.getInfo()));

        ProductoCatalogo productoActualizado = new ProductoCatalogo("P002", "Televisor", "TV 4K Smart", 8200.0);
        repository.updateData(productoActualizado);
        System.out.println("\nProducto actualizado:");
        System.out.println(repository.findDataById("P002"));

        repository.deleteData("P001");
        System.out.println("\nProductos restantes después de eliminar P001:");
        repository.findAll().forEach(producto -> System.out.println(producto.getInfo()));

        Gerente c1 = new Gerente("1","Adan");
        c1.realizarReunion(new Cliente("Cliente1","123"));
        c1.setProveedorComercial();
        c1.proveerInformacion();
    }

}
