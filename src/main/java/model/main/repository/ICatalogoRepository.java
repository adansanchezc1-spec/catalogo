/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package model.main.repository;

import java.util.List;

import model.main.menu.ProductoCatalogo;

/**
 *
 * @author ADAN
 */
public interface ICatalogoRepository {
    void saveData(ProductoCatalogo data);
    List<ProductoCatalogo> findAll();
    List<ProductoCatalogo> loadData();
    void updateData(ProductoCatalogo data);
    void deleteData(String id);
    ProductoCatalogo findDataById(String id);
}
